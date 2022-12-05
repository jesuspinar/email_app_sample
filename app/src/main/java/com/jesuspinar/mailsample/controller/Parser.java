package com.jesuspinar.mailsample.controller;

import android.content.Context;
import android.util.Log;

import com.jesuspinar.mailsample.R;
import com.jesuspinar.mailsample.model.Contact;
import com.jesuspinar.mailsample.model.Mail;
import com.jesuspinar.mailsample.model.User;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Parser {

    private User user;
    private Contact[] contacts;
    private Mail[] mails;
    private final InputStream contactsFile;

    public Parser(Context c) {
        this.contactsFile = c.getResources().openRawResource(R.raw.correos);
    }

    public boolean parse() {
        /* Parsed controla si se han podido parsear los datos. Inicialmente a false */
        boolean parsed = false;
        String json = null;
        contacts = null;
        mails = null;
        try {
            int size = contactsFile.available();
            byte[] buffer = new byte[size];
            contactsFile.read(buffer);
            contactsFile.close();
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                switch (i) {
                    case 0:
                        JSONObject jsonUser = jsonArray.getJSONObject(i);
                        user = new User(jsonUser.getString("name"), jsonUser.getString("firstSurname"),jsonUser.getString("email"));
                        break;

                    case 1: {
                        JSONObject jsonContacs = jsonArray.getJSONObject(i);
                        JSONArray contactsJsonArray = jsonContacs.getJSONArray("contacts");
                        contacts = new Contact[contactsJsonArray.length()];
                        for (int j = 0; j <  contactsJsonArray.length(); j++) {
                            JSONObject contact = jsonArray.getJSONObject(j);
                            String name = contact.getString("name");
                            String firstSurname = contact.getString("firstSurname");
                            String secondSurname = contact.getString("secondSurname");
                            String photo = contact.getString("photo");
                            String birth = contact.getString("birth");
                            String email = contact.getString("email");
                            String phone1 = contact.getString("phone1");
                            String phone2 = contact.getString("phone2");
                            String address = contact.getString("address");
                            contacts[j] = new Contact(name, firstSurname, secondSurname, photo, birth, email, phone1, phone2, address);
                        }

                    } break;

                    case 2: {
                        JSONObject jsonMails = jsonArray.getJSONObject(i);
                        JSONArray mailsJsonArray = jsonMails.getJSONArray("mails");
                        mails = new Mail[mailsJsonArray.length()];
                        for (int j = 0; j <  mailsJsonArray.length(); j++) {
                            JSONObject mail = jsonArray.getJSONObject(j);

                            String from = mail.getString("from");
                            String to = mail.getString("to");
                            String subject = mail.getString("subject");
                            String body = mail.getString("body");
                            String sentOn = mail.getString("sentOn");

                            boolean readed = mail.getBoolean("readed");
                            boolean deleted = mail.getBoolean("deleted");
                            boolean spam = mail.getBoolean("spam");
                            mails[j] = new Mail(from, to, subject ,body, sentOn, readed,deleted, spam);
                        }
                    }
                    break;
                    default: throw new RuntimeException("Error while parsing, see "+ this.getClass());
                }


            }
            parsed = true;
        } catch (Exception e) {
            Log.e("CountryParser", "Unknown Exception: " + e.getLocalizedMessage());
        }
        return parsed;
    }

    public User getUser() {
        return user;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public Mail[] getMails() {
        return mails;
    }
}
