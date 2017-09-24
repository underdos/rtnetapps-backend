package net.kusnadi.rtnetapps.controller;

import net.kusnadi.rtnetapps.entity.LoginReq;
import net.kusnadi.rtnetapps.entity.LoginRes;
import net.kusnadi.rtnetapps.entity.RegistReq;
import net.kusnadi.rtnetapps.entity.ServiceResponse;
import net.kusnadi.rtnetapps.entity.db.Permission;
import net.kusnadi.rtnetapps.entity.db.Role;
import net.kusnadi.rtnetapps.entity.db.User;
import net.kusnadi.rtnetapps.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
    public ResponseEntity getUsers(){
        List<User> users = userService.getUsers();
        ServiceResponse sr = new ServiceResponse(0, "success", users);
        return new ResponseEntity(sr, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginReq loginReq){

        User user = userService.getUser(loginReq.getUsername());
        Role role = userService.getRoleByUserId(user.getId());
        List<Permission> permissions = userService.getPermissionList(role.getId());
        LoginRes loginRes = new LoginRes();
        BeanUtils.copyProperties(user, loginRes);
        loginRes.setRole(role);
        loginRes.setPermissions(permissions);
        System.out.println(user.toString()+"\n");
        System.out.println(loginRes.toString()+"\n");
        System.out.println(loginReq.toString());
        if (userService.login(loginReq)){
            ServiceResponse sr = new ServiceResponse(0,"success", loginRes);
            return new ResponseEntity(sr, HttpStatus.OK);
        } else {
            ServiceResponse sr = new ServiceResponse(10, "failed", null);
            return new ResponseEntity(sr, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody RegistReq registReq){
        User user = new User();
        BeanUtils.copyProperties(registReq, user);
        user.setCreatedBy("system");
        Date date = new Date();
        user.setCreatedOn(new Timestamp(date.getTime()));
        System.out.println(user.toString());

        if (userService.save(user)){
            ServiceResponse sr = new ServiceResponse(0,"success", user);
            return new ResponseEntity(sr, HttpStatus.OK);
        } else {
            ServiceResponse sr = new ServiceResponse(10, "failed", null);
            return new ResponseEntity(sr, HttpStatus.OK);
        }
    }


    // TEST ONLY
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    public List<Permission> getListPermission(@PathVariable("roleId") Integer roleId) {
        return userService.getPermissionList(roleId);
    }

}
