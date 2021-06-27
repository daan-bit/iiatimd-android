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

                            if(response.getString("rol").equals("werknemer")) {
                                anderScherm();
                            } else {
                                Log.d("rol", "geen werknemer");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(MainActivity.this, "Ingelogd", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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


    public void anderScherm() {
        Bundle bundleForTimerScreen = new Bundle();
        String name = "Alex";
        Log.d("to timer", "went to timer");
        bundleForTimerScreen.putString("name", name);
        Intent toTimerScreenIntent = new Intent(this, ActivityTimer.class);
        startActivity(toTimerScreenIntent);
    }




}

