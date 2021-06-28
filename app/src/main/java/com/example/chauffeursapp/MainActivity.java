package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, pass;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        login();
    }


    //inloggen
    public void login() {
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        final JSONObject obj = new JSONObject();
        try {
            obj.put("email", email.getText().toString());
            obj.put("password", pass.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, APICalls.LOGIN, obj,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        SharedPreferences prefs;
                        SharedPreferences.Editor edit;
                        prefs = MainActivity.this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
                        edit = prefs.edit();
                        try {
                            String saveToken = response.getString("token");
                            String saveRol = response.getString("rol");
                            edit.putString("token", saveToken);
                            edit.putString("rol", saveRol);
                            Log.d("inloggentoken", saveToken);
                            Log.d("inloggenToken", saveRol);
                            edit.commit();

                            //hier verkrijgen we de rol van de gebruiker
                            getRol();
                            //rol is werknemer?
                            if(getRol().equals("werknemer")) {
                                //hier zetten we de dashboard van werknemer
                                Toast.makeText(MainActivity.this, "Werknemer", Toast.LENGTH_SHORT).show();
                                toTimerScreen();
                            } else {
                                //hier gebruiken we de dashboard van de user
                                //anders tonen we ander dashboard scherm
                                toDashboardAdmin();
                                Toast.makeText(MainActivity.this, "Werkgever", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(MainActivity.this, "Ingelogd", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(MainActivity.this, "Vekeerde e-mail of wachtwoord. Probeer opnieuw", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                /*params.put("email", email);
                params.put("password", pass);*/
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);

    }

    //token verkrijgen
    private String getToken() {
        SharedPreferences prefs = this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        String token = prefs.getString("token", "");
        return token;

    }

    //rol verkrijgen
    private String getRol() {
        SharedPreferences prefs = this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        String rol = prefs.getString("rol", "");
        return rol;
    }


    public void toTimerScreen() {
        getToken();
        Bundle bundleForTimerScreen = new Bundle();
        String name = "Alex";
        String token = getToken();
        Log.d("to timer", "went to timer");
        bundleForTimerScreen.putString("name", name);
        bundleForTimerScreen.putString("token", token);
        Intent toTimerScreenIntent = new Intent(this, ActivityTimer.class);
        startActivity(toTimerScreenIntent);
    }

    public void toDashboardAdmin(){
        Bundle bundleForDashboardScreen = new Bundle();
        String name = "Daniël";
        bundleForDashboardScreen.putString("name", name);
        Log.d("onClickTest", "Hallo ik ben geklikt");
        Intent toAdminDashboard = new Intent(this, DashboardAdminActivity.class);
        toAdminDashboard.putExtras(bundleForDashboardScreen);
        startActivity(toAdminDashboard);
    }



}

