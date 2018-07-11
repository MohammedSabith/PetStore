package com.example.iamgroot.petstore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class register_user extends AppCompatActivity implements View.OnClickListener {

    private static final String url = Config.SERVER_IP + "/petRegister.php";

    private EditText mphone, maddress;
    private TextView alRegistered;
    private EditText edUsername, edPassword, edEmail,edUname;
    private Button register;
    private ProgressDialog progressDialog;

    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        alRegistered = findViewById(R.id.alRegistered);
        edUsername = findViewById(R.id.username);
        edUname = findViewById(R.id.uname);
        edPassword = findViewById(R.id.password);
        edEmail = findViewById(R.id.email);
        mphone = findViewById(R.id.phone);
        register = findViewById(R.id.btnRegister);
        maddress = findViewById(R.id.address);
        progressDialog = new ProgressDialog(this);
        register.setOnClickListener(this);
        edUsername.requestFocus();
        alRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentss = new Intent(register_user.this, LoginActivity.class);
                startActivity(intentss);
            }
        });

    }

    public void Register() {
        StringRequest stringRequest;
        final String username = edUsername.getText().toString().trim();
        final String uname = edUname.getText().toString().trim();
        final String password = edPassword.getText().toString().trim();
        final String email = edEmail.getText().toString().trim();
        final String phone = mphone.getText().toString().trim();
        final String address = maddress.getText().toString().trim();
        progressDialog.setMessage("Requesting");
        progressDialog.show();
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String m=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(), m, Toast.LENGTH_LONG).show();

                            if(m.contentEquals("Registered successfully"))
                            {
                                edUsername.getText().clear();
                                edUname.getText().clear();
                                edPassword.getText().clear();
                                edEmail.getText().clear();
                                mphone.getText().clear();
                                maddress.getText().clear();
                                edUsername.requestFocus();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("uname", uname);
                params.put("password", password);
                params.put("email", email);
                params.put("phone", phone);
                params.put("address", address);
                return params;
            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    @Override
    public void onClick(View view) {

        if (view == register) {
            Register();
        }

    }

    public void onClickText(View v) {
        if (v == alRegistered) {
            Intent intent = new Intent(register_user.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
