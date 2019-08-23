//package com.hao.userservice.Config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * @author Muggle Lee
// * @Date: 2019/7/31 10:14
// */
//@Component
//@Slf4j
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("用户的用户名: {}", username);
//        // TODO 根据用户名，查找到对应的密码，与权限
//
//        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
//        User user = new User(username, passwordEncoder.encode("123456"),
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;
//    }
//}
