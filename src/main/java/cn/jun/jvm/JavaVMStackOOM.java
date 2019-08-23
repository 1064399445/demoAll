package cn.jun.jvm;

/**
 * VM args:-Xss2m
 */
public class JavaVMStackOOM {

    private  void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            new Thread(() -> {
                dontStop();
            }).start();
        }
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}
