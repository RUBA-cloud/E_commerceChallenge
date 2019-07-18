package e.inspiron.e_commercechallenge;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;

public class loginactivity extends AppCompatActivity {
EditText email,pass;
Button btn;
ProgressBar progressBar; final static String URL_ROOT="http://192.168.1.142/php/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        setTitle("Log in");
        progressBar.setVisibility(View.INVISIBLE);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                loginuser();


    }});}
    public  void loginuser() {
            final String EMail= email.getText().toString().trim();
            final String password = pass.getText().toString().trim();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(EMail).matches()) {
           email.setError("Enter a valid email");
           email.requestFocus();

            return;
        }
        if (TextUtils.isEmpty(password)) {
                pass.setError("Enter Password");
                pass.requestFocus();
                return;
            }


        RequestQueue r= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ROOT,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                  String t=response.trim();
try{
    JSONArray jsonArray=new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            String id = product.getString("id");
                            String name = product.getString("name");
progressBar.setVisibility(View.VISIBLE);btn.setVisibility(View.INVISIBLE);

                          Intent intent = new Intent(loginactivity.this, homea.class);
                          intent.putExtra("id",id);
                          intent.putExtra("name",name);
                          startActivity(intent);
                        }
                    }catch (JSONException e){}}


    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> hashmap=new HashMap<>();
            hashmap.put("email",EMail);
            hashmap.put("password",password);
           return  hashmap;
        }
    };
        r.add(stringRequest);}}

