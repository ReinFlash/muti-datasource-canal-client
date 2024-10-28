package com.xyt.canalclient.consumer.demo;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.duxinglangzi.canal.starter.annotation.CanalInsertListener;
import com.duxinglangzi.canal.starter.annotation.CanalListener;
import com.duxinglangzi.canal.starter.annotation.CanalUpdateListener;
import com.duxinglangzi.canal.starter.annotation.EnableCanalListener;
import com.duxinglangzi.canal.starter.mode.CanalMessage;
import com.xyt.canalclient.consumer.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.xyt.canalclient.utils.MessageParseUtil.printChange;

/**
 * @Author Leon Lv
 * @Date 2024/10/24 15:50
 * @version 1.0
 * @注释 示例消费者
 */

/* 必须在类上 使用 EnableCanalListener 注解才能开启 canal listener */
@EnableCanalListener
@Component
public class DemoConsumer {

    @Autowired
    DemoMapper demoMapper;

    /**
     * 必须在类上 使用 EnableCanalListener 注解才能开启 canal listener
     *
     * 目前 Listener 方法的参数必须为 com.duxinglangzi.canal.starter.mode.CanalMessage
     * 程序在启动过程中会做检查
     */

    /**
     * 监控更新操作
     * 支持动态参数配置，配置项需在 yml 或 properties 进行配置
     * 目标是 ${prod.example} 的  ${prod.database} 库  users表
     */
    @CanalUpdateListener(destination = "${prod.example}", database = "${prod.database}", table = {"users"})
    public void listenerExampleBooksUsers(CanalMessage message) {
        printChange("listenerExampleBooksUsers", message);

        if(message.getEventType().equals(CanalEntry.EventType.UPDATE)){
            /* 进行数据源1的相关操作 */
            demoMapper.selectBook(1);
        }else {
            /* 进行数据源2的相关操作 */
            demoMapper.selectMallDeposit(1L);
        }
    }

    /**
     * 监控更新操作 ，目标是 example的  books库  users表
     */
    @CanalInsertListener(destination = "example", database = "books", table = {"users"})
    public void listenerExampleBooksUser(CanalMessage message) {
        printChange("listenerExampleBooksUsers", message);
    }

    /**
     * 监控更新操作 ，目标是 example的  books库  books表
     */
    @CanalUpdateListener(destination = "example", database = "books", table = {"books"})
    public void listenerExampleBooksBooks(CanalMessage message) {
        printChange("listenerExampleBooksBooks", message);
    }

    /**
     * 监控更新操作 ，目标是 example的  books库的所有表
     */
    @CanalListener(destination = "example", database = "books", eventType = CanalEntry.EventType.UPDATE)
    public void listenerExampleBooksAll(CanalMessage message) {
        printChange("listenerExampleBooksAll", message);
    }

    /**
     * 监控更新操作 ，目标是 example的  所有库的所有表
     */
    @CanalListener(destination = "example", eventType = CanalEntry.EventType.UPDATE)
    public void listenerExampleAll(CanalMessage message) {
        printChange("listenerExampleAll", message);
    }

    /**
     * 监控更新、删除、新增操作 ，所有配置的目标下的所有库的所有表
     */
    @CanalListener(eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void listenerAllDml(CanalMessage message) {
        printChange("listenerAllDml", message);
    }
}
