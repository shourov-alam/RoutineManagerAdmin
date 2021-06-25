package com.al.drmadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.al.drmadmin.Model.ClassModel;
import com.al.drmadmin.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllClassAdapter extends RecyclerView.Adapter<AllClassAdapter.AllClaasViewHolder>{

    ArrayList<ClassModel> arrayList;
    Context context;


    public AllClassAdapter(Context context, ArrayList<ClassModel> arrayList) {

        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AllClaasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.all_class_sample,parent,false);

        return new AllClaasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllClaasViewHolder holder, final int position) {

        holder.c_name.setText(arrayList.get(position).getCourse_name());
        holder.room.setText(arrayList.get(position).getRoom());
        holder.teacher.setText(arrayList.get(position).getTeacher_name());
        holder.time.setText(arrayList.get(position).getTime());
holder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseDatabase.getInstance().getReference("Classes").child(arrayList.get(position).getUid()).removeValue();
    }
});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AllClaasViewHolder extends RecyclerView.ViewHolder{
    TextView c_name,room,teacher,time;
    ImageView img;

        public AllClaasViewHolder(@NonNull View itemView) {
            super(itemView);

            c_name=itemView.findViewById(R.id.course_name);
            room=itemView.findViewById(R.id.rm);
            teacher=itemView.findViewById(R.id.teachers);
            time=itemView.findViewById(R.id.times);
            img=itemView.findViewById(R.id.delete);


        }
    }
}
