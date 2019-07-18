package e.inspiron.e_commercechallenge;

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
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class order1 extends AppCompatActivity {
TextView start,finish,itemid,itemname,useid,username1;
String itemname1,itemid1,username,userid1;
    Button accept;CalendarView calendar;String Url="http://192.168.1.142/php/order.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        start=(TextView)findViewById(R.id.startdate);
        accept=(Button)findViewById(R.id.Accept) ;
        finish=(TextView)findViewById(R.id.finishdate);
        itemid=(TextView)findViewById(R.id.itemid);
        itemname=(TextView)findViewById(R.id.itemname);
        username1=(TextView)findViewById(R.id.username);
        useid=(TextView)findViewById(R.id.userid);
      itemname1=getIntent().getStringExtra("itemname");
     itemid1=getIntent().getStringExtra("itemid");
    username=getIntent().getStringExtra("username");
      userid1=getIntent().getStringExtra("userid");
        itemname.setText("Item Name " +itemname1);
        itemid.setText("Item Id " +itemid1);
        username1.setText("User Name "+username);
        useid.setText("User Id"+userid1);
        calendar=(CalendarView)findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

              ;
if(TextUtils.isEmpty(start.getText().toString())){ start.setText("Send Item Date: " + dayOfMonth +" / " + (month+1) + " / " + year);}
else finish.setText("Recive Item Date: " + dayOfMonth +" / " + (month+1) + " / " + year);

            }});



        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

add();


            }});}
public void  add(){

    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("Add")) {
                        Toast.makeText(getApplicationContext(), " Done", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        }
    }) {@Override
    protected Map<String, String> getParams() throws AuthFailureError {

        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("userid",userid1);
        hashmap.put("itemid", itemid1);
        hashmap.put("startdate",start.getText().toString().trim());
        hashmap.put("finishdate",finish.getText().toString().trim());
        return hashmap;}};;

   Volley.newRequestQueue(order1.this).add(stringRequest);}}


