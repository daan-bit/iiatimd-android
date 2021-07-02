package com.example.chauffeursapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class VakantieAdapter extends RecyclerView.Adapter<VakantieAdapter.VakantieViewHolder>{
    private Vakantiedagen[] vakantiedagen;

    public VakantieAdapter(Vakantiedagen[] vakantiedagen){
        this.vakantiedagen = vakantiedagen;
    }

    public static class VakantieViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public VakantieViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.textViewVakantieAdmin);
        }
    }

    @NonNull
    @Override
    public VakantieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adminvakantiedagencard,parent, false);
        VakantieViewHolder vakantieViewHolder = new VakantieViewHolder(v);
        return vakantieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VakantieViewHolder holder, int position) {
        holder.textView.setText(vakantiedagen[position].getVakantie_van());
    }

    @Override
    public int getItemCount() {
        return vakantiedagen.length;
    }

}

