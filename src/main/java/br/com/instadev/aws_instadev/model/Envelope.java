package br.com.instadev.aws_instadev.model;

public class Envelope {
    private String eventType;
    private String data;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
