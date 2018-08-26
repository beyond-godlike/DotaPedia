package com.unava.dia.dotapedia.data;

/**
 * Created by Dia on 24.08.2018.
 */

public class Hero {
    public String name;

    public String strength = "";
    public String agility = "";
    public String intelligence = "";

    public String baseDamage = "";
    public String armor = "";
    public String speed = "";

    public String history;

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

}
