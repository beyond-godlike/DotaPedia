package com.unava.dia.dotapedia.network.service;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    public ErrorResponse(String response) {
        String re = response;
    }

    @SerializedName("tracked_until")
    @Expose
    public String trackedUntil;
    @SerializedName("solo_competitive_rank")
    @Expose
    public String soloCompetitiveRank;
    @SerializedName("rank_tier")
    @Expose
    public String rankTier;
    @SerializedName("competitive_rank")
    @Expose
    public String competitiveRank;
    @SerializedName("leaderboard_rank")
    @Expose
    public String leaderboardRank;
    @SerializedName("mmr_estimate")
    @Expose
    public MmrEstimate mmrEstimate;

    public class MmrEstimate {

    }
}
