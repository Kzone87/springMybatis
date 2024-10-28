package com.example.Model;

import java.util.List;

public interface UserMapper {
	
    userVO getUserById(int id);
    
    void insertUser(userVO user);
    
    List<userVO> searchUsers(SearchVO searchVO);

}
