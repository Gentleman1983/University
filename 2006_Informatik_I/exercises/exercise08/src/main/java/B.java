class B extends A 
{
    private int c;

    public B() 
    {
        super();
        c = 0;
    }

    public void out() 
    {
        super.out();
        System.out.print(", c=" + c);
    }

    public void setB(int x, int y, int z) 
    {
        setA(x, y);
        c = z;
    }
}