package com.jesuspinar.mailsample.model;

public class Mail {
    private static int AUTO_ID = 0;
    private final int id;

    private final String from;
    private final String to;
    private final String subject;
    private final String body;
    private final String sentOn;
    private final String date;
    private final String time;
    private boolean readed;
    private boolean deleted;
    private boolean spam;

    public Mail(String from, String to, String subject, String body, String sentOn, boolean readed, boolean deleted, boolean spam) {
        id = ++AUTO_ID;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.readed = readed;
        this.deleted = deleted;
        this.spam = spam;
        this.sentOn = sentOn;
        date = sentOn.substring(0,sentOn.indexOf(" "));
        time = sentOn.substring(sentOn.indexOf(" ")+1);
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getSentOn() {
        return sentOn;
    }

    public boolean isReaded() {
        return readed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isSpam() {
        return spam;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }
}
