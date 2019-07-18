package e.inspiron.e_commercechallenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class add2item extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button next, addimage;String usname;
    EditText id,productname,desc,thumbial,salary,discount,salaryprice;
    static final int gallary = 1;
    ImageView image1,image2;
   Uri imag,iimag1;

  String userid;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    static final int cemra = 2;final  static  String Url2="http://192.168.1.142/php/shopcard.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       userid=getIntent().getStringExtra("id");
       setTitle(" Add Item");
        usname=getIntent().getStringExtra("username");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        id=(EditText)findViewById(R.id.Productid);
        productname=(EditText)findViewById(R.id.ProductName);
        desc=(EditText)findViewById(R.id.ProductDiscriptian);
        thumbial=(EditText)findViewById(R.id.thumbnail);
        salaryprice=(EditText)findViewById(R.id.salary);
        salary=(EditText)findViewById(R.id.Productsalary);
        discount=(EditText)findViewById(R.id.discount);
        image1=(ImageView)findViewById(R.id.image1);
        image2=(ImageView)findViewById(R.id.image2);
        addimage = (Button) findViewById(R.id.addimage);
        next = (Button) findViewById(R.id.next);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhotoFromGallary();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdata();


            }
        });

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
        getMenuInflater().inflate(R.menu.add2item, menu);
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
            Intent intent=new Intent(add2item.this,homea.class);
            startActivity(intent);
        } else if (id == R.id.Profile) {
            Intent intent=new Intent(add2item.this,profile.class);
            intent.putExtra("id",id);
            intent.putExtra("username",usname);
            startActivity(intent);


        } else if (id == R.id.Additem) {
            Intent intent=new Intent(add2item.this,add2item.class);
            startActivity(intent);
        } else if (id == R.id.AddCard) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Shop Card Action");
            final EditText input = new EditText(this);
            final TextView textView=new TextView(this);
            final Button button=new Button(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setText(""+usname);
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
                        hashmap.put("userid",userid);
                        hashmap.put("shopcard",input.getText().toString().trim());

                        return hashmap;}

                    }; Volley.newRequestQueue(add2item.this).add(stringRequest);}



            });



            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
builder.show();}
        if(id==R.id.logout){
            finish();
            Intent intent=new Intent(add2item.this,MainActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public  void checkdata(){
        String dis=discount.getText().toString().trim();
        String Id=id.getText().toString().trim();
        String name=productname.getText().toString().trim();
        String ds=desc.getText().toString().trim();
        String Salary=salaryprice.getText().toString().trim();
        String price=salary.getText().toString().trim();
        String Thumbnoil=thumbial.getText().toString().trim();

        if (TextUtils.isEmpty(Id)) {
            id.setError("Please enter Product Id ");
            id.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(dis)) {
            discount.setError("Please enter Product Discount");
            discount.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            id.setError("Please enter Product  Name");
            id.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Thumbnoil)) {
            thumbial.setError("Please enter Product Thumbial");
            thumbial.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Salary)) {
            salary.setError("Please enter Product Salary");
            salary.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(price)) {
            id.setError("Please enter Product $");
            id.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Thumbnoil)) {
           thumbial.setError("Please enter Product Discount ");
        thumbial.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ds)) {
            desc.setError("Please enter your Descriptian");
            desc.requestFocus();
            return;
        }
        Intent intent = new Intent(add2item.this, ProductLocation.class);
        intent.putExtra("desc",desc.getText().toString().trim());
        intent.putExtra("id",id.getText().toString().trim());
        intent.putExtra("salary",salary.getText().toString().trim());
        intent.putExtra("salaryprice",salaryprice.getText().toString().trim());

        intent.putExtra("name",productname.getText().toString().trim());
        intent.putExtra("th",thumbial.getText().toString().trim());
        intent.putExtra("discount",discount.getText().toString().trim());
        intent.putExtra("image1",""+imag);
        intent.putExtra("image2",""+iimag1);
        intent.putExtra("userid",userid);
        intent.putExtra("username",usname);
        startActivity(intent);
    }



    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, gallary);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == gallary) {
            if (data != null) {
                Uri contentURI = data.getData();
                imag=contentURI;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    Toast.makeText(add2item.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    if (image1.getDrawable()!= null) {
                        if (data != null) {
Uri uri=data.getData();

                        Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                           iimag1=uri;

                            image2.setImageBitmap(bitmap2);
                    }}
                    else{
                    image1.setImageBitmap(bitmap);}

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(add2item.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        }
}
