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

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ProductLocation extends AppCompatActivity {
    EditText iddep,idname,idcatrog,namec;Button next;private int progressStatus = 0, progressStatus2 = 0, progressStatus3 = 0;
    TextView LOAD;String email,username1;
    String userid, i,i2,id,name,des,thum,discount,salary,productsalary;final static String URL_ROOT=" http://192.168.1.142/php/additem.php";
    private Handler handler = new Handler();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iddep=(EditText)findViewById(R.id.Department_id);
        progressBar = findViewById(R.id.pb2);
        LOAD=findViewById(R.id.load);

        progressBar.setVisibility(View.INVISIBLE);
        idname=(EditText)findViewById(R.id.Department_name);
        idcatrog=(EditText)findViewById(R.id.category_id);
        namec=(EditText)findViewById(R.id.category_name);
         id = (String) getIntent().getStringExtra("id");
         userid=(String)getIntent().getStringExtra("userid");
         i=getIntent().getStringExtra("image1");
i2=getIntent().getStringExtra("image2");

         name = (String) getIntent().getStringExtra("name");
        username1 =(String) getIntent().getStringExtra("username");
         des = (String) getIntent().getStringExtra("desc");
        thum = (String) getIntent().getStringExtra("th");
         discount = (String) getIntent().getStringExtra("discount");
       salary = (String) getIntent().getStringExtra("salary");
         productsalary = (String) getIntent().getStringExtra("salaryprice");

idcatrog.setText(""+id);
namec.setText(""+name);

next=(Button)findViewById(R.id.next);
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        checkdata();
    }
});
    }
private void checkdata() {
String idd=iddep.getText().toString().trim();
String idname1=idname.getText().toString().trim();
    if (TextUtils.isEmpty(idd)) {
        iddep.setError("Please enter  Department id ");
        iddep.requestFocus();
        return;
    }
    if (TextUtils.isEmpty(idname1)) {
        idname.setError("Please enter Department name ");
        idname.requestFocus();
        return;
    }  StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ROOT,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   next.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (progressStatus < 100) {
                                // Update the progress status
                                progressStatus += 1;

                                // Try to sleep the thread for 20 milliseconds
                                try {
                                    Thread.sleep(70);  //3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // Update the progress bar
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        LOAD.setVisibility(View.VISIBLE);
                                        LOAD.setText(""+progressStatus);

                                    }
                                });
                            }
                        }
                    }).start(); // Start the operation

                    Intent intent=new Intent(ProductLocation.this,homea.class);

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
            hashmap.put("name",name);
            hashmap.put("id",id);
            hashmap.put("desc",des);
            hashmap.put("salary",salary);
            hashmap.put("salary2",productsalary);
            hashmap.put("thumb",thum);
            hashmap.put("Did",iddep.getText().toString().trim());
            hashmap.put("discount",discount);
            hashmap.put("image1",i);
            hashmap.put("image2",i2);
            hashmap.put("userid",userid);
            hashmap.put("username",username1);
            hashmap.put("dname",idname.getText().toString().trim());
            return hashmap;
        }
    };
    RequestQueue r= Volley.newRequestQueue(this);
    r.add(stringRequest);}}