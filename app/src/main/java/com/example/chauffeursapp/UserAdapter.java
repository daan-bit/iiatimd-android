package com.example.chauffeursapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private User[] users;
    GeschiedenisAdminActivity gaa;

    public UserAdapter(User[] users, GeschiedenisAdminActivity gaa) {
        this.users = users;
        this.gaa = gaa;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNaam;


        public UserViewHolder(View v) {
            super(v);
            textViewNaam = v.findViewById(R.id.textViewUser);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_geschiedenisadminusercard, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textViewNaam.setText(users[position].getName());

        holder.textViewNaam.setOnClickListener(new UserOnclickListener(users[position].getUuid(), gaa));
    }


    @Override
    public int getItemCount() {
        return users.length;
    }


}
