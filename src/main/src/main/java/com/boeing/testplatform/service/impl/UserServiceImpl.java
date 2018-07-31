package com.boeing.testplatform.service.impl;

import com.boeing.testplatform.bean.User;
import com.boeing.testplatform.mapper.UserMapper;
import com.boeing.testplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;


    @Override
    public List<HashMap> findByName() {
        return mapper.findOne();
    }
}
