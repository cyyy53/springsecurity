package com.itcy.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itcy.security.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 176
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
}
