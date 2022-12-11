public class SyntaxChk 
{
    public SyntaxChk() 
    {
    }
    public static void main(String[] args) 
    {
A[] obj = {new A(), new B(), new A(), new B()};
obj[0].setA(1, 2);
obj[1].setA(1, 2);
obj[2].setA(2, 3);
for (int i = 0; i<obj.length; i++) obj[i].out();
    }
    
}
