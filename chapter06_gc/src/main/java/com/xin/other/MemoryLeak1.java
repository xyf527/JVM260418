package main.java.com.xin.other;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-03 10:47
 * @github https://github.com/xyf527
 * @copyright
 */

public class MemoryLeak1 {

    static Map wMap = new WeakHashMap();
    static Map map = new HashMap();

    public static void main(String[] args) {
        init();
        testWeakHashMap();
        testHashMap();
    }

    public static void init() {
        String ref1 = new String("weakMap1");
        String ref2 = new String("weakMap2");

        String ref3 = new String("map3");
        String ref4 = new String("map4");

        wMap.put(ref1, "cacheObject1");
        wMap.put(ref2, "cacheObject2");

        map.put(ref3, "cacheObject3");
        map.put(ref4, "cacheObject4");

        System.out.println("String引用ref1 ref2 ref3 ref4 消失");
    }

    public static void testWeakHashMap() {
        System.out.println("WeakHashMap GC之前");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }

        try {
            System.gc();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("WeakHashMap GC之后");

        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
    }


    public static void testHashMap() {
        System.out.println("HashMap GC之前");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }

        try {
            System.gc();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("HashMap GC之后");

        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
    }

}
