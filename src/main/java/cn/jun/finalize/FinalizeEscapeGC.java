package cn.jun.finalize;

public class FinalizeEscapeGC {

    public static FinalizeEscapeGC saveMe  = null;

    public void isAlive(){
        System.out.println("我还活着");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("垃圾回收");
        FinalizeEscapeGC.saveMe = this;
    }

    public static void main(String[] args){
        saveMe = new FinalizeEscapeGC();
        //1. 对象引用置空
        saveMe = null;
        //2. 垃圾回收，并在finalize方法中拯救自己
        System.gc();
        //3. 暂停一会
        try {
            Thread.sleep(500);
        }catch (Exception e){

        }
        //4. 是否拯救自己
        if(saveMe != null){
            saveMe.isAlive();
        }else {
            System.out.println("啊 我挂了!");
        }

        //-----再来一遍
        //1. 对象引用置空
        saveMe = null;
        //2. 垃圾回收，并在finalize方法中拯救自己
        System.gc();
        //3. 暂停一会
        try {
            Thread.sleep(500);
        }catch (Exception e){

        }
        //4. 是否拯救自己
        if(saveMe != null){
            saveMe.isAlive();
        }else {
            System.out.println("啊 我挂了!");
        }
    }
}
