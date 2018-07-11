package in.bvm.college.icwstcsc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    EditText username, password,name;
    Button registerButton;
    String user, pass,name1;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                name1 = name.getText().toString();
                final String usertemp = user.replace('.','@');
                if(usertemp.equals("")){
                    username.setError("can't be blank");
                }
                if(name1.equals("")){
                    name.setError("can't be blank");
                }
                else if(pass.equals("")){
                        password.setError("can't be blank");
                    }
                        else if(user.length()<5){
                                username.setError("at least 5 characters long");
                            }
                            else if(pass.length()<5){
                                password.setError("at least 5 characters long");
                            }
                            else {
                                final ProgressDialog pd = new ProgressDialog(Register.this);
                                pd.setMessage("Loading...");
                                pd.show();

                                String url = "https://bvm-chat.firebaseio.com/users.json";

                                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                                    @Override
                                    public void onResponse(String s) {
                                        Firebase reference = new Firebase("https://bvm-chat.firebaseio.com/users");

                                        if(s.equals("null")) {
                                            reference.child(usertemp).child("password").setValue(pass);
                                            reference.child(usertemp).child("name").setValue(name1);
                                            Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            try {
                                                JSONObject obj = new JSONObject(s);

                                                if (!obj.has(usertemp)) {
                                                    reference.child(usertemp).child("password").setValue(pass);
                                                    reference.child(usertemp).child("name").setValue(name1);
                                                    Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                                }

                                            } catch (JSONException e) {
                                                    e.printStackTrace();
                                            }
                                        }

                                    }

                                },new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Toast.makeText(Register.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                                        pd.dismiss();
                                    }
                                });

                                RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                                rQueue.add(request);
                            }
            }
        });
    }
}