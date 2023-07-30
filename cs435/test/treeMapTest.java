import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.Map.*;
public class treeMapTest{
    public static void main(String[] args){
        final TreeMap<String,String> a = null;
        a.put("a","a");
        a.put("b","b");
        a.put("c","c");
        for(Entry<String,String> entry : a.entrySet()){
            System.out.println(entry.getKey()+", "+entry.getValue());
        }
        // final TreeMap<String,String> b = new TreeMap<String,String>();
        // b.putAll(a);
        // for(Entry<String,String> entry : b.entrySet()){
        //     System.out.println(entry.getKey()+", "+entry.getValue());
        // }
    }
} 