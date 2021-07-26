package com.example.mynotestory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class REmindAdapter extends RecyclerView.Adapter<REmindAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<RemindNote> relist;
    DBhelper mydb;

    public REmindAdapter(Context context, ArrayList<RemindNote> relist) {
        this.context = context;
        this.relist = relist;
    }

    @Override
    public REmindAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_show, parent, false);
        ViewHolder vw= new ViewHolder(view);
        return vw;
    }

    @Override
    public void onBindViewHolder(REmindAdapter.ViewHolder holder, int position) {
        RemindNote remindNote= relist.get(position);
        holder.tv1.setText(remindNote.getRtitle());
        holder.tv2.setText(remindNote.getRdate());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView im1, im2;
        TextView tv1, tv2;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            im1= itemView.findViewById(R.id.imageView2);
            im2= itemView.findViewById(R.id.image2);
            tv1=itemView.findViewById(R.id.textView);
            tv2= itemView.findViewById(R.id.textview2);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
