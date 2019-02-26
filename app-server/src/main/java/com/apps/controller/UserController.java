package com.apps.controller;import com.apps.entity.UserDao;import com.apps.service.UserService;import com.apps.util.DateUtility;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.propertyeditors.CustomDateEditor;import org.springframework.data.domain.Page;import org.springframework.web.bind.ServletRequestDataBinder;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.InitBinder;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import javax.servlet.http.HttpServletRequest;import java.text.SimpleDateFormat;import java.util.Date;import java.util.List;import java.util.TimeZone;/** * @author SimonYang * @date 2019/2/10 */@RestController@RequestMapping(value = "/user")public class UserController {    @Autowired    private UserService userService;    @Autowired    private UserDao userInfo;    @InitBinder    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {        /*TimeZone時區，解決差8小時的問題*/        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtility.FORMAT_DATETIME);        dateFormat.setTimeZone(TimeZone.getTimeZone(DateUtility.UTC));        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    }    @GetMapping(value = "/getUserList")    public List<UserDao> getUserList() {        return userService.getAllList();    }    @GetMapping(value = "/getUser")    public UserDao getUserInfoByName(@RequestParam("name") String name) {        return userService.getUserByName(name).orElse(null);    }    @GetMapping(value = "/getCurrentUserList")    public List<UserDao> getCurrentUserList() {        return userService.getCurrentUserList();    }    @GetMapping(value = "/getPageUserList")    public Page<UserDao> getPageUserList() {        return userService.getPageUserList();    }    @PutMapping(value = "/addUser")    public UserDao addUser(UserDao userInfo) {        return userService.save(userInfo, "TEST", "127.0.0.1").orElse(null);    }    @PostMapping(value = "/updateUser")    public UserDao updateUser(UserDao userInfo) {        return userService.save(userInfo, "TEST", "127.0.0.1").orElse(null);    }    @PostMapping(value = "/enableUser")    public UserDao enableUser(UserDao userInfo) {        return userService.enable(userInfo.getId(), "TEST", "127.0.0.1").orElse(null);    }    @PostMapping(value = "/removeUser")    public UserDao removeUser(UserDao userInfo) {        return userService.remove(userInfo.getId(), "TEST", "127.0.0.1").orElse(null);    }    @PostMapping(value = "/deleteUser")    public void deleteUserInfo(@RequestParam("id") String id) {        userService.delete(id);    }}