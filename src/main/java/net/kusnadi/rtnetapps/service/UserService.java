package net.kusnadi.rtnetapps.service;

import net.kusnadi.rtnetapps.entity.LoginReq;
import net.kusnadi.rtnetapps.entity.db.Permission;
import net.kusnadi.rtnetapps.entity.db.Role;
import net.kusnadi.rtnetapps.entity.db.User;
import net.kusnadi.rtnetapps.repository.PermissionRepository;
import net.kusnadi.rtnetapps.repository.RoleRepository;
import net.kusnadi.rtnetapps.repository.UserRepository;
import net.kusnadi.rtnetapps.utils.Md5Generator;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 12/09/17.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public List<User> getUsers(){
        return userRepository.getUsers();
    }


    public User getUser(String username){
        return userRepository.getUser(username);
    }

    public boolean save(User user){
        try{
            user.setPassword(Md5Generator.create(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e){
            System.out.println("TEST "+e.getMessage() +" \n"+e.toString());
//            throw new
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Role getRoleByUserId(Integer id){
        return roleRepository.getRoleByUserId(id);
    }

    public List<Permission> getPermissionList(Integer roleId){
        return permissionRepository.getPermissionList(roleId);
    }

    public boolean login(LoginReq loginReq){
        String username = loginReq.getUsername();
        String passwordHash = Md5Generator.create(loginReq.getPassword());

        try {
            User user = userRepository.login(username, passwordHash);

            if (username.equals(user.getUsername()) && user.getActive()){
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException e){
            return false;
        }

    }
}
