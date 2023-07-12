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

    // Constants
    private static final String SCHOOL_DATA_API = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json";
    private static final String DETAIL_DATA_API = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json";

    // Data lists and adapters
    private List<School> schoolList = new ArrayList<>();
    private List<Detail> detailList = new ArrayList<>();
    private SchoolAdapter schoolAdapter;

    // RequestQueue for API requests
    private RequestQueue requestQueue;

    // UI elements
    private ListView schoolsLv;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Find UI elements
        schoolsLv = findViewById(R.id.lv_schools_main);
        textView = findViewById(R.id.tv_main);

        // Set click listener for ListView items
        schoolsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                School school = schoolList.get(position);
                Detail detail = findDetailByDBN(school.getDbn());

                if (detail != null) {
                    startActivity(new Intent(MainActivity.this, DetailActivity.class)
                            .putExtra("school", school)
                            .putExtra("detail", detail));
                } else {
                    Toast.makeText(MainActivity.this, "Data not available", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Show progress dialog
        Utils.showProgressDialog(this, "Getting schools\nPlease wait");

        // Fetch school data from API
        fetchSchoolData();
    }

    private void fetchSchoolData() {
        // Create JSON request for school data
        JsonArrayRequest schoolJsonRequest = new JsonArrayRequest(Request.Method.GET, SCHOOL_DATA_API, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseSchoolData(response);

                        // Once school data is fetched, fetch detail data from API
                        fetchDetailData();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display error message if request fails
                        textView.setText(error.getLocalizedMessage());
                    }
                });

        // Add school JSON request to the RequestQueue
        requestQueue.add(schoolJsonRequest);
    }

    private void fetchDetailData() {
        // Create JSON request for detail data
        JsonArrayRequest detailJsonRequest = new JsonArrayRequest(Request.Method.GET, DETAIL_DATA_API, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseDetailData(response);

                        // Dismiss progress dialog and set adapter for ListView
                        Utils.dismissProgressDialog();
                        setSchoolsLvAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display error message if request fails
                        textView.setText(error.getLocalizedMessage());
                    }
                });

        // Add detail JSON request to the RequestQueue
        requestQueue.add(detailJsonRequest);
    }

    private void parseSchoolData(JSONArray response) {
        // Parse JSON response for school data
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject schoolJson = response.getJSONObject(i);

                // Check if JSON object has required fields
                if (schoolJson.has("dbn") && schoolJson.has("school_name") && schoolJson.has("overview_paragraph") &&
                        schoolJson.has("location") && schoolJson.has("phone_number") && schoolJson.has("school_email") &&
                        schoolJson.has("website") && schoolJson.has("total_students") && schoolJson.has("city")) {

                    // Create School object and add it to the list
                    School school = new School(schoolJson.getString("dbn"), schoolJson.getString("school_name"),
                            schoolJson.getString("overview_paragraph"), schoolJson.getString("location"), schoolJson.getString("phone_number"),
                            schoolJson.getString("school_email"), schoolJson.getString("website"), schoolJson.getString("total_students"),
                            schoolJson.getString("city"));
                    schoolList.add(school);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseDetailData(JSONArray response) {
        // Parse JSON response for detail data
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject detailJson = response.getJSONObject(i);

                // Check if JSON object has required fields
                if (detailJson.has("dbn") && detailJson.has("num_of_sat_test_takers") && detailJson.has("sat_critical_reading_avg_score") &&
                        detailJson.has("sat_math_avg_score") && detailJson.has("sat_writing_avg_score")) {

                    // Create Detail object and add it to the list
                    Detail detail = new Detail(detailJson.getString("dbn"), detailJson.getString("num_of_sat_test_takers"),
                            detailJson.getString("sat_critical_reading_avg_score"), detailJson.getString("sat_math_avg_score"),
                            detailJson.getString("sat_writing_avg_score"));
                    detailList.add(detail);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Detail findDetailByDBN(String dbn) {
        // Find Detail object in the list based on the DBN value
        for (Detail detail : detailList) {
            if (detail.getDbn().equals(dbn)) {
                return detail;
            }
        }
        return null;
    }

    private void setSchoolsLvAdapter() {
        // Create adapter for ListView and set it
        schoolAdapter = new SchoolAdapter(MainActivity.this, new ArrayList<>(schoolList));
        schoolsLv.setAdapter(schoolAdapter);
    }
}
