package e.inspiron.e_commercechallenge;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import android.provider.SearchRecentSuggestions;
import android.text.InputType;
import android.util.Base64;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

public class homea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
RecyclerView recyclerView;String search;Button checkout;
SearchView searchView; String username,iduser;

    List<products> productList;final  static  String Url="http://192.168.1.142/php/alldata.php";
    final  static  String Url2="http://192.168.1.142/php/shopcard.php";
    final  static  String Url3="http://192.168.1.142/php/search.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homea);
        Toolbar toolbar = findViewById(R.id.toolbar);
searchView =(SearchView)findViewById(R.id.app_bar_search);
iduser=getIntent().getStringExtra("id");
setTitle("Home");
checkout=(Button)findViewById(R.id.checkout) ;
checkout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent intent=new Intent(homea.this,checkout.class);
intent.putExtra("id",iduser);
intent.putExtra("username",username);
startActivity(intent);
    }
});
     username=getIntent().getStringExtra("name");
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                      @Override
                                      public boolean onQueryTextSubmit(String s) {
                                         getData(s);
                                          return false;
                                      }

                                      @Override
                                      public boolean onQueryTextChange(String s) {
                                         getData(s);
                                          return true;
                                      }
                                  });
        recyclerView = findViewById(R.id.recycelview);
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
        getMenuInflater().inflate(R.menu.homea, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
            Intent intent=new Intent(homea.this,homea.class);
            startActivity(intent);
        } else if (id == R.id.Profile) {
            Intent intent=new Intent(homea.this,profile.class);
            intent.putExtra("id",iduser);
            intent.putExtra("username",username);
            startActivity(intent);

        } else if (id == R.id.Additem) {
            Intent intent=new Intent(homea.this,add2item.class);
            intent.putExtra("id",iduser);
            intent.putExtra("username",username);
            startActivity(intent);
        }
        else if (id == R.id.AddCard) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Shop Card Action");
            final EditText input = new EditText(this);
            final TextView textView=new TextView(this);
            final Button button=new Button(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setText(""+username);
            builder.setView(textView);
            builder.setView(input);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.trim().equals("done")){
Toast.makeText(getApplicationContext(),"New Shop Card Added",Toast.LENGTH_SHORT).show();
                            }


                        }





                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                        }}){@Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> hashmap=new HashMap<>();
                        hashmap.put("userid",""+iduser);
                        hashmap.put("shopcard",input.getText().toString().trim());

                        return hashmap;}

                    }; Volley.newRequestQueue(homea.this).add(stringRequest);}



            });



            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });


            builder.show();
        }
        if(id==R.id.logout){
            finish();
            Intent intent=new Intent(homea.this,MainActivity.class);
            startActivity(intent);
        }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;}




    public void loadproducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONArray jsonArray = new JSONArray(response);
for(int i=0;i<jsonArray.length();i++){
JSONObject product=jsonArray.getJSONObject(i);
String id=product.getString("id");
int id1=0;int id2=0;Bitmap bitmap = null;
String name=product.getString("name");
String des=product.getString("decriptian");
    String salary=product.getString("salary");
    String salary2=product.getString("salaryprice");
    String discount=product.getString("discount");
    String thum=product.getString("thumbnial");
    String image1=product.getString("image1");


    String image2=product.getString("image2");
    String username1=product.getString("username");
    String userid=product.getString("userid");
    String dep=product.getString("departmantid");
    String depname=product.getString("departmantname");
try{ id1=Integer.parseInt(id);
    id2=Integer.parseInt(dep);

}catch (Exception r){}

    products products=new products(id1,name,des,salary,salary2,discount,thum,image1,image2,depname,id2,username1,userid,iduser,username);
productList.add(products);



                }
                    ProductAdpater adapter = new ProductAdpater(homea.this, productList);
                    recyclerView.setAdapter(adapter); }
                catch (JSONException j) {
                    j.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }


        }); Volley.newRequestQueue(this).add(stringRequest);


}

    public  void getData(final String search) {
final String S=search;

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

productList.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
int id1=0;int id2=0;
                        JSONObject product = jsonArray.getJSONObject(i);
                        String id = product.getString("id");
                        String name = product.getString("name");
                        String des = product.getString("decriptian");
                        String salary = product.getString("salary");
                        String salary2 = product.getString("salaryprice");
                        String discount = product.getString("discount");
                  String thum = product.getString("thumbnial");
                     String image1 = product.getString("image1");
                      String image2 = product.getString("image2");
                      String dep = product.getString("departmantid");
                    String depname = product.getString("departmantname");
                        String username1=product.getString("username");
                        String userid=product.getString("userid");

                        Toast.makeText(getApplicationContext(),""+S,Toast.LENGTH_SHORT).show();
                        try {
                            id1 = Integer.parseInt(id);
                            id2 = Integer.parseInt(dep);

                        } catch (Exception r) {
                        }


                        products products=new products(id1,name,des,salary,salary2,discount,thum,image1,image2,depname,id2,username1,userid,iduser,username);
                        productList.add(products);
                    }

                } catch (JSONException e) {
                }


                ProductAdpater adapter = new ProductAdpater(homea.this, productList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadproducts();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }


        }){@Override  protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> hashmap=new HashMap<>();
            hashmap.put("search",S);


            return hashmap;
        }
        };
        Volley.newRequestQueue(homea.this).add(stringRequest2); loadproducts();}   }
