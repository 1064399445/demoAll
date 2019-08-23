package cn.jun.test;


public class Test {

    static int x, y;

    static {
        System.out.println(x);
        x = 5;
    }

    static {
        System.out.println(x);
        x = 6;
    }


    public static void main(String[] args) throws Exception{
        System.out.println(x);

    }



}
