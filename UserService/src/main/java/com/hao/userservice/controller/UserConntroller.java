package com.hao.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MuggleLee
 * @date 2019/07/21
 */
@Slf4j
@RestController
public class UserConntroller {

    @GetMapping("/user/current")
    public void getLoginAppUser() {
        System.out.println("Hello !");
//        return AppUserUtils.getLoginAppUser();
    }
//    @Autowired
//    private UserService userService;

    /**
     * 获取当前用户的信息
     * @return
     */
//    @GetMapping("/user/current")
//    public LoginAppUser getLoginAppUser(){
//        return null;
////        return AppUserUtils.getLoginAppUser();
//    }

//    @GetMapping(value = "/users-anon/internal",params = "username")
//    public LoginAppUser findUserByName(String userName){
//        return userService.findByUserName(userName);
//    }
//
//
//    /**
//     * 用户查询
//     */
//    @PreAuthorize("hasAuthority('bak:user:query')")
//    @GetMapping("/users")
//    public Page<AppUser> findUsers(@RequestParam Map<String,Object> params){
//        return userService.findUsers(params);
//    }
//
//    /**
//     * 根据用户id查询用户信息
//     */
//    @PreAuthorize("hasAuthority('bak:user:query')")
//    @GetMapping("/users/{id}")
//    public AppUser findUserById(@PathVariable Long id){
//        return userService.findById(id);
//    }
//
//    /**
//     * 注册用户
//     */
//    @PostMapping("/user-anon/register")
//    public AppUser register(@RequestBody AppUser appUser){
//        userService.addAppUser(appUser);
//        return appUser;
//    }
//
////    @LogAnnotation(module = "修改个人信息")
////    @PutMapping("/user/me")
////    public AppUser updateMe(@RequestBody AppUser appUser){
////        AppUser user = AppUserUtils.getLoginAppUser();
////        appUser.setId(user.getId());
////        userService.updateAppUser(appUser);
////        return appUser;
////    }
//
//    /**
//     * 修改密码
//     */
////    @LogAnnotation(module = "修改密码")
////    @PutMapping(value = "/users/password",params = {"oldPassword","newPassword"})
////    public void updatePassword(String oldPassword,String newPassword){
////        if(StringUtils.isBlank(oldPassword)){
////            throw new IllegalArgumentException("旧密码不能为空");
////        }
////        if(StringUtils.isBlank(newPassword)){
////            throw new IllegalArgumentException("新密码不能为空");
////        }
////
////        AppUser user = AppUserUtils.getLoginAppUser();
////        userService.updatePassword(user.getId(),oldPassword,newPassword);
////    }
//
//    /**
//     * 重置密码
//     */
//    @LogAnnotation(module = "重置密码")
//    @PreAuthorize("hasAuthority('bak:user:password')")
//    @PutMapping(value = "/user/{id}/password",params = "newPassword")
//    public void resetPassowrd(@PathVariable Long id,String newPassword){
//        userService.updatePassword(id,null,newPassword);
//    }
//
//    /**
//     * 后台修改用户信息
//     * @return
//     */
//    @LogAnnotation(module = "修改用户")
//    @PreAuthorize("hasAuthority('bak:user:update')")
//    @PutMapping("/users")
//    public void updateAppUser(@RequestBody AppUser appUser){
//        userService.updateAppUser(appUser);
//    }
//
//    /**
//     * 设置角色
//     */
//    @LogAnnotation(module = "分配角色")
//    @PreAuthorize("hasAuthority('bak:user:query:role:set')")
//    @PostMapping("/user/{id}/roles")
//    public void setRoleToUser(@PathVariable Long id , @RequestBody Set<Long> roleIds){
//        userService.setRoletToUser(id,roleIds);
//    }
//
//    /**
//     * 根据用户ID查询用户角色
//     * @return
//     */
//    @PreAuthorize("hasAnyAuthority('bak:user:query:role:set','bak:user:byuid')")
//    @GetMapping("/users/{id}/roles")
//    public Set<SysRole> findRolesByUserId(@PathVariable Long id){
//        return userService.findRolesByUserId(id);
//    }


//    @LogAnnotation(module = "绑定手机号")
//    @PostMapping(value = "/user/binding-phone")
//    public void bindingPhone(String phone,String key,String code) throws IllegalAccessException {
//        if(StringUtils.isBlank(phone)){
//            throw new IllegalAccessException("手机号不能为空");
//        }
//        if(StringUtils.isBlank(key)){
//            throw new IllegalAccessException("key不能为空");
//        }
//        if(StringUtils.isBlank(code)){
//            throw new IllegalAccessException("code不能为空");
//        }
//
////        LoginAppUser loginAppUser =
//
//
//    }

}
