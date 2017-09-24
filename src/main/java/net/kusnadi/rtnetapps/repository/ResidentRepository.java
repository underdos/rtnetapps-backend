package net.kusnadi.rtnetapps.repository;

import net.kusnadi.rtnetapps.entity.db.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 17/09/17.
 */
public interface ResidentRepository extends JpaRepository<Resident, Integer>{
}
