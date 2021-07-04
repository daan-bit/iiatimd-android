package com.example.chauffeursapp;

import android.util.Log;
import android.view.View;

public class VakantieOnclickListener implements View.OnClickListener {

    public int id;
    public AdminVakantieWerknemerActivity avwa;

    public VakantieOnclickListener(int id, AdminVakantieWerknemerActivity avwa){
        this.id = id;
        this.avwa = avwa;
    }

    @Override
    public void onClick(View v) {
        Log.d("Id", String.valueOf(id));
        avwa.toAdminUserVakantiedagen(id);

    }
}
