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
    public static final String VAKANTIEDAGEN = HOME+"vakantiedagen";
    public static final String WERKTIJDEN = HOME+"werktijden";
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




    public static void sendVakantieDagen(int user_id, String vakantie_van, String vakantie_tot, int inLaravelDB, Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/api/vakantiedagen/store";

        StringRequest postRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(context, "Data opgeslagen", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(context, "Error met opslaan data", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            public Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id", String.valueOf(user_id));
                params.put("vakantie_van", vakantie_van);
                params.put("vakantie_tot", vakantie_tot);
                params.put("inLaravelDB", vakantie_tot);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(postRequest);
    }


    /*Vakantiedagen ophalen*/

    public static void getAllVakantiedagen(Context context) {
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, VAKANTIEDAGEN, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("vakantiedagen");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject vakantiedag = jsonArray.getJSONObject(i);
                                int id = vakantiedag.getInt("id");
                                int user_id = vakantiedag.getInt("user_id");
                                String vakantie_van = vakantiedag.getString("vakantie_van");
                                String vakantie_tot = vakantiedag.getString("vakantie_tot");
                                int inLaravelDB = vakantiedag.getInt("inLaravelDB");

                                Vakantiedagen[] vakantiedagen = new Vakantiedagen[jsonArray.length()];
                                vakantiedagen[i] = new Vakantiedagen(id, user_id, vakantie_van, vakantie_tot, inLaravelDB);

                                //Database aanmaken
                                AppDatabase db = AppDatabase.getInstance(context); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer

                                new Thread(new InsertVakantiedagenTask(db, vakantiedagen[i])).start();
                                try {
                                    Thread.sleep(200);
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

    /*einde vakantiedagen ophalen*/





    /*Sturen werktijden naar db */

    public static void sendWerktijden(int id, int user_id, String begin_shift, String einde_shift, int inLaravelDB, Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/api/werktijden/store";

        StringRequest postRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(context, "Werktijden opgeslagen", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("APPLOG", error.toString());
                        Toast.makeText(context, "Error met opslaan werktijden", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            public Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("user_id", String.valueOf(user_id));
                params.put("begin_shift", begin_shift);
                params.put("einde_shift", einde_shift);
                params.put("inLaravelDB", "1");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    /* einde sturen werktijden naar db */


    /*Vakantiedagen ophalen*/

    public static void getAllWerktijden(Context context) {
        RequestQueue queue = VolleySingleton.getInstance(context.getApplicationContext()).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, WERKTIJDEN, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("response");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject werktijden = jsonArray.getJSONObject(i);
                                int id = werktijden.getInt("id");
                                int uuid = werktijden.getInt("user_id");
                                String begin_shift = werktijden.getString("begin_shift");
                                String einde_shift = werktijden.getString("einde_shift");
                                int inLaravelDB = werktijden.getInt("inLaravelDB");

                                Werktijden[] werktijdens = new Werktijden[jsonArray.length()];
                                werktijdens[i] = new Werktijden(id, uuid, begin_shift, einde_shift, inLaravelDB);

                                //Database aanmaken
                                AppDatabase db = AppDatabase.getInstance(context); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer

                                new Thread(new InsertWerktijdenTask(db, werktijdens[i])).start();
                                try {
                                    Thread.sleep(200);
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

    /*einde vakantiedagen ophalen*/


}
