package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface DishServise {


    /*
    新增菜品和对应的口味
    *@param disDTO
    * */

    public void saveWithFlavor(DishDTO dishDTO);

    /*
    菜品分页查询
    * */

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /*
     * 菜品批量删除
     * param ids
     * @return
     * */
    void deleteBatch(List<Long> ids);


    /*
     * 根据id来查询菜品和对应的口味数据
     * param id
     * @return
     * */
    DishVO getByIdWithFlavor(Long id);

    /*
     * 根据id来修改菜品基本信息和对应的口味数据
     * param id
     * @return
     * */
    void updateWithFlavor(DishDTO dishDTO);


    /*菜品起售停售
    *
    * @param status
    * @param id
    * */
    void startOrStop(Integer status,Long id);

    /*
    *
    * 根据分类id查询结果
    * @param categoryId
    * @return
    * */
    List<Dish> list(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
