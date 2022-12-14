import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.axis.components.uuid.UUIDGen;
import org.apache.axis.components.uuid.UUIDGenFactory;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.axis.message.addressing.Address;
import org.globus.exec.client.GramJob;
import org.globus.exec.client.GramJobListener;
import org.globus.exec.generated.StateEnumeration;
import org.globus.exec.generated.JobDescriptionType;
import org.globus.exec.generated.FilePairType;
import org.globus.exec.utils.ManagedJobConstants;
import org.globus.wsrf.impl.security.authentication.Constants;
import org.globus.wsrf.impl.security.authorization.Authorization;
import org.globus.wsrf.impl.security.authorization.HostAuthorization;
import org.globus.wsrf.impl.SimpleResourceKey;

public class SubmitJob implements GramJobListener  {
	
	private static Object waiter = new Object();

	public static void main (String args[]) {
		
		SubmitJob client = new SubmitJob();
		System.out.print("submitting job ... ");
		try {
			client.submitJob();
			System.out.println("done");
			System.out.println("Waiting for notification messages ...");
			synchronized (waiter) {
				waiter.wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitJob() throws Exception {
		// create factory epr
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(new Address
			("https://server:8443/wsrf/services/ManagedJobFactoryService"));
		ReferencePropertiesType props = new ReferencePropertiesType();
		SimpleResourceKey key = 
			new SimpleResourceKey(ManagedJobConstants.RESOURCE_KEY_QNAME, "Fork");
		props.add(key.toSOAPElement());
		endpoint.setProperties(props);

		// job rsl
		String rsl = 
			"<job><executable>/bin/sleep</executable><argument>100</argument></job>";
  
		// setup security
		Authorization authz = HostAuthorization.getInstance();
		Integer xmlSecurity = Constants.ENCRYPTION;

		boolean batchMode = false;
		boolean limitedDelegation = true;

		// generate job uuid
		UUIDGen uuidgen   = UUIDGenFactory.getUUIDGen();
		String submissionID = "uuid:" + uuidgen.nextUUID();

		GramJob job = new GramJob(rsl);
		job.setAuthorization(authz);
		job.setMessageProtectionType(xmlSecurity);
		job.setDelegationEnabled(true);
		job.addListener(this);

		job.submit(	endpoint,
					batchMode,
					limitedDelegation,
					submissionID);
	}

	// GramJob calls this method when a job changes its state
	// It's part of GramJobListener Interface
	public void stateChanged(GramJob job) {
        
		StateEnumeration jobState = job.getState();
		System.out.println("got state notifiation: job is in state " + jobState);
		if (jobState.equals(StateEnumeration.Done)
				|| jobState.equals(StateEnumeration.Failed)) {
			System.out.print("job finished. destroying job resource ... ");
			try {
				job.removeListener(this);
				job.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("done");
				synchronized (waiter) {
					waiter.notify();
				}
			}
		}
	}
}

