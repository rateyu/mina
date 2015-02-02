package dbcp;

//import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ym on 2014/8/11.
 */
public class Hello {

    public static final Map MAP = new HashMap();

    public static void main(String[] args) {
        System.out.println("args = [ hello world]");
        Map map = new HashMap();
        map.put("aaa", "bbb");
        long start = System.nanoTime();
        
        map.get("aaa");
        System.out.println(System.nanoTime()-start);
        System.out.println("come back");

        TreeMap<String, String> tree = new TreeMap<String,String>();
        tree.size();
    }


}
