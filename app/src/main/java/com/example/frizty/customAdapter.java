package com.example.frizty;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frizty.Model.model;
import com.example.frizty.ViewHolder.feedbackViewHolder;

import java.util.List;

public class customAdapter extends RecyclerView.Adapter<feedbackViewHolder> {

    private feedbackList listActivity;
    private  List<model>modelList;
    private  Context context;





    public customAdapter(feedbackList listActivity, List<model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;


    }



    @NonNull
    @Override
    public feedbackViewHolder  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_layout,viewGroup,false);

        feedbackViewHolder viewHolder= new feedbackViewHolder((itemView));

        viewHolder.setOnClickListener(new feedbackViewHolder.ClickListener() {



            @Override
            public void onItemClick(View view, int position) {


                String name = modelList.get(position).getName();
                String email = modelList.get(position).getEmail();
                String comment = modelList.get(position).getComment();
                //feedbackList feedbackList = new feedbackList();

                Toast.makeText(listActivity,name+"\n"+email+"\n"+comment,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onItemLongClick(View view, final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);
                String[] options = {"Update","Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0 )
                        {
                             //update
                            String id   = modelList.get(position).getId();
                            String name = modelList.get(position).getName();
                            String email = modelList.get(position).getEmail();
                            String commnet = modelList.get(position).getComment();



                            Intent intent = new Intent(listActivity,feedback.class);

                            intent.putExtra("pId",id);
                            intent.putExtra("pname",name);
                            intent.putExtra("pemail",email);
                            intent.putExtra("pcomment",commnet);

                            listActivity.startActivity(intent);




                        }
                        if (which == 1)
                        {
                            //delete
                            listActivity.deleteData(position);

                        }


                    }
                }).create().show();

            }
        });

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull feedbackViewHolder viewHolder, int i) {

        viewHolder.rname.setText(modelList.get(i).getName());
        viewHolder.remail.setText(modelList.get(i).getEmail());
        viewHolder.rcomment.setText(modelList.get(i).getComment());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
