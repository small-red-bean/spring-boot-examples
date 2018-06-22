package com.redbean;

import com.alibaba.fastjson.JSON;
import com.redbean.mybatis.SpringBootMybatisApplication;
import com.redbean.mybatis.mapper.UserMenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class SpringBootMybatisApplicationTests {

	@Autowired
	private UserMenuMapper userMenuMapper;


	@Test
	public void contextLoads() {
		System.out.println(JSON.toJSONString(userMenuMapper.getUserMenu()));
	}

}
