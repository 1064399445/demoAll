package cn.jun.concurrency.java_concurrency_in_practice;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
