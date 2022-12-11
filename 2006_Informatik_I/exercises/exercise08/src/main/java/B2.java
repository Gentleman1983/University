class B2 
{
    private String x = "foo";

    public void foo (int x, int y) 
    {
        A2.foo(x,y);
    }

    public static void main (String[] args) 
    {
      B2 objB = new B2();
      B2.foo(1,1);
    }
}