package com.mobileapp.nycschools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileapp.nycschools.R;
import com.mobileapp.nycschools.models.School;

import java.util.ArrayList;

public class SchoolAdapter extends ArrayAdapter<School> {
    public SchoolAdapter(Context context, ArrayList<School> schoolArrayList) {
        super(context, 0, schoolArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        School school = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_school, parent, false);
        }
        // Lookup view for data population
        TextView nameTv = (TextView) convertView.findViewById(R.id.tv_name_school);
        TextView infoTv = (TextView) convertView.findViewById(R.id.tv_info_school);
        // Populate the data into the template view using the data object
        nameTv.setText(school.school_name);
        infoTv.setText(school.getSchoolInfo());
        // Return the completed view to render on screen
        return convertView;
    }
}