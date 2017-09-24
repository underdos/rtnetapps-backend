package net.kusnadi.rtnetapps.controller;

import net.kusnadi.rtnetapps.entity.ServiceResponse;
import net.kusnadi.rtnetapps.entity.db.Family;
import net.kusnadi.rtnetapps.entity.db.Resident;
import net.kusnadi.rtnetapps.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by root on 17/09/17.
 */
@RestController
@RequestMapping("/family")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponse saveFamily(@RequestBody Family family){
        family.setCreatedBy("system");
        Date date = new Date();
        family.setCreatedOn(new Timestamp(date.getTime()));

        if(residentService.saveFamily(family)){
            return new ServiceResponse(0,"success", family);
        } else {
            return new ServiceResponse(10,"failed", null);
        }

    }

    @RequestMapping(value = "/residents",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponse saveFamily(@RequestBody List<Resident> residents){
        Date date = new Date();
        for (int x = 0; x < residents.size(); x++){
            residents.get(x).setCreatedBy("system");
            residents.get(x).setCreatedOn(new Timestamp(date.getTime()));
        }

        if(residentService.saveResident(residents)){
            return new ServiceResponse(0,"success", residents);
        } else {
            return new ServiceResponse(10,"failed", null);
        }
    }
}
