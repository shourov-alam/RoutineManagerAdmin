package com.al.drmadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.al.drmadmin.Model.ExamModel;
import com.al.drmadmin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class ExamRoutine extends AppCompatActivity {

    Spinner level,term,type,week,shift,section,course;

    EditText room,date,time;

    Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_routine);

        level=findViewById(R.id.level1);
        term=findViewById(R.id.term1);
        type=findViewById(R.id.type1);
        week=findViewById(R.id.week1);
        shift=findViewById(R.id.shift1);
        section=findViewById(R.id.section1);
        course=findViewById(R.id.c_name);

        room=findViewById(R.id.r_name);
        date=findViewById(R.id.d_name);
        time=findViewById(R.id.t_name);

        save=findViewById(R.id.save);

        final String[] cr=getResources().getStringArray(R.array.subjects);
        final String[] lv=getResources().getStringArray(R.array.level);
        final String[] tr=getResources().getStringArray(R.array.term);
        final String[] tp=getResources().getStringArray(R.array.type);
        final String[] wk=getResources().getStringArray(R.array.week);
        final String[] sh=getResources().getStringArray(R.array.shift);
        final String[] se=getResources().getStringArray(R.array.section);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cr1=cr[course.getSelectedItemPosition()];
                String lv1=lv[level.getSelectedItemPosition()];
                String tr1=tr[term.getSelectedItemPosition()];
                String tp1=tp[type.getSelectedItemPosition()];
                String wk1=wk[week.getSelectedItemPosition()];
                String sh1=sh[shift.getSelectedItemPosition()];
                String se1=se[section.getSelectedItemPosition()];

                String mt=sh1+lv1+tr1+se1;




                if(room.getText().toString().equals("")
                ||date.getText().toString().equals("")||time.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Fill up all the fields",Toast.LENGTH_LONG).show();

                }else {

                    ExamModel examModel=new ExamModel(cr1,lv1,tr1,se1,sh1,wk1,room.getText().toString(),date.getText().toString(),
                            time.getText().toString(),mt,tp1,"","");

                    FirebaseDatabase.getInstance().getReference("ExamRoutines").child(FirebaseDatabase.getInstance().getReference().push().getKey()).setValue(examModel).addOnSuccessListener(new OnSuccessListener<Void>() {
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
