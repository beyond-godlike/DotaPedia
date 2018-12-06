package com.unava.dia.dotapedia.network.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MmrEstimate {
    @SerializedName("estimate")
    @Expose
    public Integer estimate;

    @SerializedName("stdDev")
    @Expose
    public Integer stdDev;

    @SerializedName("n")
    @Expose
    public Integer n;
}
