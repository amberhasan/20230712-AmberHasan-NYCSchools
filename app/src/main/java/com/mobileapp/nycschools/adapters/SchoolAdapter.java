package com.mobileapp.nycschools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileapp.nycschools.R;
import com.mobileapp.nycschools.models.School;

import java.util.List;

public class SchoolAdapter extends ArrayAdapter<School> {
    private LayoutInflater inflater;

    public SchoolAdapter(Context context, List<School> schoolList) {
        super(context, 0, schoolList);
        inflater = LayoutInflater.from(context);
    }

    // ViewHolder to improve view recycling and performance
    private static class ViewHolder {
        TextView nameTv;
        TextView infoTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_school, parent, false);

            // Create a ViewHolder and store references to the views
            viewHolder = new ViewHolder();
            viewHolder.nameTv = convertView.findViewById(R.id.tv_name_school);
            viewHolder.infoTv = convertView.findViewById(R.id.tv_info_school);

            // Set the ViewHolder as a tag on the convertView for easy access
            convertView.setTag(viewHolder);
        } else {
            // If convertView is not null, retrieve the ViewHolder from the tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for the current position
        School school = getItem(position);

        // Populate the views with data from the School object
        viewHolder.nameTv.setText(school.getSchoolName());
        viewHolder.infoTv.setText(school.getSchoolInfo());

        // Return the completed view to render on screen
        return convertView;
    }
}
