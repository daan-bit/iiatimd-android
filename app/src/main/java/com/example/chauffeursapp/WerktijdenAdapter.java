package com.example.chauffeursapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WerktijdenAdapter extends RecyclerView.Adapter<WerktijdenAdapter.WerktijdenViewHolder>{
    private Werktijden[] werktijdens;

    public WerktijdenAdapter(Werktijden[] werktijdens){
        this.werktijdens = werktijdens;
    }

    public static class WerktijdenViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public WerktijdenViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.textViewUserTijdVan);

        }
    }

    @NonNull
    @Override
    public WerktijdenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_geschiedenisusercard,parent, false);
        WerktijdenViewHolder userViewHolder = new WerktijdenViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WerktijdenViewHolder holder, int position) {
        holder.textView.setText(werktijdens[position].getBegin_shift());
    }

    @Override
    public int getItemCount() {
        return werktijdens.length;
    }

}
