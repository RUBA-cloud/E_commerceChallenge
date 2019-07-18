package e.inspiron.e_commercechallenge;

import android.app.DownloadManager;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createAccount extends AppCompatActivity {
    EditText editTextUsername, editTextEmail, editTextPassword,city,country,region,mobile,daymob,evephon,address1,address2,shipping;
    Button signup;ProgressBar progressBar;int evem,daym,mom;private int progressStatus = 0, progressStatus2 = 0, progressStatus3 = 0;

    private Handler handler = new Handler();
    private static final String URL_ROOT = "http://192.168.1.142/php/p2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextUsername = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
      mobile = findViewById(R.id.mob_phone);
evephon = findViewById(R.id.evephone);
shipping=findViewById(R.id.shipping_region_id);
       city = findViewById(R.id.city);
       country = findViewById(R.id.country);
        region = findViewById(R.id.region);
      address1 = findViewById(R.id.addressa);
      address2=findViewById(R.id.addressb);
      daymob=findViewById(R.id.dayphone);
        progressBar = findViewById(R.id.pb2);
        progressBar.setVisibility(View.INVISIBLE);
signup=(Button)findViewById(R.id.CreateAccount);
signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        registerUser();


    }
});}

        private void registerUser() {
            final String username = editTextUsername.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            final String city1 = city.getText().toString().trim();
            final String usercountry = country.getText().toString().trim();
            final String useraddress = address1.getText().toString().trim();
            final String useraddress2 = address2.getText().toString().trim();
            final String mobilephone = mobile.getText().toString().trim();
            final String evemobile = evephon.getText().toString().trim();
            final String daypho = daymob.getText().toString().trim();
            final String userregion = region.getText().toString().trim();
            final String shop = shipping.getText().toString().trim();
            if (TextUtils.isEmpty(shop)) {
             shipping.setError("Please enter username");
               shipping.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(username)) {
                editTextUsername.setError("Please enter username");
                editTextUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                editTextEmail.setError("Please enter your email");
                editTextEmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Enter a valid email");
                editTextEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Enter a password");
                editTextPassword.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(city1)) {
                city.setError("Enter your city");
                city.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(usercountry)) {
                country.setError("Enter your Country");
                country.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(userregion)) {
                region.setError("Enter your  Region");
                region.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(useraddress)) {
               address1.setError("Enter your Address");
                address1.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(useraddress2)) {
                address2.setError("Enter your  Address2");
                address2.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(mobilephone)) {
             mobile.setError("Enter your mobilePhone");
                try {
                     mom=Integer.parseInt(evemobile);
                }catch (Exception e){Toast.makeText(getApplicationContext(),"you must enter number",Toast.LENGTH_LONG).show();}

                mobile.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(daypho)) {
                try {
                    daym=Integer.parseInt(evemobile);
                }catch (Exception e){Toast.makeText(getApplicationContext(),"you must enter number",Toast.LENGTH_LONG).show();}

                daymob.setError("Enter your  Region");
                daymob.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(evemobile)) {
                try {
                     evem=Integer.parseInt(evemobile);
                }catch (Exception e){Toast.makeText(getApplicationContext(),"you must enter number",Toast.LENGTH_LONG).show();}

                evephon.setError("Enter your  Region");
         evephon.requestFocus();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ROOT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            User user =new User(email,username);
signup.setVisibility(View.INVISIBLE);
progressBar.setVisibility(View.VISIBLE);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (progressStatus < 100) {
                                        // Update the progress status
                                        progressStatus += 1;

                                        // Try to sleep the thread for 20 milliseconds
                                        try {
                                            Thread.sleep(50);  //3 seconds
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        // Update the progress bar
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressBar.setProgress(progressStatus);
                                                // Show the progress on TextView

                                            }
                                        });
                                    }
                                }
                            }).start(); // Start the operation

Intent intent=new Intent(createAccount.this,homea.class);

startActivity(intent);

}
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> hashmap=new HashMap<>();
                    hashmap.put("username",username);
                    hashmap.put("email",editTextEmail.getText().toString().trim());
                    hashmap.put("password",password);
                    hashmap.put("country",usercountry);
                    hashmap.put("city",city1);
                    hashmap.put("address",useraddress);
                    hashmap.put("address2",useraddress2);
                    hashmap.put("mobile",mobilephone);
                    hashmap.put("evemobile",evemobile);
                    hashmap.put("daymobile",daypho);
                    hashmap.put("ship_cart",shop);
                    hashmap.put("region",userregion);
                    return hashmap;
                }
            };
        RequestQueue r=Volley.newRequestQueue(this);
    r.add(stringRequest);}}