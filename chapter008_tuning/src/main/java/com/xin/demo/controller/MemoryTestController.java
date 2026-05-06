package com.xin.demo.controller;

import com.xin.demo.bean.People;
import com.xin.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-05 17:05
 * @github https://github.com/xyf527
 * @copyright
 */

@RestController
public class MemoryTestController {

    @Autowired
    private PeopleService peopleService;

    /*
    案例一 模拟线上环境OOM
     -XX:+PrintGCDetails -XX:MetaspaceSize=60m
     -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/heapdump.hprof
     -XX:+PrintGCDateStamps -Xms30M -Xmx30M -Xloggc:log/gc-oomHeap.log
     */
    @RequestMapping("/add")
    public void addObject() {

        System.err.println("add" + peopleService);

        ArrayList<People> people = new ArrayList<>();

        while (true) {
            people.add(new People());
        }

    }

    /*
    案例二 模拟元空间OOM溢出
     -XX:+PrintGCDetails -XX:MetaspaceSize=60m -XX:MaxMetaspaceSize=60m
     -Xss640k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/heapdumpMeta.hprof
     -XX:SurvivorRatio=8 -XX:+TraceClassLoading -XX:+TraceClassUnloading
     -XX:+PrintGCDateStamps -Xms30M -Xmx30M -Xloggc:log/gc-oomMeta.log
     */
    @RequestMapping("/metaSpaceOom")
    public void metaSpaceOom() {

    ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(People.class);
            // enhancer.setUseCache(false);
            enhancer.setUseCache(true);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("我是加强类 输出print之前的加强方法");
                return methodProxy.invokeSuper(o, objects);
            });
            People people = (People) enhancer.create();
            people.print();
            System.out.println(people.getClass());
            System.out.println("totalClass: " + classLoadingMXBean.getTotalLoadedClassCount());
            System.out.println("activeClass: " + classLoadingMXBean.getLoadedClassCount());
            System.out.println("unloadedClass: " + classLoadingMXBean.getUnloadedClassCount());
        }
    }

    /*
    性能优化案例三 合理配置堆内存

     */
    @RequestMapping("/getData")
    public List<People> getProduct() {
        List<People> peopleList = peopleService.getPeopleList();
        return peopleList;
    }

}
