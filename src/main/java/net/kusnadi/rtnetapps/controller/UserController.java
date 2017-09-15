package net.kusnadi.rtnetapps.controller;

import net.kusnadi.rtnetapps.entity.LoginReq;
import net.kusnadi.rtnetapps.entity.RegistReq;
import net.kusnadi.rtnetapps.entity.ServiceResponse;
import net.kusnadi.rtnetapps.entity.db.User;
import net.kusnadi.rtnetapps.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 12/09/17.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ServiceResponse getUsers(){
        List<User> users = userService.getUsers();
        return new ServiceResponse(0, "success", users);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponse login(@RequestBody LoginReq loginReq){
        System.out.println(loginReq.toString());
        if (userService.login(loginReq)){
            return new ServiceResponse(0,"success", userService.getUser(loginReq.getUsername()));
        } else {
            return new ServiceResponse(10, "failed", null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponse save(@RequestBody RegistReq registReq){
        User user = new User();
        BeanUtils.copyProperties(registReq, user);
        user.setCreatedBy("system");
        Date date = new Date();
        user.setCreatedOn(new Timestamp(date.getTime()));
        System.out.println(user.toString());
        if (userService.save(user)){
            return new ServiceResponse(0,"success", user);
        } else {
            return new ServiceResponse(10, "failed", null);
        }
    }
}
