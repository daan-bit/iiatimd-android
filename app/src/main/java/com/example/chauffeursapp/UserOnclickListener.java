package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UserOnclickListener implements View.OnClickListener {

    public String name;
    public GeschiedenisAdminActivity gaa;

    public UserOnclickListener(String name, GeschiedenisAdminActivity gaa){
        this.name = name;
        this.gaa = gaa;
    }

    @Override
    public void onClick(View v) {
        gaa.toAdminUserWerktijden(name);

    }
}
