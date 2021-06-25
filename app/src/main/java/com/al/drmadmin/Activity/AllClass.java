package com.al.drmadmin.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.al.drmadmin.Adapter.AllClassAdapter;
import com.al.drmadmin.Model.ClassModel;
import com.al.drmadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllClass extends AppCompatActivity {

     Spinner sp_level,sp_term,sp_day,sp_section,sp_week;
     RecyclerView recyclerView;
     Button search;

     LinearLayoutManager llm;
     ArrayList<ClassModel> list=new ArrayList<>();
     DatabaseReference databaseReference;
     AllClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class);

        sp_level=findViewById(R.id.level1);
        sp_term=findViewById(R.id.term1);
        sp_day=findViewById(R.id.shift1);
        sp_section=findViewById(R.id.section1);
        sp_week=findViewById(R.id.week1);

        search=findViewById(R.id.submit);

        adapter=new AllClassAdapter(getApplicationContext(),list);

        recyclerView=findViewById(R.id.myList);
        llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm) ;
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        databaseReference= FirebaseDatabase.getInstance().getReference("Classes");

      //  setHasOptionsMenu(true);

      //  recyclerView.setAdapter(adapter);



        final String[] a=getResources().getStringArray(R.array.level);
        final String[] b=getResources().getStringArray(R.array.term);
        final String[] c=getResources().getStringArray(R.array.section);
        final String[] d=getResources().getStringArray(R.array.shift);
        final String[] e=getResources().getStringArray(R.array.week);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lv=a[sp_level.getSelectedItemPosition()];
                String tm=b[sp_term.getSelectedItemPosition()];
                String se=c[sp_section.getSelectedItemPosition()];
                String sh=d[sp_day.getSelectedItemPosition()];
                String we=e[sp_week.getSelectedItemPosition()];

                final String t=we+sh+lv+tm+se;

                Query query=databaseReference.orderByChild("week").equalTo(we);

                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        ClassModel request = snapshot.getValue(ClassModel.class);
                        request.setUid(snapshot.getKey());
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getUid().equals(request.getUid())) {

                                adapter.notifyItemRangeChanged(i,list.size());
                                list.remove(i);
                                adapter.notifyItemRemoved(i);
                                //  adapter.notifyItemChanged(i);


                                break;
                            }
                        }
                        // adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();

                        if(!snapshot.exists()){

                            Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                        }
                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){

                            ClassModel classModel = dataSnapshot1.getValue(ClassModel.class);

                            if(classModel.getMatch().equals(t)){
                                list.add(classModel);
                            }

                        }


                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        });


    }
}
