package com.unava.dia.dotapedia.data.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by Dia on 27.09.2018.
 */

public class DotaHero extends RealmObject {
    private String name = "";

    private String icon = "";
    private int ultimateLvlUnlock = 0;

    private double strength = 0.0;
    private double agility = 0.0;
    private double intelligence = 0.0;
	
	private double addSt = 0.0;
	private double addAg = 0.0;
	private double addInt = 0.0;
	
	private double baseDamage1 = 0.0;
	private double baseDamage2 = 0.0;

    private double physarmor = 0.0;
    private int speed = 0;

    private String attackType = "";
    private int range = 0;

    private double baseHP = 0.0;
    private double baseMP = 0.0;

    // вещчи
    private String item0 = "";
    private String item1 = "";
    private String item2 = "";
    private String item3 = "";
    private String item4 = "";
    private String item5 = "";

    // таланты
    private String talent10 = "";
    private String talent15 = "";
    private String talent20 = "";
    private String talent25 = "";

    // скелы
    private String skill1 = "";
    private String skill2 = "";
    private String skill3 = "";
    private String skill4 = "";
    private String skill5 = "";
    private String skill6 = "";
    private String ult1 = "";
    private String ult2 = "";
	
	// поля, не относящиеся к реалму
	@Ignore
	public int currentLvl = 1;

    @Ignore
    public double currentAgility = 0.0;

    @Ignore
    public double currentStrength = 0.0;

    @Ignore
    public double currentIntelligence = 0.0;
	
	@Ignore
	public double currentHp = 0.0;
	
	@Ignore
	public double currentMp = 0.0;
	
	@Ignore
	public double currentDmg1 = 0.0;
	
	@Ignore
	public double currentDmg2 = 0.0;
	
	@Ignore
	public int currentSpeed = 0;
	

	
	///////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getUltimateLvlUnlock() {
        return ultimateLvlUnlock;
    }

    public void setUltimateLvlUnlock(int ultimateLvlUnlock) {
        this.ultimateLvlUnlock = ultimateLvlUnlock;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getPhysarmor() {
        return physarmor;
    }

    public void setPhysarmor(double physarmor) {
        this.physarmor = physarmor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getItem0() {
        return item0;
    }

    public void setItem0(String item0) {
        this.item0 = item0;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getTalent10() {
        return talent10;
    }

    public void setTalent10(String talent10) {
        this.talent10 = talent10;
    }

    public String getTalent15() {
        return talent15;
    }

    public void setTalent15(String talent15) {
        this.talent15 = talent15;
    }

    public String getTalent20() {
        return talent20;
    }

    public void setTalent20(String talent20) {
        this.talent20 = talent20;
    }

    public String getTalent25() {
        return talent25;
    }

    public void setTalent25(String talent25) {
        this.talent25 = talent25;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public String getSkill6() {
        return skill6;
    }

    public void setSkill6(String skill6) {
        this.skill6 = skill6;
    }

    public String getUlt1() {
        return ult1;
    }

    public void setUlt1(String ult1) {
        this.ult1 = ult1;
    }

    public String getUlt2() {
        return ult2;
    }

    public void setUlt2(String ult2) {
        this.ult2 = ult2;
    }

    public double getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(double baseHP) {
        this.baseHP = baseHP;
    }

    public double getBaseMP() {
        return baseMP;
    }

    public void setBaseMP(double baseMP) {
        this.baseMP = baseMP;
    }

    public double getAddSt() {
        return addSt;
    }

    public void setAddSt(double addSt) {
        this.addSt = addSt;
    }

    public double getAddAg() {
        return addAg;
    }

    public void setAddAg(double addAg) {
        this.addAg = addAg;
    }

    public double getAddInt() {
        return addInt;
    }

    public void setAddInt(double addInt) {
        this.addInt = addInt;
    }

    public double getBaseDamage1() {
        return baseDamage1;
    }

    public void setBaseDamage1(double baseDamage1) {
        this.baseDamage1 = baseDamage1;
    }

    public double getBaseDamage2() {
        return baseDamage2;
    }

    public void setBaseDamage2(double baseDamage2) {
        this.baseDamage2 = baseDamage2;
    }
}
