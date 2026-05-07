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
     -XX:+PrintGCDetails -XX:MetaspaceSize=60m -Xss640k
     -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/heapdump3.hprof
     -XX:SurvivorRatio=8 -XX:+PrintGCDateStamps -Xms1024M -Xmx1024M -Xloggc:log/gc-oom3.log

     jmap -histo:live 32369 将当前的存货对象dump到文件 此时会出发FullGC

    估算GC的频率
    正常情况我们应该根据系统进行一个内存的估算 这个我们可以在测试环境中测试 最开始可以将内存设置的大一些 比如4G这样 当然
    也可以根据业务系统估算来的

    比如从数据库获取一条数据占用128个字节 需要获取1000条数据 那么一次读取到内存的大小就是(128b / 1024kb / 1024m) * 1000 = 0.122m
    那么我们程序可能需要并发读取 比如美妙读取100次 那么内存占用就是0.122m * 100 = 12.2m 如果堆内存设置1G 那么年轻代大小
    大约333m 那么333m * 80% / 12.2m = 21.84s 也就是说程序每分钟两三次YoungGC 这样可以让我们对系统有一个大致的估算
    0.122 * 100 = 12.2m/s  --Eden Area
    1024m * 1/3 * 80% = 273m
    273 / 12.2m = 22.38s  -->  YoungGC每分钟2-3次
     */
    @RequestMapping("/getData")
    public List<People> getProduct() {
        List<People> peopleList = peopleService.getPeopleList();
        return peopleList;
    }

}
