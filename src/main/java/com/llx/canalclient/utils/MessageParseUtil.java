package com.llx.canalclient.utils;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.duxinglangzi.canal.starter.mode.CanalMessage;

import java.util.stream.Collectors;

/**
 * @Author Leon Lv
 * @Date 2024/10/24 15:54
 * @version 1.0
 * @注释 canal-server 报文解析工具类
 */
public class MessageParseUtil {

    public static void printChange(String method, CanalMessage message) {
        CanalEntry.EventType eventType = message.getEventType();
        CanalEntry.RowData rowData = message.getRowData();


        System.out.println(" >>>>>>>>>>>>>[当前数据库: "+message.getDataBaseName()+" ," +
                "数据库表名: " + message.getTableName() + " , " +
                "方法: " + method );

        if (eventType == CanalEntry.EventType.DELETE) {
            rowData.getBeforeColumnsList().stream().collect(Collectors.toList()).forEach(ele -> {
                System.out.println("[方法: " + method + " ,  delete 语句 ] --->> 字段名: " + ele.getName() + ", 删除的值为: " + ele.getValue());
            });
        }

        if (eventType == CanalEntry.EventType.INSERT) {
            rowData.getAfterColumnsList().stream().collect(Collectors.toList()).forEach(ele -> {
                System.out.println("[方法: " + method + " ,insert 语句 ] --->> 字段名: " + ele.getName() + ", 新增的值为: " + ele.getValue());
            });
        }

        if (eventType == CanalEntry.EventType.UPDATE) {
            for (int i = 0; i < rowData.getAfterColumnsList().size(); i++) {
                CanalEntry.Column afterColumn = rowData.getAfterColumnsList().get(i);
                CanalEntry.Column beforeColumn = rowData.getBeforeColumnsList().get(i);
                System.out.println("[方法: " + method + " , update 语句 ] -->> 字段名," + afterColumn.getName() +
                        " , 是否修改: " + afterColumn.getUpdated() +
                        " , 修改前的值: " + beforeColumn.getValue() +
                        " , 修改后的值: " + afterColumn.getValue());
            }
        }
    }
}
