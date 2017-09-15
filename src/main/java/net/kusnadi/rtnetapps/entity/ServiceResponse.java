package net.kusnadi.rtnetapps.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by root on 12/09/17.
 */
@JsonIgnoreProperties
public class ServiceResponse {
    private Integer status;
    private String message;
    private Object response;

    public ServiceResponse(Integer status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public ServiceResponse(){}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}
