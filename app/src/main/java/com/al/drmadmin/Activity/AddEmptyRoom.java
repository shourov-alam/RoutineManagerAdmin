package com.al.drmadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.al.drmadmin.Model.Empty_Room_Model;
import com.al.drmadmin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmptyRoom extends AppCompatActivity {

    Spinner week,month,time;
    AutoCompleteTextView day_of_month;
    EditText room;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_empty_room);


        week=findViewById(R.id.week);
        time=findViewById(R.id.time);
        month=findViewById(R.id.month);
        day_of_month=findViewById(R.id.day_auto);
        room=findViewById(R.id.roomz);
        save=findViewById(R.id.save);



        String[] days = getResources().getStringArray(R.array.date);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, days);
        day_of_month.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(day_of_month.getText().toString().equals("") && room.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Fill up all the fields properly", Toast.LENGTH_LONG).show();
                }else {

                    String[] a=getResources().getStringArray(R.array.month);
                    String[] a1=getResources().getStringArray(R.array.week);
                    String[] a2=getResources().getStringArray(R.array.time);


                    String mn=a[month.getSelectedItemPosition()];
                    String wk=a1[week.getSelectedItemPosition()];
                    String tm=a2[time.getSelectedItemPosition()];

                    String u_id=FirebaseDatabase.getInstance().getReference().push().getKey();

                    Empty_Room_Model empty_room_model=new Empty_Room_Model(wk,day_of_month.getText().toString(),room.getText().toString()
                    ,tm,mn,u_id);


                    FirebaseDatabase.getInstance().getReference("EmptyRooms").child(u_id).setValue(empty_room_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Posted",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }
}
