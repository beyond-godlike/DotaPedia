package com.unava.dia.dotapedia.presenter;

import com.unava.dia.dotapedia.data.model.DotaHero;

/**
 * Created by Dia on 26.10.2018.
 */

public class HeroConstructorPresenter {

    public DotaHero changeStatsStrength(DotaHero tempHero) {
        // пересчитываем статы
        tempHero.currentStrength = tempHero.getStrength() + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentAgility = tempHero.getAgility() + tempHero.currentLvl * tempHero.getAddAg();
        tempHero.currentIntelligence = tempHero.getIntelligence() + tempHero.currentLvl * tempHero.getAddInt();

        // пересчитываем скорость и броню
        tempHero.currentDmg1 = (tempHero.currentLvl * tempHero.getAddSt()) + tempHero.getBaseDamage1() + tempHero.getStrength();
        tempHero.currentDmg2 = (tempHero.currentLvl * tempHero.getAddSt()) + tempHero.getBaseDamage2()+ tempHero.getStrength();

        tempHero.currentArmor = tempHero.getPhysarmor() + (tempHero.currentAgility * 0.16);
        tempHero.magResist = 0.25;

        tempHero.currentSpeed = (int) (tempHero.getSpeed() + (tempHero.currentAgility * 0.05));


        // высчитываем хп
        tempHero.currentStrength = tempHero.getStrength()
                + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentHp = tempHero.getBaseHP() + (tempHero.currentStrength * 22.5);


        // высчитываем мп
        tempHero.currentIntelligence = tempHero.getIntelligence()
                + tempHero.currentLvl * tempHero.getAddInt();
        tempHero.currentMp = tempHero.getBaseMP() + (tempHero.currentIntelligence * 12.0);

        // ЭХП
        tempHero.currentEHP = tempHero.currentHp * (tempHero.currentArmor * 0.06 + 1);
        tempHero.currentEHPm = tempHero.currentHp / (1 - tempHero.magResist);

        return tempHero;
    }

    public DotaHero changeStatsAgility(DotaHero tempHero) {
        // пересчитываем статы
        tempHero.currentStrength = tempHero.getStrength() + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentAgility = tempHero.getAgility() + tempHero.currentLvl * tempHero.getAddAg();
        tempHero.currentIntelligence = tempHero.getIntelligence() + tempHero.currentLvl * tempHero.getAddInt();

        // пересчитываем скорость и броню
        tempHero.currentDmg1 = (tempHero.currentLvl * tempHero.getAddAg()) + tempHero.getBaseDamage1() + tempHero.getAgility();
        tempHero.currentDmg2 = (tempHero.currentLvl * tempHero.getAddAg()) + tempHero.getBaseDamage2() + tempHero.getAgility();

        tempHero.currentArmor = tempHero.getPhysarmor() + (tempHero.currentAgility * 0.2);
        tempHero.magResist = 0.25;

        tempHero.currentSpeed = (int) (tempHero.getSpeed() + (tempHero.currentAgility * 0.063));


        // высчитываем хп
        tempHero.currentStrength = tempHero.getStrength()
                + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentHp = tempHero.getBaseHP() + (tempHero.currentStrength * 18);


        // высчитываем мп
        tempHero.currentIntelligence = tempHero.getIntelligence()
                + tempHero.currentLvl * tempHero.getAddInt();
        tempHero.currentMp = tempHero.getBaseMP() + (tempHero.currentIntelligence * 12.0);

        // ЭХП
        tempHero.currentEHP = tempHero.currentHp * (tempHero.currentArmor * 0.06 + 1);
        tempHero.currentEHPm = tempHero.currentHp / (1 - tempHero.magResist);

        return tempHero;
    }

    public DotaHero changeStatsIntelligence(DotaHero tempHero) {
        // пересчитываем статы
        tempHero.currentStrength = tempHero.getStrength() + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentAgility = tempHero.getAgility() + tempHero.currentLvl * tempHero.getAddAg();
        tempHero.currentIntelligence = tempHero.getIntelligence() + tempHero.currentLvl * tempHero.getAddInt();

        // пересчитываем скорость и броню
        tempHero.currentDmg1 = (tempHero.currentLvl * tempHero.getAddInt()) + tempHero.getBaseDamage1() + tempHero.getIntelligence();
        tempHero.currentDmg2 = (tempHero.currentLvl * tempHero.getAddInt()) + tempHero.getBaseDamage2() + tempHero.getIntelligence();

        tempHero.currentArmor = tempHero.getPhysarmor() + (tempHero.currentAgility * 0.16);
        tempHero.magResist = 0.25;

        tempHero.currentSpeed = (int) (tempHero.getSpeed() + (tempHero.currentAgility * 0.05));


        // высчитываем хп
        tempHero.currentStrength = tempHero.getStrength()
                + tempHero.currentLvl * tempHero.getAddSt();
        tempHero.currentHp = tempHero.getBaseHP() + (tempHero.currentStrength * 18);


        // высчитываем мп
        tempHero.currentIntelligence = tempHero.getIntelligence()
                + tempHero.currentLvl * tempHero.getAddInt();
        tempHero.currentMp = tempHero.getBaseMP() + (tempHero.currentIntelligence * 15.0);

        // ЭХП
        tempHero.currentEHP = tempHero.currentHp * (tempHero.currentArmor * 0.06 + 1);
        tempHero.currentEHPm = tempHero.currentHp / (1 - tempHero.magResist);

        return tempHero;
    }

    public String formatter(double val) {
        String value = (new Integer((int) val).toString());

        return value;
    }

    public String formatterD(double val) {
        String value = (new Double(val).toString());

        return value;
    }
}
