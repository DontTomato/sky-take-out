package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Map;

@Mapper
public interface UserMapper {


    /*
    * 根据openid来查询用户
    * @param openid
    * @retunr
    * */
    User getByOpenid(String openid);


    /*
     * 插入数据
     * @param user
     *
     * */
    void insert(User user);



    User getById(Long userId);

    /**
     * 根据动态条件统计用户数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
