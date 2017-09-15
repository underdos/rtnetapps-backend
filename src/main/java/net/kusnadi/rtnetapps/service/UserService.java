package net.kusnadi.rtnetapps.service;

import net.kusnadi.rtnetapps.entity.LoginReq;
import net.kusnadi.rtnetapps.entity.db.User;
import net.kusnadi.rtnetapps.repository.UserRepository;
import net.kusnadi.rtnetapps.utils.Md5Generator;
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
            return false;
        }
        return true;
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
