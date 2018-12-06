package com.unava.dia.dotapedia.network.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("account_id")
    @Expose
    public Integer accountId;
    @SerializedName("personaname")
    @Expose
    public String personaname;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cheese")
    @Expose
    public Integer cheese;
    @SerializedName("steamid")
    @Expose
    public String steamid;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("avatarmedium")
    @Expose
    public String avatarmedium;
    @SerializedName("avatarfull")
    @Expose
    public String avatarfull;
    @SerializedName("profileurl")
    @Expose
    public String profileurl;
    @SerializedName("last_login")
    @Expose
    public String lastLogin;
    @SerializedName("loccountrycode")
    @Expose
    public String loccountrycode;
    @SerializedName("is_contributor")
    @Expose
    public Boolean isContributor;
}
