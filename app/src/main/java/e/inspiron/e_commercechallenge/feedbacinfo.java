package e.inspiron.e_commercechallenge;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class feedbacinfo extends AppCompatActivity {
String feedbackdate,feedback,username,userid,itemname,itemid;
TextView feedbackdate1,feedback1,username1,userid1,itemnmae1,itemid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("FeedBack Information");
        setContentView(R.layout.activity_feedbacinfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
feedbackdate1=findViewById(R.id.feedbackdate);
feedback1=findViewById(R.id.feedback);
userid1=findViewById(R.id.userid);
username1=findViewById(R.id.username);
itemnmae1=findViewById(R.id.itemname);
itemid1=findViewById(R.id.itemid);

       feedbackdate=getIntent().getStringExtra("feedback");
       feedback=getIntent().getStringExtra("feedback");
       username=getIntent().getStringExtra("username");

        itemname=getIntent().getStringExtra("itemname");
        itemid=getIntent().getStringExtra("itemid");
   feedback1.setText("User FeedBack "+feedback);
   feedbackdate1.setText("Date of FeedBack "+feedbackdate);
   itemnmae1.setText("Item Name "+itemname);
   itemid1.setText("Item Id"+itemid);
   username1.setText("username "+username);

    }

}
