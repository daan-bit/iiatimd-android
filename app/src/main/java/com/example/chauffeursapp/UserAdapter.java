package com.example.chauffeursapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private User[] users;

    public UserAdapter(User[] users){
        this.users = users;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public UserViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.textViewAdmin);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_geschiedenisadminusercard,parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textView.setText(users[position].getName());
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

}
