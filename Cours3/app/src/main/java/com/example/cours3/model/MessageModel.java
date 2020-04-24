package com.example.cours3.model;

public class MessageModel {
    private String message;
    private String sender;
    private Boolean read;

    public MessageModel() {
    }

    public MessageModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public MessageModel(String message, String sender, Boolean read) {
        this.message = message;
        this.sender = sender;
        this.read = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
