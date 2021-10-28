package com.example.updateanddeletedata_activity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Model> list;

    public Myadapter(List<Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.newlayout, null, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.T1.setText(model.getName());
        holder.T2.setText(model.getDob());
        holder.T3.setText(model.getEmail());
        holder.T4.setText(model.getQualification());
        holder.T5.setText(model.getPassword());
        holder.T6.setText(model.getId());



      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {

              AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
              LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
              View view = layoutInflater.inflate(R.layout.updatelayout, null, false);
              builder.setView(view);

              EditText name = view.findViewById(R.id.nameU_id);
              EditText email = view.findViewById(R.id.emailU_id);
              EditText dob = view.findViewById(R.id.dobU_id);
              EditText quali = view.findViewById(R.id.qualiU_id);
              EditText pass = view.findViewById(R.id.passwordU_id);
              Button update = view.findViewById(R.id.update_id);
              Button delete = view.findViewById(R.id.dalete_id);
              DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("CrudeUser").child(model.getId());

              update.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String N=name.getText().toString();
                      String Email=email.getText().toString();
                      String Dob=dob.getText().toString();
                      String Quali=quali.getText().toString();
                      String Pass=pass.getText().toString();
                      Model model1=list.get(holder.getAdapterPosition());

                     Model model2=new Model(N,Email,Dob,Quali,Pass,model1.getId());
                      list.clear();
                     databaseReference.setValue(model2);

                  }


              });
              delete.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      databaseReference.removeValue();
                      list.clear();

                  }
              });






              AlertDialog alertDialog = builder.create();
              alertDialog.show();
              return false;
          }
      });



    }
    @Override
    public int getItemCount() {
        return list.size();
    }


}


