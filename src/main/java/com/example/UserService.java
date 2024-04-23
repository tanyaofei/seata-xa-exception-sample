package com.example;

/**
 * @author tanyaofei
 * @since 2024/4/18
 **/
public interface UserService {

   User createUser();

   void updateUser(Long id);

   User getUser(Long id);

}
