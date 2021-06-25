package com.al.drmadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.al.drmadmin.Model.ClassModel;
import com.al.drmadmin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClass extends AppCompatActivity {

    Spinner subject1,week1,term1,level1,section1,day1,time1;

    EditText teacher_name,teacher_initial,room;

    Button save;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        subject1=findViewById(R.id.subject);
        week1=findViewById(R.id.week);
        term1=findViewById(R.id.term);
        level1=findViewById(R.id.level);
        section1=findViewById(R.id.section);
        day1=findViewById(R.id.shift);
        time1=findViewById(R.id.times);

        teacher_initial=findViewById(R.id.initial);
        teacher_name=findViewById(R.id.teacher);
        room=findViewById(R.id.room);
        save=findViewById(R.id.save);

        databaseReference= FirebaseDatabase.getInstance().getReference("Classes");



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_id=databaseReference.push().getKey();

                if (teacher_name.getText().toString().equals("") || teacher_initial.getText().toString().equals("")
                        || room.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Fill up all the data properly",Toast.LENGTH_LONG).show();

                }else {

                    String [] lv= getResources().getStringArray(R.array.level);
                    String [] trm= getResources().getStringArray(R.array.term);
                    String [] wk= getResources().getStringArray(R.array.week);
                    String [] da= getResources().getStringArray(R.array.shift);
                    String [] sub= getResources().getStringArray(R.array.subjects);
                    String [] tm= getResources().getStringArray(R.array.time);
                    String [] sc= getResources().getStringArray(R.array.section);


                    String lvs= lv[level1.getSelectedItemPosition()];
                    String tmrs= trm[term1.getSelectedItemPosition()];
                    String wks= wk[week1.getSelectedItemPosition()];
                    String das= da[day1.getSelectedItemPosition()];
                    String subs= sub[subject1.getSelectedItemPosition()];
                    String tms= tm[time1.getSelectedItemPosition()];
                    String scs= sc[section1.getSelectedItemPosition()];

                    String mat=wks+das+lvs+tmrs+scs;
                    String mat1=das+lvs+tmrs+scs;


                    ClassModel classModel=new ClassModel(mat1,u_id,mat,tmrs,lvs,scs,das,wks,subs,teacher_name.getText().toString(),
                            teacher_initial.getText().toString().toUpperCase(),tms,room.getText().toString());


                    databaseReference.child(u_id).setValue(classModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}
