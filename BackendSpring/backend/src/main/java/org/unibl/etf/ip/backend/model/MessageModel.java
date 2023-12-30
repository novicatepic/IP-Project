package org.unibl.etf.ip.backend.model;

public class MessageModel {

    private String text;

    public MessageModel() {}

    public MessageModel(String text) {this.text = text;}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
