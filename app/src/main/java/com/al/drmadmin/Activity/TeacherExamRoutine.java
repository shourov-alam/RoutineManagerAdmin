package com.al.drmadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.al.drmadmin.R;
import com.al.drmadmin.Model.TeacherExamRoutineModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherExamRoutine extends AppCompatActivity {

    EditText room,date,time,teacher,initial;
    Button save;
    Spinner week,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_exam_routine);


        room=findViewById(R.id.r_name);
        date=findViewById(R.id.d_name);
        time=findViewById(R.id.t_name);
        teacher=findViewById(R.id.teacher_name);
        initial=findViewById(R.id.t_initial);
        type=findViewById(R.id.type1);
        week=findViewById(R.id.week1);


        save=findViewById(R.id.save);

        final String[] wk=getResources().getStringArray(R.array.week);
        final String[] tp=getResources().getStringArray(R.array.type);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String wk1=wk[week.getSelectedItemPosition()];
                String tp1=tp[type.getSelectedItemPosition()];

                if(room.getText().toString().equals("") ||teacher.getText().equals("")||initial.getText().toString().equals("")
                        ||date.getText().toString().equals("")||time.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Fill up all the fields",Toast.LENGTH_LONG).show();

                }else {

                    TeacherExamRoutineModel examModel=new TeacherExamRoutineModel(tp1,wk1,room.getText().toString(),date.getText().toString(),
                            time.getText().toString(),teacher.getText().toString(),initial.getText().toString().toUpperCase());

                    FirebaseDatabase.getInstance().getReference("TeacherExamRoutines").child(FirebaseDatabase.getInstance().getReference().push().getKey()).setValue(examModel).addOnSuccessListener(new OnSuccessListener<Void>() {
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
