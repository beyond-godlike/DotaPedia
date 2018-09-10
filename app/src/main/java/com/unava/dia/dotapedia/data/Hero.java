package com.unava.dia.dotapedia.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Dia on 24.08.2018.
 */
public class Hero implements Parcelable, Serializable {
    public String name = "";

    public String strength = "";
    public String agility = "";
    public String intelligence = "";

    public String baseDamage = "";
    public String armor = "";
    public String speed = "";

    public String history = "";
    public HeroImages images;

    public  Hero(String name, String strength, String agility, String intelligence
    , String baseDamage, String armor, String speed, String history, HeroImages images) {
        this.name = name;

        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;

        this.baseDamage = baseDamage;
        this.armor = armor;
        this.speed = speed;

        this.history = history;
        this.images = images;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
    public void setAgility(String agility) {
        this.agility = agility;
    }
    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }


    public void setDamage(String damage) {
        this.baseDamage = damage;
    }
    public void setArmor(String armor) {
        this.armor = armor;
    }
    public void setSpeed(String speed) {
        this.speed = speed;
    }


    public void setHistory(String history) {
        this.history = history;
    }
    public void setImages(HeroImages images) { this.images = images; }

    //_______________________________________________________________________________________//

    protected Hero(Parcel in) {
        name = in.readString();
        strength = in.readString();
        agility = in.readString();
        intelligence = in.readString();
        baseDamage = in.readString();
        armor = in.readString();
        speed = in.readString();
        history = in.readString();
        images = (HeroImages) in.readParcelable(HeroImages.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(strength);
        dest.writeString(agility);
        dest.writeString(intelligence);
        dest.writeString(baseDamage);
        dest.writeString(armor);
        dest.writeString(speed);
        dest.writeString(history);
        dest.writeParcelable(images, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}