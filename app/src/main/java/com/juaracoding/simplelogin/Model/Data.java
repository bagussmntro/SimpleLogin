package com.juaracoding.simplelogin.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("status")
    private String Status;
    @SerializedName("message")
    private String Message;
    @SerializedName("token")
    private String Token;

    public Data(){

    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void save() {
    }
}
