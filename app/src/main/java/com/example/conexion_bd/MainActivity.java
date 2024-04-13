package com.example.conexion_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText cedula,nombre,apellido,telefono,email;
    Button agregar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cedula=(EditText) findViewById(R.id.txt_cedula);
        nombre=(EditText) findViewById(R.id.txt_nombre);
        apellido=(EditText) findViewById(R.id.txt_apellido);
        telefono=(EditText) findViewById(R.id.txt_telefono);
        email=(EditText) findViewById(R.id.txt_email);

        agregar=(Button) findViewById(R.id.btn_agregar);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://localhost/bd_android/insertar_usuario.php");
            }
        });

    }

    //metodos
    private  void ejecutarServicio (String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put ("cedula",cedula.getText().toString());
                parametros.put ("nombre",nombre.getText().toString());
                parametros.put ("apellido",apellido.getText().toString());
                parametros.put ("telefono",telefono.getText().toString());
                parametros.put ("email",email.getText().toString());
                return parametros;
            }

        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}