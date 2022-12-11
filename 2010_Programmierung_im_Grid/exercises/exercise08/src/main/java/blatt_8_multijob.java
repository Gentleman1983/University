MultiJobDescriptionType multi = new MultiJobDescriptionType();                  
List<JobDescriptionType> multijobs = new ArrayList<JobDescriptionType>();

// create JobDescriptionType ...

multijobs.add(<JobDescriptionType>);

multi.setJob((JobDescriptionType[])multijobs.toArray(new JobDescriptionType[0]));
	    
// remote host
String contact = "server";

// Factory type: Fork, Condor, PBS, LSF
String factoryType  = ManagedJobFactoryConstants.FACTORY_TYPE.MULTI;

EndpointReferenceType factoryEndpoint = getFactoryEPR(contact,factoryType);

GramJob job = new GramJob(multi); 