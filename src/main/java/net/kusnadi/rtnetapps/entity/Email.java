package net.kusnadi.rtnetapps.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

/**
 * Created by root on 13/09/17.
 */
@JsonIgnoreProperties
public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
    private Boolean status;

    public Email(String from, String to, String subject, String text, Boolean status) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.status = status;
    }

    public Email(){}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }
}
