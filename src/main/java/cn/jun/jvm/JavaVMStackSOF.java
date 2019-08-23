package cn.jun.jvm;

/**
 * VM args:-Xss2m
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args){
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
            stackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+stackSOF.stackLength);
            throw e;
        }
    }

}
