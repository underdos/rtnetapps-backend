package net.kusnadi.rtnetapps.service;

import net.kusnadi.rtnetapps.entity.db.Family;
import net.kusnadi.rtnetapps.entity.db.Resident;
import net.kusnadi.rtnetapps.repository.FamilyRepository;
import net.kusnadi.rtnetapps.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 17/09/17.
 */
@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private FamilyRepository familyRepository;

    public boolean saveFamily (Family family){
        try {
            familyRepository.save(family);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean saveResident (List<Resident> residents){
        try{
            residentRepository.save(residents);
        } catch (Exception e){
            return false;
        }

        return true;
    }

}
