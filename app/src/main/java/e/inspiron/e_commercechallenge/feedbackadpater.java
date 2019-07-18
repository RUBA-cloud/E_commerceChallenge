package e.inspiron.e_commercechallenge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class feedbackadpater extends RecyclerView.Adapter<feedbackadpater.ProductViewHolder> {
    private Context mCtx;
    private List<buyitems> productList;
    public feedbackadpater(Context mCtx, List<buyitems> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.feedbacks, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final buyitems buyitems=productList.get(position);
        holder.userid.setText("Item id "+buyitems.getUserid());
        holder.name.setText(""+buyitems.getItemid());
        holder.date.setText(""+buyitems.getFeedbackdate());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent=new Intent(mCtx,feedbacinfo.class);
intent.putExtra("username",buyitems.getUsename());
intent.putExtra("feedback",buyitems.getFeedback());
intent.putExtra("feedbackdate",buyitems.getFeedbackdate());
intent.putExtra("itemid",buyitems.getItemname());
intent.putExtra("itemname",buyitems.getItemid());
mCtx.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
Button btn;
        TextView id,name,userid,date;
        ImageView imageView,imageView2;
        Button button;
        public ProductViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.itemid);
            name=itemView.findViewById(R.id.itemname);
            userid=itemView.findViewById(R.id.userid);
date=itemView.findViewById(R.id.feedbackdate);
button=itemView.findViewById(R.id.fbtn);
        }
    }

}


