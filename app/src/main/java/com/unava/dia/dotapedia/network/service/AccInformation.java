package com.unava.dia.dotapedia.network.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccInformation {
    @SerializedName("tracked_until")
    @Expose
    public String trackedUntil;

    @SerializedName("solo_competitive_rank")
    @Expose
    public String soloCompetitiveRank;

    @SerializedName("competitive_rank")
    @Expose
    public String competitiveRank;

    @SerializedName("rank_tier")
    @Expose
    public Integer rankTier;

    @SerializedName("leaderboard_rank")
    @Expose
    public Integer leaderboardRank;

    @SerializedName("mmr_estimate")
    @Expose
    public MmrEstimate mmrEstimate;

    @SerializedName("profile")
    @Expose
    public Profile profile;

}
