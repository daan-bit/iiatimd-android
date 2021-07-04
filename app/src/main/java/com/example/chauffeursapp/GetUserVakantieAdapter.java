package com.example.chauffeursapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GetUserVakantieAdapter extends RecyclerView.Adapter<GetUserVakantieAdapter.UserVakantieViewHolder>{
    private User[] users;

    public GetUserVakantieAdapter(User[] users){
        this.users = users;
    }

    public static class UserVakantieViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public UserVakantieViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.textViewVakantieUserAdmin);
        }
    }

    @NonNull
    @Override
    public UserVakantieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminvakantiedagenusercard,parent, false);
        UserVakantieViewHolder vakantieViewHolder = new UserVakantieViewHolder(v);
        return vakantieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserVakantieViewHolder holder, int position) {
        holder.textView.setText(users[position].getName());
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

}

