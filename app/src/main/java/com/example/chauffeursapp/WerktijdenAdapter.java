package com.example.chauffeursapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WerktijdenAdapter extends RecyclerView.Adapter<WerktijdenAdapter.WerktijdenViewHolder> {

    private Werktijden[] werktijden;

    public WerktijdenAdapter(Werktijden[] werktijden) {
        this.werktijden = werktijden;
    }

    public static class WerktijdenViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public WerktijdenViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textViewWerknemer);
        }
    }

    @NonNull
    @Override
    public WerktijdenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_geschiedenisadminusercard, parent, false);
        WerktijdenViewHolder werktijdenViewHolder = new WerktijdenViewHolder(v);
        return werktijdenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WerktijdenViewHolder holder, int position) {
        holder.textView.setText(werktijden[position].getId());
    }

    @Override
    public int getItemCount() {
        return werktijden.length;
    }
}



