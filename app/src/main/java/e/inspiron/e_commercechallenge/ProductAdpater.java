package e.inspiron.e_commercechallenge;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.util.Base64;
import android.view.ViewGroup;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdpater extends RecyclerView.Adapter<ProductAdpater.ProductViewHolder> {


    private Context mCtx;
    private List<products> productList;
    final String Url2="http://192.168.1.142/php/Buy.php";
    byte[] decodedString;
    public ProductAdpater(Context mCtx, List<products> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.posts, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final products product = productList.get(position);
holder.id.setText("Item Id"+product.getId() );
   holder.name.setText(product.getUsername());
      holder.desc.setText(String.valueOf(product.getDescriptian()));
      holder.salary.setText("Item Salary :"+(product.getSalary()));
     holder.salaryprice.setText("Salary Price Currency:"+product.getSalaryprice());
      holder.thumbnail.setText("Thumbnial:"+product.getThumbnail());
        holder.discount.setText("Item Discount:"+(product.getDiscount()));
        holder.departmentid.setText("Department Id :"+product.getDepartmentid());
        holder.departmentname.setText("Departmentname"+product.getDepartmentname());
        holder.itemname.setText("Item Name:"+product.getName());
        Glide.with(mCtx).load(product.getImage1()).into(holder.imageView);
        Glide.with(mCtx).load(product.getImage2()).into(holder.imageView2);
holder.userid.setText(product.getUserid());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are You Sure You Want TO Buy this Item");
                final EditText input = new EditText(mCtx);

input.setText("Enter Your Shopping Card Id");
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                builder.setView(input);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.trim().equals("Buy")){

                                        }


                                    }





                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(mCtx, error.getMessage(), Toast.LENGTH_LONG).show();

                                    }}){@Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> hashmap=new HashMap<>();
                                    hashmap.put("itemid",""+product.getId());
                                    hashmap.put("id",product.getUserid());
                                    hashmap.put("userid",product.getIduser());
                                    hashmap.put("username1",product.getUsername());
                                    hashmap.put("username2",product.getNamelogin());
                                    hashmap.put("itemname",product.getName());
                                    hashmap.put("card",input.getText().toString().trim());
                                    return hashmap;}

                                }; Volley.newRequestQueue(mCtx).add(stringRequest);}



                });



                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                builder.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,desc,salary,salaryprice,thumbnail,discount,departmentname,departmentid,userid,itemname;
        ImageView imageView,imageView2;
Button button;
        public ProductViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.itemid);
            desc = itemView.findViewById(R.id.desc);
            salary = itemView.findViewById(R.id.itemsalary);
            salaryprice = itemView.findViewById(R.id.itemprice);
            thumbnail= itemView.findViewById(R.id.thumbnail);
            discount= itemView.findViewById(R.id.itemdiscount);
            imageView = itemView.findViewById(R.id.image);
            imageView2 = itemView.findViewById(R.id.image2);
            name=itemView.findViewById(R.id.username);
            userid=itemView.findViewById(R.id.userid);
            departmentid= itemView.findViewById(R.id.Department_id);
            itemname=itemView.findViewById(R.id.itemname);
            button=itemView.findViewById(R.id.buy);
            departmentname= itemView.findViewById(R.id.Department_name);

        }
    }
    public  void update(List<products>products){
        productList=new ArrayList<>();
        products.addAll(products);
        notifyDataSetChanged();
    }
}
