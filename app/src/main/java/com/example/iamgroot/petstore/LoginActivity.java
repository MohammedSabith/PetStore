package com.example.iamgroot.petstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String url = Config.SERVER_IP+ "/petlogin.php";
    public static String name;
    public static String email;
    public static String phone;
    public static String password;
    public static String address;

    public static ArrayList<person_details> detaillist = new ArrayList<person_details>();
    public static person_details table;

    private TextView newUser;
    private EditText edUsers, edPasswords;
    private ProgressDialog progressDialog;
    private Button login;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String NameP = "nameKey";
    public static final String PassP = "passwordKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        newUser = findViewById(R.id.newUser);
        edUsers = findViewById(R.id.edUser);
        edPasswords = findViewById(R.id.edPassword);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        login = findViewById(R.id.login);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(LoginActivity.this, register_user.class);
                startActivity(intents);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edUsers.getText().toString().trim();
                String password = edPasswords.getText().toString().trim();
                if (username.isEmpty()) {
                    edUsers.setError("please enter name");
                } else if (password.isEmpty()) {
                    edPasswords.setError("please enter password");
                } else {
                    registerUser1();
                }
            }
        });
    }

    private void registerUser1() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        detaillist.clear();
                        JSONObject jsonResponse;
                        try {
                            jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("products");
                            for (int t = 0; t < jsonArray.length(); t++) {
                                JSONObject object = jsonArray.getJSONObject(t);
                                person_details table = new person_details();
                                table.setEmail(object.getString("email"));
                                table.setPassword(object.getString("password"));
                                table.setName(object.getString("username"));
                                table.setAddress(object.getString("address"));

                                detaillist.add(table);
                                String n = object.getString("username");
                                String p = object.getString("password");


                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(NameP, n);
                                editor.putString(PassP, p);
                                editor.commit();

                                Intent ii = new Intent(LoginActivity.this, CustomViewIconTextTabsActivity.class);
                                startActivity(ii);


                            }

                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "invlaid credentials", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        for (int i = 0; i < detaillist.size(); i++) {
                            name = LoginActivity.detaillist.get(i).getName();
                            email = LoginActivity.detaillist.get(i).getEmail();
                            password = LoginActivity.detaillist.get(i).getPassword();
                            address = LoginActivity.detaillist.get(i).getAddress();

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  loading.dismiss();
                        Toast.makeText(LoginActivity.this, "Error bro", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", String.valueOf(edUsers.getText().toString()));
                params.put("password", String.valueOf(edPasswords.getText().toString()));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void Click(View view) {

        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}
