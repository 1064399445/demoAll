package cn.jun.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TimeServer {

    public static void main(String[] args) throws Exception{
        int port = 8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port){
        try {
            //创建多路复用器
            selector = Selector.open();
            //打开ServerSocketChannel用于监听客户端连接
            serverSocketChannel = ServerSocketChannel.open();
            //设置连接为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //将ServerSocketChannel注册到多路复用器Selector上，监听ACCEPT事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("时间服务器启动通过端口：" + port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()){
                    selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(selectionKey);
                    }catch (Exception e){

                    }
                }
            }catch (Exception e){
                if(selector != null){
                    try {
                        selector.close();
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        //处理新接入的请求消息
        if(key.isValid()){
            if(key.isAcceptable()){
                //接入新的连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //将新的连接注册到多路复用器上
                sc.register(selector, SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //读取数据
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBuffers = sc.read(readBuffer);
                if(readBuffers > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("时间服务器接收到顺序：" + body);
                    String currentTime = "QUERY TIME ORDER"
                            .equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                }else if(readBuffers < 0){
                    key.cancel();
                    sc.close();
                }else {
                    //读到0字节
                }
            }
        }

    }

    private void doWrite(SocketChannel channel, String response) throws IOException{
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

}
