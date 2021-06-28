package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,pass;
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
        login(email.getText().toString(), pass.getText().toString());
    }


    //inloggen
    public void login(String email, String pass){
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/api/login";

        StringRequest postRequest = new StringRequest(Request.Method.POST,url,
            new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    Toast.makeText(MainActivity.this, "Ingelogd", Toast.LENGTH_SHORT).show();
                }
            },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(MainActivity.this, "Inloggen mislukt", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            public Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", pass);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }


    //Pagina's
    public void toTimerScreen(){
        Bundle bundleForTimerScreen = new Bundle();
        String name = "Alex";
        Log.d("to timer", "went to timer");
        bundleForTimerScreen.putString("name", name);
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

