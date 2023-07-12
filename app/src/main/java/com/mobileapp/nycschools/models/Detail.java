package com.mobileapp.nycschools.models;

import java.io.Serializable;

public class Detail  implements Serializable {
    public String dbn, num_of_sat_test_takers, sat_critical_reading_avg_score, sat_math_avg_score, sat_writing_avg_score;

    public Detail(String dbn, String num_of_sat_test_takers, String sat_critical_reading_avg_score, String sat_math_avg_score, String sat_writing_avg_score) {
        this.dbn = dbn;
        this.num_of_sat_test_takers = num_of_sat_test_takers;
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
        this.sat_math_avg_score = sat_math_avg_score;
        this.sat_writing_avg_score = sat_writing_avg_score;
    }

    public Detail() {
    }

    public String getDetailInfo(){
        return "# of SAT test takers: " + num_of_sat_test_takers
                +"\n\nReading avg score: " + sat_critical_reading_avg_score
                +"\n\nMath avg score: " + sat_math_avg_score
                +"\n\nWriting avg score: " + sat_writing_avg_score;
    }
}
