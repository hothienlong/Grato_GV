package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.MainActivity;
import com.example.grato_gv.Model.truongSubject;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class truongListSubjectAdapter extends RecyclerView.Adapter<truongListSubjectAdapter.SubjectViewHolder> {

    Context context;
    ArrayList<truongSubject> lstSubject;

    public truongListSubjectAdapter(ArrayList<truongSubject> lstSubject) {
        this.lstSubject = lstSubject;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.truong_layout_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        truongSubject truongsubject = lstSubject.get(position);

        holder.btnSubject.setText(truongsubject.getBtnSubject());
        holder.txtClassName.setText(truongsubject.getTxtClassName());
//        holder.btnSubject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ClassFragment.class);
//                context.startActivity(intent);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstSubject == null ? 0 : lstSubject.size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        Button btnSubject;
        TextView txtClassName;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            btnSubject = itemView.findViewById(R.id.btnSubject);
            txtClassName = itemView.findViewById(R.id.txtClassName);
        }
    }
}
