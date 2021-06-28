package com.example.chauffeursapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WerknemerToevoegenActivity extends AppCompatActivity implements View.OnClickListener{

    EditText addEmail,addPass, addName, addPass2;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werknemertoevoegen);

        addName = (EditText) findViewById(R.id.addNameField);
        addEmail = (EditText) findViewById(R.id.addEmailField);
        addPass = (EditText) findViewById(R.id.addPasswordField);
        addPass2 = (EditText) findViewById(R.id.addPassword2Field);
        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (addPass.getText().toString().equals(addPass2.getText().toString())) {
            accountAanmaken(addEmail.getText().toString(), addPass.getText().toString(), addName.getText().toString());
        }else {
            Toast.makeText(WerknemerToevoegenActivity.this, "Wachtwoorden ongelijk", Toast.LENGTH_SHORT).show();
        }
    }

    public void accountAanmaken(String addEmail, String addPass, String addName){
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            String url = "https://iiatimd-laravel-5ahcg.ondigitalocean.app/api/register";

            StringRequest postRequest = new StringRequest(Request.Method.POST,url,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){
                            Toast.makeText(WerknemerToevoegenActivity.this, "Aangemaakt", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Log.d("APPLOG", error.toString());
                            Toast.makeText(WerknemerToevoegenActivity.this, "Niet aangemaakt", Toast.LENGTH_SHORT).show();
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
            VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
        }
    }

