package e.inspiron.e_commercechallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class feedback extends Fragment
{
    final String id;final String URL_ROOT="http://192.168.1.142/php/feedbackresult.php";
    List<buyitems> lis=new ArrayList();
    StringRequest stringRequest;
public  feedback(String id){this.id=id;}
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view= inflater.inflate(R.layout.feedback, container,false);




            recyclerView=(RecyclerView)view.findViewById(R.id.recycelviewbuy);
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
                                    String feedbackdate=product.getString("feedbackdate");
                                    String feedback=product.getString("feedback");


                                    buyitems buyitems=new buyitems(id,name,userid,username,itemid,itemname,nam,startdate,finishdate,feedback,feedbackdate);
                                    lis.add(buyitems);
                                }}catch (JSONException e){e.printStackTrace();}
                           feedbackadpater buyadpater=new feedbackadpater(getContext(),lis);
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
     return view;
    }
}
