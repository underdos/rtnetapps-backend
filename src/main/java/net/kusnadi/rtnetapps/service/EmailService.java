package net.kusnadi.rtnetapps.service;

import net.kusnadi.rtnetapps.entity.Email;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by root on 18/09/17.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    private ClassLoader classLoader;

    public EmailService(){
        this.classLoader = getClass().getClassLoader();
    }

    public void sendSimpleMail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        javaMailSender.send(message);

        System.out.println(email.toString());

//        return new ResponseEntity(email, HttpStatus.);
    }
}
