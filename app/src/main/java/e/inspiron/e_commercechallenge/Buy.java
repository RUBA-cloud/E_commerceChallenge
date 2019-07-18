package e.inspiron.e_commercechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buy extends Fragment {
    final String id;final String URL_ROOT="http://192.168.1.142/php/buyitem.php";
    List<buyitems>lis=new ArrayList();StringRequest stringRequest;
public Buy(String id){this.id=id;}RecyclerView recyclerView;View view1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       ;
     view1=inflater.inflate(R.layout.buy,container,false);

recyclerView=(RecyclerView)view1.findViewById(R.id.recycelviewbuy);
recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
recyclerView.setHasFixedSize(true);
       stringRequest = new StringRequest(Request.Method.POST, URL_ROOT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject product=jsonArray.getJSONObject(i);
                                String id=product.getString("ownerid");
                                String nam=product.getString("card");
                                String name=product.getString("ownername");
                                String username=product.getString("username");
                                String userid=product.getString("userid");
                                String itemid=product.getString("itemid");
                                String itemname=product.getString("itemname");
                                String startdate=product.getString("startdate");
                                String finishdate=product.getString("finishdate");
buyitems buyitems=new buyitems(id,name,userid,username,itemid,itemname,nam,startdate,finishdate);
                                lis.add(buyitems);
                }}catch (JSONException e){e.printStackTrace();}
                        buyadpater buyadpater=new buyadpater(getContext(),lis);
                        recyclerView.setAdapter(buyadpater);
                         }}
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashmap = new HashMap<>();
                hashmap.put("userid", id);
                return hashmap;
            }};
Volley.newRequestQueue(this.getContext()).add(stringRequest);
     return view1;
    }
}
