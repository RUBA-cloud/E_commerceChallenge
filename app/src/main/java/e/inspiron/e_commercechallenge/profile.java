package e.inspiron.e_commercechallenge;

import android.content.DialogInterface;
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

import android.text.InputType;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;String search;
    SearchView searchView; String name,iduser;

    List<products> productList;
    final  static  String Url2="http://192.168.1.142/php/profile.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     iduser=getIntent().getStringExtra("id");
name=getIntent().getStringExtra("username");
        recyclerView = findViewById(R.id.recycleview);
        productList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loadproducts();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void loadproducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject product=jsonArray.getJSONObject(i);
                        String id=product.getString("id");
                        int id1=0;int id2=0;
                        String name=product.getString("name");
                        String des=product.getString("decriptian");
                        String salary=product.getString("salary");
                        String salary2=product.getString("salaryprice");
                        String discount=product.getString("discount");
                        String thum=product.getString("thumbnial");
                        String image1=product.getString("image1");
                        String image2=product.getString("image2");
                        String dep=product.getString("departmantid");
                        String userid=product.getString("userid");
                        String username=product.getString("username");
                        String depname=product.getString("departmantname");
                        try{ id1=Integer.parseInt(id);
                            id2=Integer.parseInt(dep);

                        }catch (Exception r){}
                        products products=new products(id1,name,des,salary,salary2,discount,thum,image1,image2,depname,id2,username,userid);
                       productList.add(products);



                    }
                    productadpatr2 adapter = new productadpatr2(profile.this, productList);
                    recyclerView.setAdapter(adapter); }
                catch (JSONException j) {
                    j.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                        }}){@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashmap=new HashMap<>();

                hashmap.put("id",iduser);return hashmap;}

        }; Volley.newRequestQueue(this).add(stringRequest);}



            @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            Intent intent=new Intent(profile.this,homea.class);
            startActivity(intent);
        } else if (id == R.id.Profile) {
            Intent intent=new Intent(profile.this,profile.class);
            intent.putExtra("id",iduser);
            intent.putExtra("username",name);
            startActivity(intent);

        } else if (id == R.id.Additem) {
            Intent intent=new Intent(profile.this,add2item.class);
            intent.putExtra("id",iduser);
            intent.putExtra("username",name);
            startActivity(intent);
        }
        else if (id == R.id.AddCard) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Shop Card Action");
            final EditText input = new EditText(this);
            final TextView textView = new TextView(this);
            final Button button = new Button(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setText("" + name);
            builder.setView(textView);
            builder.setView(input);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("done")) {
                                Toast.makeText(getApplicationContext(), "New Shop Card Added", Toast.LENGTH_SHORT).show();
                            }


                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> hashmap = new HashMap<>();
                            hashmap.put("userid", "" + iduser);
                            hashmap.put("shopcard", input.getText().toString().trim());

                            return hashmap;
                        }

                    };
                    Volley.newRequestQueue(profile.this).add(stringRequest);
                }


            });


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        }

        if(id==R.id.logout){

            finish();
            Intent intent=new Intent(profile.this,MainActivity.class);
            startActivity(intent);
        }
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;}
}
