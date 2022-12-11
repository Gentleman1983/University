class A 
{
    private int a;
    protected int b;

    public A() 
    {
        a = 0;
        b = 0;
    }

    public void out() 
    {
        System.out.print("\na=" + a + ", b=" + b);
    }

    public void setA(int x, int y) 
    {
        a = x;
        b = y;
    }
}