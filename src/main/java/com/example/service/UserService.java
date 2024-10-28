package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.SearchVO;
import com.example.Model.UserMapper;
import com.example.Model.userVO;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public userVO getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public List<userVO> searchUsers(SearchVO searchVO) {
        return userMapper.searchUsers(searchVO);
    }
}
