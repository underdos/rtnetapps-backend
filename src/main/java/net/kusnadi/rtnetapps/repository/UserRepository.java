package net.kusnadi.rtnetapps.repository;

import net.kusnadi.rtnetapps.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by root on 12/09/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = "select * from t_mtr_users",
            nativeQuery = true
    )
    public List<User> getUsers();

    @Query(
            value = "select * from t_mtr_users where username = ?1",
            nativeQuery = true
    )
    public User getUser(String username);

    @Query(
            value = "select * from t_mtr_users where username = ?1 and password = ?2",
            nativeQuery = true
    )
    public User login(String username, String password);
}
