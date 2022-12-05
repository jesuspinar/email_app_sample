package com.jesuspinar.mailsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jesuspinar.mailsample.R;
import com.jesuspinar.mailsample.controller.IOnClickListener;
import com.jesuspinar.mailsample.controller.MailAdapter;
import com.jesuspinar.mailsample.model.Contact;
import com.jesuspinar.mailsample.model.Mail;

public class Emailed extends Fragment {

//    private Contact[] contacts;
    private Mail[] mails;
    private IOnClickListener clickListener;

    public interface IOnAttachListener{
//        Contact[] getContacts();
        Mail[] getMails();
    }

    public Emailed() {
        super(R.layout.fragment_emailed);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emailed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MailAdapter adapter = new MailAdapter(getContext(), mails, clickListener);

        RecyclerView recyclerView = view.findViewById(R.id.recViewEmailed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (IOnClickListener) context;
        IOnAttachListener attachListener = (IOnAttachListener) context;
//        contacts = attachListener.getContacts();
        mails = attachListener.getMails();
    }
}
