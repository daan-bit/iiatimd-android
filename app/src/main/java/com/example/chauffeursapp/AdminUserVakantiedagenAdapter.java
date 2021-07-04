package com.example.chauffeursapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdminUserVakantiedagenAdapter extends RecyclerView.Adapter< AdminUserVakantiedagenAdapter. AdminUserVakantiedagenViewHolder> {
    private Vakantiedagen[] vakantiedagen;

    public AdminUserVakantiedagenAdapter(Vakantiedagen[] vakantiedagen) {
        this.vakantiedagen = vakantiedagen;

    }

    public static class AdminUserVakantiedagenViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewAdminUserVakantiedagenBegin;
        public TextView textViewAdminUserVakantiedagenEind;


        public AdminUserVakantiedagenViewHolder(View v) {
            super(v);
            textViewAdminUserVakantiedagenBegin = v.findViewById(R.id.textViewAdminUserVakantiedagenBegin);
            textViewAdminUserVakantiedagenEind = v.findViewById(R.id.textViewAdminUserVakantiedagenEind);
        }
    }

    @NonNull
    @Override
    public AdminUserVakantiedagenAdapter.AdminUserVakantiedagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminvakantiedagencard, parent, false);
        AdminUserVakantiedagenAdapter.AdminUserVakantiedagenViewHolder adminUserVakantiedagenViewHolder = new AdminUserVakantiedagenAdapter.AdminUserVakantiedagenViewHolder(v);
        return adminUserVakantiedagenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUserVakantiedagenAdapter.AdminUserVakantiedagenViewHolder holder, int position) {
        holder.textViewAdminUserVakantiedagenBegin.setText("Van: " + vakantiedagen[position].getVakantie_van());
        holder.textViewAdminUserVakantiedagenEind.setText("Tot: " + vakantiedagen[position].getVakantie_tot());
    }


    @Override
    public int getItemCount() {
        return vakantiedagen.length;
    }
}
