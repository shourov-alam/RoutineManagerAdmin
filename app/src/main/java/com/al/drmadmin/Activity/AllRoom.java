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

import com.al.drmadmin.Adapter.AllRoomAdapter;
import com.al.drmadmin.Model.Empty_Room_Model;
import com.al.drmadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllRoom extends AppCompatActivity {

    Spinner sp_week;
    RecyclerView recyclerView;
    Button search;

    LinearLayoutManager llm;
    ArrayList<Empty_Room_Model> list=new ArrayList<>();
    DatabaseReference databaseReference;
    AllRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_room);

        sp_week=findViewById(R.id.wek);
        search=findViewById(R.id.search);


        databaseReference= FirebaseDatabase.getInstance().getReference("EmptyRooms");

        final String[] e=getResources().getStringArray(R.array.week);

        //  setHasOptionsMenu(true);
        recyclerView=findViewById(R.id.myList);
        llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm) ;
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter=new AllRoomAdapter(getApplicationContext(),list);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String we=e[sp_week.getSelectedItemPosition()];

                Query query=databaseReference.orderByChild("week").equalTo(we);

               databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        Empty_Room_Model request = snapshot.getValue(Empty_Room_Model.class);
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

                            Empty_Room_Model model = dataSnapshot1.getValue(Empty_Room_Model.class);

                                list.add(model);

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
