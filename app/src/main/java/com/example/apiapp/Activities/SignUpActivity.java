package com.example.apiapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.apiapp.MainActivity;
import com.example.apiapp.R;
import com.example.apiapp.Service.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText user,pass,passc,name;
    Button aceptar;
    Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        user = findViewById(R.id.usuario);
        pass = findViewById(R.id.pass);
        passc = findViewById(R.id.passC);
        name = findViewById(R.id.name);
        aceptar = findViewById(R.id.aceptar);
        queue = Queue.getInstance(this);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

    }

    public void registro(){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.55.111.57:8000/api/auth/signup",
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
                params.put("name", String.valueOf(name.getText()));
                params.put("password", String.valueOf(pass.getText()));
                params.put("password_confirmation", String.valueOf(passc.getText()));
                params.put("email", String.valueOf(user.getText()));
                return params;
            }
        };
        queue.addToQueue(request);
    }

    public void responseHandler(String res) {

        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();

        try {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            JSONArray response = new JSONArray(res);
            /*for(int i = 0, e = response.length(); i < e; i++){
                JSONObject rawPersona = (JSONObject) response.get(i);
                //JSONObject rawPersonaPersona = rawPersona.getJSONObject("persona");


            }*/

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }



    public void errorHandler(VolleyError error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
