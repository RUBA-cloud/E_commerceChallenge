package e.inspiron.e_commercechallenge;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class buyinfo extends AppCompatActivity {
    TextView item, owner, idowner, iditem, feedback,finish,start;
    Button makefeedback;private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date,itemid,ownername,ownerid;

   final String Url = "http://192.168.1.142/php/feedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyinfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        calendar = Calendar.getInstance();
        setSupportActionBar(toolbar);
finish=(TextView)findViewById(R.id.finishdate);
start=(TextView)findViewById(R.id.startdate);
        owner = (TextView) findViewById(R.id.username);
        item = (TextView) findViewById(R.id.itemname);
        idowner = (TextView) findViewById(R.id.userid);
        iditem = (TextView) findViewById(R.id.itemid);
        feedback = (EditText) findViewById(R.id.feedback);
        makefeedback = (Button) findViewById(R.id.feedbackbtn);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        String name = getIntent().getStringExtra("itemname");
       itemid = getIntent().getStringExtra("itemid");
        ownername = getIntent().getStringExtra("ownername");
   ownerid = getIntent().getStringExtra("ownerid");
        String startdate = getIntent().getStringExtra("startdate");
        final String finishdate = getIntent().getStringExtra("finishdate");
        owner.setText("Owner Name" + ownername);
        item.setText(" Item Name" + name);
        iditem.setText(" " + itemid);
        idowner.setText("  " + ownerid);
finish.setText(""+finishdate);
start.setText(""+startdate);
        makefeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fee = feedback.getText().toString().trim();
                if (TextUtils.isEmpty(fee)) {
                    return;
                }

add();

}});}
public void add(){
    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("Add")) {
                        Toast.makeText(getApplicationContext(), "FeedBack ADD", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        }}){@Override
        protected Map<String, String> getParams() throws AuthFailureError {

     Map<String, String> hashmap = new HashMap<>();
            hashmap.put("feedback",feedback.getText().toString().trim());
            hashmap.put("ownerid", ownerid);
            hashmap.put("itemid", itemid);
            hashmap.put("date",date);
            return hashmap;}};Volley.newRequestQueue(buyinfo.this).add(stringRequest);}}



