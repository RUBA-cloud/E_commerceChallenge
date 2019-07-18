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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;



import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

public class checkout extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ViewPager viewPager;String id;String name; final  static  String Url2="http://192.168.1.142/php/shopcard.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_checkout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 id=getIntent().getStringExtra("id");
name=getIntent().getStringExtra("username");
final ViewPager viewPager=findViewById(R.id.view);
TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);

PageAdpater pageAdpater=new PageAdpater(getSupportFragmentManager(),id);
viewPager.setAdapter(pageAdpater);
tabLayout.setupWithViewPager(viewPager);
viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        getMenuInflater().inflate(R.menu.checkout, menu);
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
        final int id = item.getItemId();
        if (id == R.id.Home) {
            Intent intent=new Intent(checkout.this,homea.class);
            startActivity(intent);
        } else if (id == R.id.Profile) {
            Intent intent=new Intent(checkout.this,profile.class);
            intent.putExtra("id",id);
            intent.putExtra("username",name);
            startActivity(intent);

        } else if (id == R.id.Additem) {
            Intent intent=new Intent(checkout.this,add2item.class);
            intent.putExtra("id",id);
            intent.putExtra("username",name);
            startActivity(intent);
        }
        else if (id == R.id.AddCard) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Shop Card Action");
            final EditText input = new EditText(this);
            final TextView textView=new TextView(this);
            final Button button=new Button(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setText(""+name);
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
                        hashmap.put("userid",""+id);
                        hashmap.put("shopcard",input.getText().toString().trim());

                        return hashmap;}

                    }; Volley.newRequestQueue(checkout.this).add(stringRequest);}



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
            Intent intent=new Intent(checkout.this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
