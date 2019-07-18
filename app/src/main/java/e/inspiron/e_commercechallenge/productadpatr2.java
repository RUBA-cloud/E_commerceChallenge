package e.inspiron.e_commercechallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class productadpatr2 extends RecyclerView.Adapter< productadpatr2.ProductViewHolder> {

 final  static  String Url2="http://192.168.1.142/php/delet.php";
    private Context mCtx;
    private List<products> productList;

    public  productadpatr2 (Context mCtx, List<products> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.profileposts, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final products product = productList.get(position);
        holder.name.setText("Item Name"+product.getUsername());
        holder.desc.setText(String.valueOf(product.getDescriptian()));
        holder.salary.setText("Item Salary :"+(product.getSalary()));
        holder.salaryprice.setText("Salary Price Currency:"+product.getSalaryprice());
        holder.thumbnail.setText("Thumbnial:"+product.getThumbnail());
        holder.discount.setText("Item Discount:"+(product.getDiscount()));
        holder.departmentid.setText("Department Id :"+product.getDepartmentid());
        holder.departmentname.setText("Departmentname"+product.getDepartmentname());

        Glide.with(mCtx).load(product.getImage1()).into(holder.imageView);
        Glide.with(mCtx).load(product.getImage2()).into(holder.imageView2);



        holder. Delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Delet")){
                            Toast.makeText(mCtx,"Delet",Toast.LENGTH_SHORT).show();
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
                    hashmap.put("id",product.getUserid());return hashmap;}

                }; Volley.newRequestQueue(mCtx).add(stringRequest);}



       });


    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,desc,salary,salaryprice,thumbnail,discount,departmentname,departmentid,itemname;
        ImageView imageView,imageView2;
Button Delet;
        public ProductViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.itemid);
            desc = itemView.findViewById(R.id.desc);
            itemname=itemView.findViewById(R.id.itemname);
            salary = itemView.findViewById(R.id.itemsalary);
            salaryprice = itemView.findViewById(R.id.itemprice);
            thumbnail= itemView.findViewById(R.id.thumbnail);
            discount= itemView.findViewById(R.id.itemdiscount);
            imageView = itemView.findViewById(R.id.image);
            imageView2 = itemView.findViewById(R.id.image2);
            name=itemView.findViewById(R.id.name);
            departmentid= itemView.findViewById(R.id.Department_id);
            departmentname= itemView.findViewById(R.id.Department_name);
            Delet=(Button)itemView.findViewById(R.id.delet);

        }
    }

}
