package e.inspiron.e_commercechallenge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class buyadpater extends RecyclerView.Adapter<buyadpater.ProductViewHolder> {
    private Context mCtx;
    private List<buyitems> productList;
    public buyadpater(Context mCtx, List<buyitems> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.buyposts, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final buyitems buyitems=productList.get(position);

        holder.name.setText("Item Name "+buyitems.getItemid());
        holder.id.setText("Item Id"+buyitems.getItemname());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx,buyinfo.class);
                intent.putExtra("itemid",buyitems.getItemid());
                intent.putExtra("ownerid",buyitems.getId());
                intent.putExtra("ownername", buyitems.getOwnername());
                intent.putExtra("itemname",buyitems.getItemname());
                intent.putExtra("startdate",buyitems.getStartdate());
                intent.putExtra("finishdate",buyitems.getFinishdate());
                mCtx.startActivity(intent);
            }
        });

        holder.userid.setText("user id "+buyitems.getUserid());

    }













    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,userid;
        ImageView imageView,imageView2;
        Button button;
        public ProductViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.itemid);
          name=itemView.findViewById(R.id.itemname);
          userid=itemView.findViewById(R.id.userid);
            button=itemView.findViewById(R.id.btn);
        }
    }
    public  void update(List<products>products){
        productList=new ArrayList<>();
        products.addAll(products);
        notifyDataSetChanged();
    }
}


