class Shower 
{
	int males = 0;
	int females = 0;
	int waitingMales = 0;
	int waitingFemales = 0;
	Semaphore lock = 1;		// mutex
	Semaphore semFemale = 0;	// always wait
	Semaphore semMale = 0;		// always wait

	female_requests_to_enter() 
	{
		down(lock);

		if(males > 0)
		{
			waitingFemales++;
			up(lock);

			// possible interruption may only problematic if the last male leaves in the meantime.
			// But waitingFemales is already increased, that means after male_leaves is females = 1
			// and semFemale = 1. So no male can enter the shower, number of females is correct and
			// down(semFemale) will not block this task because now is it a female shower.
			//
			// up(lock) after down(semFemale) is a fatal error and can deadlock the system.

			down(semFemale);
		}
		else 
		{
			females++;
			up(lock);
		}
	}

	male_requests_to_enter() // You could write "Yust like female_requests_to_enter() with changed genders"
	{			 // while writing a test to save time.
		down(lock);

		if(females > 0) 
		{
			waitingMales++;
			up(lock);
			down(semMale);
		}
		else 
		{
			males++;
			up(lock);
		}
	}

	female_leaves() 
	{
		down(lock);
		females--;

		if(females == 0) 
		{
			while(waitingMales > 0) 
			{
				waitingMales--;
				males++;
				up(semMale);
			}
		}
		up(lock);
	}

	male_leaves() 
	{
		down(lock);
		males--;

		if(males == 0) 
		{
			while(waitingFemales > 0) 
			{
				waitingFemales--;
				females ++;
				up(semFemale);
			}
		}
		up(lock);
	}
}


