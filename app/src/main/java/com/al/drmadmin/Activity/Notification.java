package com.al.drmadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.al.drmadmin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Notification extends AppCompatActivity {

    Button post;
    EditText title,message;
    String g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        post=findViewById(R.id.post);
        title=findViewById(R.id.title);
        message=findViewById(R.id.msg);

        g = new SimpleDateFormat("dd MMMM yyyy").format(Calendar.getInstance().getTime());

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().equals("") && !message.getText().toString().equals("") ){

                    HashMap<String, Object> result = new HashMap<>();
                    result.put("title", title.getText().toString().trim());
                    result.put("message",message.getText().toString().trim());
                    result.put("date",g);
                    result.put("timestamp",new Date().getTime());

                    FirebaseDatabase.getInstance().getReference("Notifications").child(FirebaseDatabase.getInstance().getReference().push().getKey()).setValue(result).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Successfully posted",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Check Internet Connection!!",Toast.LENGTH_SHORT).show();

                        }
                    });

                }else {

                    Toast.makeText(getApplicationContext(),"Fill up all the fields properly",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
