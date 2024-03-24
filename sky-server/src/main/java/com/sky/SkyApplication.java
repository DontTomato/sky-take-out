package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableCaching//开发缓存注解
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");

        //刚才的问题是端口被占用了，你这会应该是一直在测试，所以8080端口被占用，修改一下就和哦
        //端口占用的问题，记住启动服务后要关闭服务，不然会造成端口占用
        //这个地方log爆红，是你没有下载lombok插件的原因，不影响运行
        //这会就不报错了
        //再去看数据库
    }
}
