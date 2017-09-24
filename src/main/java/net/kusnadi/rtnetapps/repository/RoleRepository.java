package net.kusnadi.rtnetapps.repository;

import net.kusnadi.rtnetapps.entity.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by root on 17/09/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            value = "select * from t_mtr_roles roles right join t_mtr_user_role user_role on user_role.role_id = roles.id where user_role.user_id = ?1",
            nativeQuery = true
    )
    public Role getRoleByUserId(Integer UserId);
}
