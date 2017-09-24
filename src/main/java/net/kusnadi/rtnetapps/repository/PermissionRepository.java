package net.kusnadi.rtnetapps.repository;

import net.kusnadi.rtnetapps.entity.db.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by root on 17/09/17.
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query(
            value = "select * from t_mtr_permissions permission right join t_mtr_role_permission role_permission on role_permission.permission_id = permission.id where role_permission.role_id = ?1",
            nativeQuery = true
    )
    public List<Permission> getPermissionList(Integer roleId);
}
