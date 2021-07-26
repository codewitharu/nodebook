package com.example.mynotestory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter<noteTry> extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  implements Filterable {
   private Context context;
    private ArrayList<NoteTry> notelist;
    private ArrayList<NoteTry> noteexample;
    DBhelper mydb;
    private TextDrawable mDrawableBuilder;
    private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;

    public CustomAdapter(Context context, ArrayList<NoteTry> notelist) {
        this.context = context;
        this.notelist = notelist;
        noteexample= new ArrayList<>(notelist);
    }



    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_show, parent, false);
        ViewHolder vw = new ViewHolder(view);
        return vw;

    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        NoteTry noteTry = notelist.get(position);
        //holder.note_title_text.setText(noteTry.getNametitle());
        holder.setReminderTitle(noteTry.getNametitle());
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    @Override
    public Filter getFilter() {
        Log.d("dbNote","Dhur");
        return exampleFilter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView note_title_text;
        private final ImageView note_colour_image;

        NoteTry noteTry = new NoteTry();

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            note_title_text = (TextView) itemView.findViewById(R.id.texv);
            note_colour_image = (ImageView) itemView.findViewById(R.id.colourimage);


        }

        public void setReminderTitle(String title) {
            note_title_text.setText(title);
            String letter = "A";
            if (title != null && !title.isEmpty()) {
                letter = title.substring(0, 1);
            }
            int color = mColorGenerator.getRandomColor();

            // Create a circular icon consisting of  a random background colour and first letter of title
            mDrawableBuilder = TextDrawable.builder()
                    .buildRound(letter, color);
            note_colour_image.setImageDrawable(mDrawableBuilder);

        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            NoteTry noteTRy = notelist.get(position);
            String name = noteTRy.getNametitle();
            String date = noteTRy.getDatename();
            String description = noteTRy.getDescriptionname();
            Log.d("dbNote", "Clicked " + name);
            Log.d("dbNote", "Clicked " + date);

            Intent intent = new Intent(context, ActivityShow.class);
            //  Toast.makeText(this, noteTRy.getNametitle(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            intent.putExtra("id", noteTRy.getId());
            intent.putExtra("name", noteTRy.getNametitle());
            intent.putExtra("date", noteTRy.getDatename());
            intent.putExtra("description", noteTRy.getDescriptionname());
            context.startActivity(intent);
            ((Activity) context).finish();


        }
    }


  private Filter exampleFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //ArrayList<NoteTry> filteredList= new ArrayList<>();
            List<NoteTry> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(noteexample);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (NoteTry item : noteexample) {
                    if (item.getNametitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results= new FilterResults();
            results.values= filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notelist.clear();
            notelist.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };
   /* private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<NoteTry> filteredList= new ArrayList<>();
            //List<NoteTry> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(noteexample);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (NoteTry item : noteexample) {
                    if (item.getNametitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results= new FilterResults();
            results.values= filteredList;
            return results;


        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notelist.clear();
            notelist.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };*/
}


