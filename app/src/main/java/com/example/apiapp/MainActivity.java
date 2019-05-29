package com.example.apiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.apiapp.Activities.MenuActivity;
import com.example.apiapp.Activities.SignUpActivity;
import com.example.apiapp.Service.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText user,pass;
    Button registro, aceptar;
    private Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registro= findViewById(R.id.registro);
        aceptar = findViewById(R.id.ingresar);
        queue = Queue.getInstance(this);

        user = findViewById(R.id.usuario);
        pass = findViewById(R.id.pass);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
    }

    public void ingresar(){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.55.111.57:8000/api/auth/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseHandler(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorHandler(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", String.valueOf(user.getText()));
                params.put("password", String.valueOf(pass.getText()));
                return params;
            }
        };
        queue.addToQueue(request);
    }

    public void responseHandler(String res) {
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        //Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        try {
            JSONObject response = new JSONObject(res);

            boolean r =(boolean)response.getBoolean("success");
            Toast.makeText(this, r+"", Toast.LENGTH_SHORT).show();
            if(r){
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }



    public void errorHandler(VolleyError error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


}
