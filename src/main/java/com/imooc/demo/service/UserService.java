package com.imooc.demo.service;

import com.imooc.demo.domain.User;
import com.imooc.demo.dto.UserDto;
import com.imooc.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface UserService {

    UserDto doLogin(UserDto dto);


    /**
     * @return
     */
    void list();
}
