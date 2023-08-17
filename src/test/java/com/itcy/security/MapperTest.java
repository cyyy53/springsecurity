package com.itcy.security;

import com.baomidou.mybatisplus.annotation.TableId;
import com.itcy.security.entity.User;
import com.itcy.security.mapper.MenuMapper;
import com.itcy.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security
 * @Author: cuiYong
 * @CreateTime: 2023-08-11  16:33
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123");
        boolean result = encoder.matches("123",
                "$2a$10$e3NBn3E3fUxlS5BAhwZCyulORD46BNqg4WCpuPXo8DSIxCRHMmhXu");
        System.out.println(result);
        System.out.println("BCryptPasswordEncoder===>" + encode);
    }

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void test1() {
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }
}
