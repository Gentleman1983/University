class Min
{
	public static void main(String[] EventArgs)
	{
		int list[] = {10 , 20 , -90000 , -1 , 22 , 34 , 3 , 20 , 100 , 99};
		int length = 10;
		int v0 = 0x7fffffff;
		int a0 = 0;
		int a1 = length;
		int t2;
		while(a1 > 0)
		{
			t2 = list[a0];
			a0++;
			a1--;
			if(t2 <= v0)
			{
				v0 = t2;
			}
		}
		int s0 = v0;
//----------------------------------------------------------------
		System.out.println("Minimum " + s0 );
	}
}
