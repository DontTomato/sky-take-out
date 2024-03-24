package com.sky.controller.admin;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishServise;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/*
* 菜品管理
* */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishServise dishServise;
    @Autowired
    private RedisTemplate redisTemplate;
    /*
    * 新增菜品
    *
    * @param disDTO
    * @return*/
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品为:{}",dishDTO);

        dishServise.saveWithFlavor(dishDTO);
        //清除缓存数据
        String key = "dish_" +dishDTO.getCategoryId();
        redisTemplate.delete(key);
        return Result.success();
    }
    /*
    * 菜品分页查询
    * */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishServise.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
    * 菜品批量删除
    * param ids
    * @return
    * */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids){
        dishServise.deleteBatch(ids);
        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        cleanCache("dish_*");
        return Result.success();

    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        DishVO dishVO = dishServise.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /*
     * 根据id来查询菜品和对应的口味数据
     * param dishDTO
     * @return
     * */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        dishServise.updateWithFlavor(dishDTO);

        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        cleanCache("dish_*");
        return Result.success();
    }

    /*
    * 菜品起售停售
    *
    * @param status
    * @param id
    * @return
    * */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        dishServise.startOrStop(status,id);
        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        cleanCache("dish_*");
        return Result.success();
    }

    /*
    * 根据分类id查询菜品
    *
    * @param categoryId
    * @return
    * */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishServise.list(categoryId);
        return Result.success(list);
    }

    /*
    * 清理缓存数据
    *
    * @param pattern
    * */
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
