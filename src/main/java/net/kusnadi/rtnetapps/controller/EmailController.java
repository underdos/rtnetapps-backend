package net.kusnadi.rtnetapps.controller;

import net.kusnadi.rtnetapps.entity.Email;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 13/09/17.
 */

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    private ClassLoader classLoader;

    public EmailController(){
        this.classLoader = getClass().getClassLoader();
    }

    @RequestMapping("version")
    @ResponseStatus(HttpStatus.OK)
    public String version() {
        return "[OK] Welcome to withdraw Restful version 1.0";
    }

    @RequestMapping(value = "send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Email> sendSimpleMail(@RequestBody Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        javaMailSender.send(message);
        email.setStatus(true);

        System.out.println(email.toString());

        return new ResponseEntity(email, HttpStatus.OK);
    }

    @RequestMapping(value = "attachments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Email> attachments(@RequestBody Email email) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText("<html><body><img src=\"cid:banner\" >" + email.getText() + "</body></html>", true);

        FileSystemResource file = new FileSystemResource(new File(classLoader.getResource("assets/banner.png").getFile()));
        mimeMessageHelper.addInline("banner", file);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(classLoader.getResource("assets/attachment.png").getFile()));
        mimeMessageHelper.addAttachment("attachment.png", fileSystemResource);

        javaMailSender.send(mimeMessage);
        email.setStatus(true);

        return new ResponseEntity<Email>(email, HttpStatus.OK);
    }

    @RequestMapping(value = "template", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Email> template(@RequestBody Email email) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", email.getSubject());
        model.put("body", email.getText());
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/email.vm", "UTF-8", model);

        System.out.println(text);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);

        email.setStatus(true);

        return new ResponseEntity<Email>(email, HttpStatus.OK);
    }


}
