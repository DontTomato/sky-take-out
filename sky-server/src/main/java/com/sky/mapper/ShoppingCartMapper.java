package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /*
    * 动态条件查询
    * @param shoping
    * */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time)" +
            "values                 (#{name},  #{image},  #{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /*
    * 根据用户id删除数据
    * @param userId
    * */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteById(Long id);

    /**
     * 将购物车对象批量添加到购物车
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);

}
