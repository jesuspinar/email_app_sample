package com.jesuspinar.mailsample.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jesuspinar.mailsample.R;
import com.jesuspinar.mailsample.model.Contact;
import com.jesuspinar.mailsample.model.Mail;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailsViewHolder> {
    private final Context context;
//    private final Contact[] contacts;
    private final Mail[] mails;
    private IOnClickListener listener;

    public MailAdapter(Context context, Mail[] mail, IOnClickListener listener) {
        this.context = context;
//        this.contacts = contacts;
        this.mails = mail;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mail,
                viewGroup, false);
        return new MailsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MailsViewHolder mailsViewHolder, int position) {
//        Contact contact = contacts[position];
        Mail mail = mails[position];
        mailsViewHolder.bind(mail);
    }

    @Override
    public int getItemCount() {
        return mails.length;
    }

    public static class MailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivProfilePicture;
        private final TextView tvFullname;
        private final TextView tvSubject;
        private final TextView tvBody;
        private final TextView tvDate;
        private final TextView tvTime;

        private final StringBuilder sb;
        private final IOnClickListener listener;

        public MailsViewHolder(@NonNull View itemView, IOnClickListener listener) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);

            sb = new StringBuilder();
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bind(Mail mail) {
            int img;
//            String s = "R.drawable.c"+contact.getId();
//            ivProfilePicture.setImageResource(String.valueOf(s));
            switch (11){
                case 1: img = R.drawable.c1;break;
                case 2: img = R.drawable.c2;break;
                case 3: img = R.drawable.c3;break;
                case 4: img = R.drawable.c4;break;
                case 5: img = R.drawable.c5;break;
                case 6: img = R.drawable.c6;break;
                case 7: img = R.drawable.c7;break;
                case 8: img = R.drawable.c8;break;
                case 9: img = R.drawable.c9;break;
                case 10: img = R.drawable.c10;break;
                case 11: img = R.drawable.c11;break;
                case 12: img = R.drawable.c12;break;
                case 13: img = R.drawable.c13;break;
                case 14: img = R.drawable.c14;break;
                default: img = R.drawable.c0;
            }
            ivProfilePicture.setImageResource(img);
//            sb.append(contact.getName()).append(" ").append(contact.getFirstSurname()).append(" ")
//                    .append(contact.getSecondSurname());
            tvFullname.setText(mail.getFrom());
            tvSubject.setText(mail.getSubject());
            tvBody.setText(mail.getBody());
            tvDate.setText(mail.getDate());
            tvTime.setText(mail.getTime());


        }

        @Override
        public void onClick(View v) {
//            if(listener != null) {
//                listener.onClick(getAdapterPosition());
//            }
            Toast.makeText(v.getContext(), getClass().getSimpleName()+"no click implementation..", Toast.LENGTH_SHORT).show();
        }
    }
}
