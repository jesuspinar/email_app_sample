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


            JSONObject jsonUserData = jsonArray.getJSONObject(0);
            JSONArray jsonMailsData = jsonUserData.getJSONArray("mails");
            JSONArray jsonContactsData = jsonUserData.getJSONArray("contacts");
            jsonUserData = jsonUserData.getJSONObject("myAccount");

            // USER DATA
            String userName = jsonUserData.getString("name");
            String userFirstSurname = jsonUserData.getString("firstSurname");
            String userEmail = jsonUserData.getString("email");
            user = new User(userName, userFirstSurname, userEmail);

            // USER CONTACTS
            contacts = new Contact[jsonContactsData.length()];
            for (int j = 0; j < jsonContactsData.length(); j++) {
                JSONObject contact = jsonContactsData.getJSONObject(j);
                String name = contact.getString("name");
                String firstSurname = contact.getString("firstSurname");
                String secondSurname = contact.getString("secondSurname");
                int photo = contact.getInt("foto");
                String birth = contact.getString("birth");
                String email = contact.getString("email");
                String phone1 = contact.getString("phone1");
                String phone2 = contact.getString("phone2");
                String address = contact.getString("address");
                contacts[j] = new Contact(name, firstSurname, secondSurname, String.valueOf(photo),
                        birth, email, phone1, phone2, address);
            }
            // USER MAILS
            mails = new Mail[jsonMailsData.length()];
            for (int j = 0; j < jsonMailsData.length(); j++) {
                JSONObject mail = jsonMailsData.getJSONObject(j);

                String from = mail.getString("from");
                String to = mail.getString("to");
                String subject = mail.getString("subject");
                String body = mail.getString("body");
                String sentOn = mail.getString("sentOn");

                boolean readed = mail.getBoolean("readed");
                boolean deleted = mail.getBoolean("deleted");
                boolean spam = mail.getBoolean("spam");
                mails[j] = new Mail(from, to, subject, body, sentOn, readed, deleted, spam);
            }
            parsed = true;
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Unknown Exception: " + e.getLocalizedMessage());
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
        return this.mails;
    }
}
