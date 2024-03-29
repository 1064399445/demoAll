package cn.jun.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClient {

    public static void main(String[] args){
        new Thread(new TimeClientHandle("127.0.0.1", 8080)).start();
    }
}

class TimeClientHandle implements Runnable{

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;


    public TimeClientHandle(String host, int port){
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(selector != null){
            try {
                selector.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws Exception{
        //如果连接成功
        if(key.isValid()){
            SocketChannel sc = (SocketChannel)key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                }else {
                    System.exit(1);
                }
            }
            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now is : " + body);
                    this.stop = true;
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }else {
                    //读到0字节忽略
                }
            }
        }
    }

    private void doConnect() throws Exception{
        //如果连接成功，则注册到多路复用器上，发送请求消息，读应答
        if(socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }
    private void doWrite(SocketChannel sc) throws Exception{
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeByter = ByteBuffer.allocate(req.length);
        writeByter.put(req);
        writeByter.flip();
        sc.write(writeByter);
        if(!writeByter.hasRemaining()){
            System.out.println("Send order 2 server succeed.");
        }
    }
}
