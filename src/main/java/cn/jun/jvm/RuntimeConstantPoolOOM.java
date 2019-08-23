package cn.jun.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args1:-XX:PermSize=10m -XX:MaxPermSize=10m
 * VM args2:-Xms2m -Xmx2m
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

}
