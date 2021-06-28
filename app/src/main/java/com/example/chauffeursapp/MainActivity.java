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
                            String user_name = response.getString("naam");
                            int user_id = response.getInt("user_id");
                            String email = response.getString("email");

                            edit.putString("token", saveToken);
                            edit.putString("rol", saveRol);
                            edit.putString("user_name", user_name);
                            edit.putInt("user_id", user_id);
                            edit.putString("email", email);

                            Log.d("inloggentoken", saveToken);
                            Log.d("inloggenToken", saveRol);
                            Log.d("inloggennnaam", user_name);
                            edit.commit();

                            //hier verkrijgen we de rol van de gebruiker
                            getRol();
                            //rol is werknemer?
                            if(getRol().equals("werknemer")) {
                                getUserName();
                                //hier zetten we de dashboard van werknemer
                                Toast.makeText(MainActivity.this, getUserName(), Toast.LENGTH_SHORT).show();
                                toDashboardUser();
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
                new Response.ErrorListener() {
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
    //naam verkrijgen
    private String getUserName() {
        SharedPreferences prefs = this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        String userName = prefs.getString("user_name", "");
        return userName;

    }

    private int getUserId() {
        SharedPreferences prefs = this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        int userId = prefs.getInt("user_id", 0);
        return userId;
    }

    //rol verkrijgen
    private String getRol() {
        SharedPreferences prefs = this.getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        String rol = prefs.getString("rol", "");
        return rol;
    }


    public void toTimerScreen() {
        getToken();
        getUserName();
        Bundle bundleForTimerScreen = new Bundle();
        String name = getUserName();
        String token = getToken();
        int userId = getUserId();
        Log.d("to timer", "went to timer");
        bundleForTimerScreen.putString("name", name);
        bundleForTimerScreen.putString("token", token);
        bundleForTimerScreen.putInt("user_id", userId);
        Intent toTimerScreenIntent = new Intent(this, ActivityTimer.class);
        startActivity(toTimerScreenIntent);
    }

    public void toDashboardUser() {
        getToken();
        getUserName();
        Bundle bundleForUserDashboard = new Bundle();
        String name = getUserName();
        String token = getToken();
        Log.d("gebruikernaam", name);
        Log.d("to timer", "went to timer");
        bundleForUserDashboard.putString("name", name);
        bundleForUserDashboard.putString("token", token);
        Intent toUserDashboard = new Intent(this, DashboardUserActivity.class);
        toUserDashboard.putExtras(bundleForUserDashboard);
        startActivity(toUserDashboard);
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

