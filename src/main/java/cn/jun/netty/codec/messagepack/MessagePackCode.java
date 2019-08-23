package cn.jun.netty.codec.messagepack;


import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

public class MessagePackCode {

    public static void main(String[] args) throws Exception{
        List<String> list = new ArrayList<>();
        list.add("小王");
        list.add("效力");
        list.add("笑话");
        //创建MessagePack
        MessagePack messagePack = new MessagePack();
        //编码
        byte[] bytes = messagePack.write(list);
        //解码
        List<String> list1 = messagePack.read(bytes, Templates.tList(Templates.TString));

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        System.out.println(list1.get(2));

    }

}
