package com.al.drmadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.al.drmadmin.R;

public class MainActivity extends AppCompatActivity {

    CardView addClass,addEmptyRoom,addClassAll,emptyRoomAll,noti,exam,teacherExamRoutine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addClass=findViewById(R.id.add_class);
        addEmptyRoom=findViewById(R.id.empty_room);
        addClassAll=findViewById(R.id.add_class_all);
        emptyRoomAll=findViewById(R.id.empty_room_all);
        noti=findViewById(R.id.add_notification);
        exam=findViewById(R.id.exam_routine);
        teacherExamRoutine=findViewById(R.id.teacher_exam_routine);


        teacherExamRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TeacherExamRoutine.class));
            }
        });

        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ExamRoutine.class));
            }
        });


        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AddClass.class));
            }
        });


        addEmptyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AddEmptyRoom.class));

            }
        });

        addClassAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllClass.class));
            }
        });

        emptyRoomAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllRoom.class));
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notification.class));
            }
        });

    }
}
