package com.example.assignment1.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment1.Adapters.RecordAdapter;
import com.example.assignment1.Logic.DataManager;
import com.example.assignment1.Models.Record;
import com.example.assignment1.R;
import com.example.assignment1.Utilities.SharePreferencesManager;
import com.example.assignment1.interfaces.Callback_ScoresItemClicked;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ScoresFragment extends Fragment {

    private RecyclerView main_LST_records;
    private Callback_ScoresItemClicked callbackScoresItemClicked;
    private ArrayList<Record> records;
    private Record newRecordToCheck;


    public ScoresFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scores, container, false);

        findViews(v);
        initViews();
        return v;
    }

    private void initViews() {
        Gson gson = new Gson();
        String recordsAsJson = SharePreferencesManager
                .getInstance()
                .getString("records", "");
        Type recordListType = new TypeToken<ArrayList<Record>>() {
        }.getType();
        records = gson.fromJson(recordsAsJson, recordListType);
        RecordAdapter recordAdapter = new RecordAdapter(records);
        sortRecordsByScore(recordAdapter);
        if(newRecordToCheck != null) {
            checkNewRecord(newRecordToCheck);
        }
        // save the records
        String recordsToJson = gson.toJson(records);
        SharePreferencesManager.getInstance().putString("records", recordsToJson);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_records.setLayoutManager(linearLayoutManager);
        main_LST_records.setAdapter(recordAdapter);
        recordAdapter.setRecordCallback(new Callback_ScoresItemClicked() {
            @Override
            public void scoresItemClicked(Record record) {
                if (callbackScoresItemClicked != null)
                    callbackScoresItemClicked.scoresItemClicked(record);

            }
        });
    }


    private void findViews(View v) {
        main_LST_records = v.findViewById(R.id.main_LST_records);

    }

    public void setCallbackScoresItemClicked(Callback_ScoresItemClicked callbackScoresItemClicked) {
        this.callbackScoresItemClicked = callbackScoresItemClicked;
    }

    private void sortRecordsByScore(RecordAdapter recordAdapter) {
        records.sort(Comparator.comparingInt(Record::getScoreRecord).reversed());

// Notify the adapter that the data has changed
        recordAdapter.notifyDataSetChanged();
    }

    // this function is for checking whether the new record's score is greater-
    // than another score (AFTER THE ARRAY IS SORTED, and if it is , place him instead one of the records
    private void checkNewRecord(Record newRecord) {
        // Define a comparator based on scoreRecord descending order
        Comparator<Record> scoreComparator = (r1, r2) -> Integer.compare(r2.getScoreRecord(), r1.getScoreRecord());

        // Use binary search with the custom comparator
        int index = Collections.binarySearch(records, newRecord, scoreComparator);

        // Adjust index to positive value if not found
        if (index < 0) {
            index = -(index + 1);
        }

        // Insert the new value at the correct position
        records.add(index, newRecord);

        // Remove the last element to maintain the same size
        records.remove(records.size() - 1);


    }

    public void setData(Record recordForFragment) {
        newRecordToCheck = recordForFragment;
    }
}
