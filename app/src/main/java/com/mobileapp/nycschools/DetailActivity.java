package com.mobileapp.nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mobileapp.nycschools.models.Detail;
import com.mobileapp.nycschools.models.School;

public class DetailActivity extends AppCompatActivity {

    private School school;
    private Detail detail;

    private TextView schoolTv, detailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get school and detail objects from intent
        school = (School) getIntent().getSerializableExtra("school");
        detail = (Detail) getIntent().getSerializableExtra("detail");

        // Find TextViews
        schoolTv = findViewById(R.id.tv_school_detail);
        detailTv = findViewById(R.id.tv_detail_detail);

        // Set text for school and detail information
        schoolTv.setText(school.getDetailInfo());
        detailTv.setText(detail.getDetailInfo());
    }
}
