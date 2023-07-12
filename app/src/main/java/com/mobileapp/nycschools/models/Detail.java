package com.mobileapp.nycschools.models;

import java.io.Serializable;

public class Detail implements Serializable {
    private String dbn;
    private String numOfSatTestTakers;
    private String satCriticalReadingAvgScore;
    private String satMathAvgScore;
    private String satWritingAvgScore;

    public Detail(String dbn, String numOfSatTestTakers, String satCriticalReadingAvgScore, String satMathAvgScore, String satWritingAvgScore) {
        this.dbn = dbn;
        this.numOfSatTestTakers = numOfSatTestTakers;
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
        this.satMathAvgScore = satMathAvgScore;
        this.satWritingAvgScore = satWritingAvgScore;
    }

    public String getDbn() {
        return dbn;
    }

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    public String getDetailInfo() {
        return "# of SAT test takers: " + numOfSatTestTakers
                + "\nReading avg score: " + satCriticalReadingAvgScore
                + "\nMath avg score: " + satMathAvgScore
                + "\nWriting avg score: " + satWritingAvgScore;
    }
}
