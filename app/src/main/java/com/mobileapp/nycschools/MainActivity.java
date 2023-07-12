package com.mobileapp.nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobileapp.nycschools.adapters.SchoolAdapter;
import com.mobileapp.nycschools.models.Detail;
import com.mobileapp.nycschools.models.School;
import com.mobileapp.nycschools.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String schoolDataAPI = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json";
    private String detailDataAPI = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json";
    private List<School> schoolList = new ArrayList<>();
    private List<Detail> detailList = new ArrayList<>();




    RequestQueue queue;

    private ListView schoolsLv;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        queue = Volley.newRequestQueue(MainActivity.this);
        schoolsLv = findViewById(R.id.lv_schools_main);

        schoolsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                School school = schoolList.get(position);
                Detail detail = null;
                for (Detail d: detailList) {
                    if(d.dbn.equals(school.dbn)){
                        detail = d;
                    }
                }

                if(detail!=null){
                    startActivity(new Intent(MainActivity.this, DetailActivity.class)
                            .putExtra("school", school)
                            .putExtra("detail", detail));
                }else{
                    Toast.makeText(MainActivity.this, "Data not available", Toast.LENGTH_LONG).show();
                }

            }
        });
        textView = findViewById(R.id.tv_main);
        Utils.showProgressDialog(MainActivity.this, "Getting schools\nPlease wait");
        JsonArrayRequest schoolJsonRequest = new JsonArrayRequest(Request.Method.GET, schoolDataAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject schoolJson = response.getJSONObject(i);

                        if(schoolJson.has("dbn") && schoolJson.has("school_name") && schoolJson.has("overview_paragraph") &&
                                schoolJson.has("location") && schoolJson.has("phone_number") && schoolJson.has("school_email") &&
                                schoolJson.has("website") && schoolJson.has("total_students") && schoolJson.has("city")){
                            schoolList.add(new School(schoolJson.getString("dbn"), schoolJson.getString("school_name"),
                                    schoolJson.getString("overview_paragraph"), schoolJson.getString("location"), schoolJson.getString("phone_number"),
                                    schoolJson.getString("school_email"), schoolJson.getString("website"), schoolJson.getString("total_students"),
                                    schoolJson.getString("city")));
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }


                JsonArrayRequest detailJsonRequest = new JsonArrayRequest(Request.Method.GET, detailDataAPI, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject detailJson = response.getJSONObject(i);
                                if(detailJson.has("dbn") && detailJson.has("num_of_sat_test_takers") && detailJson.has("sat_critical_reading_avg_score") &&
                                        detailJson.has("sat_math_avg_score") && detailJson.has("sat_writing_avg_score")){
                                    detailList.add(new Detail(detailJson.getString("dbn"), detailJson.getString("num_of_sat_test_takers"),
                                            detailJson.getString("sat_critical_reading_avg_score"), detailJson.getString("sat_math_avg_score"), detailJson.getString("sat_writing_avg_score")));

                                }
                         } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }

                        Utils.dismissProgressDialog();
                        setSchoolsLvAdapter();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getLocalizedMessage());
                    }
                });

                queue.add(detailJsonRequest);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getLocalizedMessage());
          }
        });


        queue.add(schoolJsonRequest);

    }

    private void setSchoolsLvAdapter(){
        SchoolAdapter schoolAdapter = new SchoolAdapter(MainActivity.this, (ArrayList<School>) schoolList);
        schoolsLv.setAdapter(schoolAdapter);
    }
}