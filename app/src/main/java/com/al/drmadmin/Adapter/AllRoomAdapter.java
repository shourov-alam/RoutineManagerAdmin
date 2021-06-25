package com.al.drmadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.al.drmadmin.Model.Empty_Room_Model;
import com.al.drmadmin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllRoomAdapter  extends RecyclerView.Adapter<AllRoomAdapter.AllRoomViewHolder>{

    ArrayList<Empty_Room_Model> arrayList;
    Context context;

    public AllRoomAdapter(Context context, ArrayList<Empty_Room_Model> arrayList) {

        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AllRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.empty_room_sample,parent,false);

        return new AllRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRoomViewHolder holder, final int position) {

        holder.room.setText(arrayList.get(position).getRoom());
        holder.week.setText(arrayList.get(position).getDate()+" "+arrayList.get(position).getMonth());
        holder.time.setText(arrayList.get(position).getTime());



        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("EmptyRooms").child(arrayList.get(position).getUid()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {

                            }


                        }
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AllRoomViewHolder extends RecyclerView.ViewHolder{
        TextView week,room,time;
        ImageView img;

        public AllRoomViewHolder(@NonNull View itemView) {
            super(itemView);

            week=itemView.findViewById(R.id.wk);
            room=itemView.findViewById(R.id.rm);
            time=itemView.findViewById(R.id.tm);
            img=itemView.findViewById(R.id.dlt);


        }
    }
}
