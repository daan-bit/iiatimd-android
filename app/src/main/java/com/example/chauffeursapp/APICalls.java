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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APICalls {
    //In deze klasse voegen we alle API URLS toe om zo het overzichtelijk te gaan houden

    public static final String URL = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/";
    public static final String HOME = URL+"api/";
    public static final String LOGIN = HOME+"login";
    public static final String GEBRUIKERS = HOME+"gebruikers";
    //nog meer urls toevoegen

    public static void getAllUsers(Context context) {
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GEBRUIKERS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("users");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject gebruiker = jsonArray.getJSONObject(i);
                                String name = gebruiker.getString("name");
                                int uuid = gebruiker.getInt("id");
                                String email = gebruiker.getString("email");
                                String rol = gebruiker.getString("rol");

                                User[] users = new User[jsonArray.length()];
                                users[i] = new User(name, email, rol, uuid);

                                //Database aanmaken
                                AppDatabase db = AppDatabase.getInstance(context); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer

                                new Thread(new InsertUserTask(db, users[i])).start();
                                try {
                                    Thread.sleep(500);
                                    Thread.currentThread().interrupt(); // very important
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(request);
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
