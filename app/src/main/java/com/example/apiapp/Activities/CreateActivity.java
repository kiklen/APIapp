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
import com.example.apiapp.R;
import com.example.apiapp.Service.Queue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    EditText materia,semestre;
    Button aceptar;
    private Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        aceptar = findViewById(R.id.guardar);
        queue = Queue.getInstance(this);

        materia = findViewById(R.id.materia);
        semestre = findViewById(R.id.semestre);


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crear();
            }
        });
    }

    public void crear(){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.55.111.57:8000/api/materia/insertar",
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
                params.put("nombre", String.valueOf(materia.getText()));
                params.put("semestre", String.valueOf(semestre.getText()));
                return params;
            }
        };
        queue.addToQueue(request);
    }

    public void responseHandler(String res) {
        //startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        try {
            JSONObject response = new JSONObject(res);
            if(response!=null){
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
