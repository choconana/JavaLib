package generalpractice;

public class InitTest1
{
    public static InitTest1 t1 = new InitTest1();
    {
         System.out.println("blockA");
    }
    static
    {
        System.out.println("blockB");
    }
    public static void main(String[] args)
    {
        InitTest1 t2 = new InitTest1();
    }
 }