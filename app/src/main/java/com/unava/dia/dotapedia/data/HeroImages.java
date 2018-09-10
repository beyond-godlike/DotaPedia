package com.unava.dia.dotapedia.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Dia on 10.09.2018.
 */

public class HeroImages extends RealmObject {
    private String heroIcon;
    private String skillIcon1;
    private String skillIcon2;
    private String skillIcon3;
    private String skillIcon4;

    public String getHeroIcon() {
        return heroIcon;
    }

    public void setHeroIcon(String heroIcon) {
        this.heroIcon = heroIcon;
    }

    public String getSkillIcon1() {
        return skillIcon1;
    }

    public void setSkillIcon1(String skillIcon1) {
        this.skillIcon1 = skillIcon1;
    }

    public String getSkillIcon2() {
        return skillIcon2;
    }

    public void setSkillIcon2(String skillIcon2) {
        this.skillIcon2 = skillIcon2;
    }

    public String getSkillIcon3() {
        return skillIcon3;
    }

    public void setSkillIcon3(String skillIcon3) {
        this.skillIcon3 = skillIcon3;
    }

    public String getSkillIcon4() {
        return skillIcon4;
    }

    public void setSkillIcon4(String skillIcon4) {
        this.skillIcon4 = skillIcon4;
    }

}
