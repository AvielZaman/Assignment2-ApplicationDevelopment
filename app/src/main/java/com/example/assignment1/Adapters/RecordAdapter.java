package com.example.assignment1.Adapters;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.Models.Record;
import com.example.assignment1.R;
import com.example.assignment1.interfaces.Callback_ScoresItemClicked;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private final ArrayList<Record> records;
    private Callback_ScoresItemClicked scoresItemClicked;

    public RecordAdapter(ArrayList<Record> records) {
        this.records = records;
    }

    public void setRecordCallback(Callback_ScoresItemClicked recordCallback) {
        this.scoresItemClicked = recordCallback;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_record_item, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = getItem(position);
        holder.record_LBL_score.setText(String.valueOf(record.getScoreRecord()));
        holder.record_LBL_name.setText(record.getName());
        holder.record_CARD.setOnClickListener(v -> {
            if (scoresItemClicked != null)
                scoresItemClicked.scoresItemClicked(record);
        });
    }

    @Override
    public int getItemCount() {
        return records == null ? 0 : records.size();
    }

    private Record getItem(int position) {
        return records.get(position);
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        private final CardView record_CARD;
        private final MaterialTextView record_LBL_name;
        private final MaterialTextView record_LBL_score;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            record_CARD = itemView.findViewById(R.id.record_CARD);
            record_LBL_name = itemView.findViewById(R.id.record_LBL_name);
            record_LBL_score = itemView.findViewById(R.id.record_LBL_score);

        }
    }


}

