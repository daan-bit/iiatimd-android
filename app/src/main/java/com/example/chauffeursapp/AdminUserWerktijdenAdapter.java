package com.example.chauffeursapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdminUserWerktijdenAdapter extends RecyclerView.Adapter< AdminUserWerktijdenAdapter. AdminUserWerktijdenViewHolder> {
    private Werktijden[] werktijden;

    public AdminUserWerktijdenAdapter(Werktijden[] werktijden) {
        this.werktijden = werktijden;

    }

    public static class AdminUserWerktijdenViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewAdminUserWerktijdenBegin;
        public TextView textViewAdminUserWerktijdenEind;


        public AdminUserWerktijdenViewHolder(View v) {
            super(v);
            textViewAdminUserWerktijdenBegin = v.findViewById(R.id.textViewAdminUserWerktijdenBegin);
            textViewAdminUserWerktijdenEind = v.findViewById(R.id.textViewAdminUserWerktijdenEind);
        }
    }

    @NonNull
    @Override
    public AdminUserWerktijdenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminuserwerktijdencard, parent, false);
        AdminUserWerktijdenViewHolder adminUserWerktijdenViewHolder = new AdminUserWerktijdenViewHolder(v);
        return adminUserWerktijdenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUserWerktijdenViewHolder holder, int position) {
        holder.textViewAdminUserWerktijdenBegin.setText("Begonnen: " + werktijden[position].getBegin_shift());
        holder.textViewAdminUserWerktijdenEind.setText("Gestopt: " + werktijden[position].getEinde_shift());
    }


    @Override
    public int getItemCount() {
        return werktijden.length;
    }


}


