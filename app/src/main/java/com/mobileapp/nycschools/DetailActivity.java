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

        school = (School) getIntent().getSerializableExtra("school");
        detail = (Detail) getIntent().getSerializableExtra("detail");

        schoolTv = findViewById(R.id.tv_school_detail);
        detailTv = findViewById(R.id.tv_detail_detail);

        schoolTv.setText(school.getDetailInfo());
        detailTv.setText(detail.getDetailInfo());




    }
}