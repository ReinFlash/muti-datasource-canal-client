package com.xyt.canalclient.consumer.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.Map;

/**
 * @Author Leon Lv
 * @Date 2024/10/25 14:12
 * @version 1.0
 * @注释
 */
public interface DemoMapper {

    @DS(value = "default")
    Map<String,Object> selectMallDeposit(Long id);

    @DS(value = "targetOne")
    Map<String,Object> selectBook(Integer id);
}
