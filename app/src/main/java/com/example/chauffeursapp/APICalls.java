package com.example.chauffeursapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class APICalls {
    //In deze klasse voegen we alle API URLS toe om zo het overzichtelijk te gaan houden

    public static final String URL = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/";
    public static final String HOME = URL+"api/";
    public static final String LOGIN = HOME+"login";
    //nog meer urls toevoegen

    public static void getAllUsers() {

    }

    public static void accountAanmaken(String addEmail, String addPass, String addName, Context context){
        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/api/register";

        StringRequest postRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(context, "Aangemaakt", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(context, "Niet aangemaakt", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            public Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name", addName);
                params.put("email", addEmail);
                params.put("password", addPass);
                params.put("rol", "werknemer");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(postRequest);
    }


}
