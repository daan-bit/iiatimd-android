package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UserOnclickListener implements View.OnClickListener {

    public int id;
    public GeschiedenisAdminActivity gaa;

    public UserOnclickListener(int id, GeschiedenisAdminActivity gaa){
        this.id = id;
        this.gaa = gaa;
    }

    @Override
    public void onClick(View v) {
        gaa.toAdminUserWerktijden(id);

    }
}
