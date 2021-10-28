package com.example.updateanddeletedata_activity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView T1,T2,T3,T4,T5,T6;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        T1=itemView.findViewById(R.id.name_id);
        T2=itemView.findViewById(R.id.dob_id);
        T3=itemView.findViewById(R.id.email_id);
        T4=itemView.findViewById(R.id.quali_id);
        T5=itemView.findViewById(R.id.pass_id);
        T6=itemView.findViewById(R.id.Id_id);
    }
}
