package cn.jun.netty.codec.protobuf;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws Exception{
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("lihuajun");
        builder.setProductName("Netty Book");
        List<String> list = new ArrayList<>();
        list.add("wuhan");
        list.add("tianjin");
        list.add("beijing");
        builder.addAllAddress(list);
        return builder.build();
    }

    public static void main(String[] args) throws Exception{
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode : " + req.toString());
        SubscribeReqProto.SubscribeReq req1 = decode(encode(req));
        System.out.println("After decode : " + req.toString());
        System.out.println("Assert equal : --> " + req1.equals(req));
    }

}
