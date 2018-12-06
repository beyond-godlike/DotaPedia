package com.unava.dia.dotapedia.data.model;

public class Invoker {
    public String combination = "";

    public String skillBasePath = "img/invoker/";
    public String skillEndPath = ".png";
    public String skillMidpath = "";
    public String fullPath;
    public boolean flag = true;

    public void pushEnd(String qwe)
    {
        if(combination.length() >= 3) combination = combination.substring(1);
        combination = combination + qwe;
    }

    public String getFullPath () {
        generateMidPath();
        fullPath = skillBasePath + skillMidpath + skillEndPath;

        return fullPath;
    }

    public void generateMidPath() {
        if(combination.length() != 3) return;

        if(combination.contains("q") && combination.contains("w") && combination.contains("e"))
        {
            skillMidpath = "qwe";
        }
        else if(combination.equals("qqq")) {
            skillMidpath = "qqq";
        }
        else if(combination.equals("www")) {
            skillMidpath = "www";
        }
        else if(combination.equals("eee")) {
            skillMidpath = "eee";
        }

        else if(count(combination, "q") == 2) {
            if(combination.contains("w")) skillMidpath = "qqw";
            else if(combination.contains("e")) skillMidpath = "qqe";
        }

        else if(count(combination, "w") == 2) {
            if(combination.contains("q")) skillMidpath = "wwq";
            else if(combination.contains("e")) skillMidpath = "wwe";
        }

        else if(count(combination, "e") == 2) {
            if(combination.contains("w")) skillMidpath = "eew";
            else if(combination.contains("q")) skillMidpath = "eeq";
        }

        else skillMidpath = "4";
    }

    public void swapFlag() {
        if(flag == true) flag = false;
        else flag = true;
    }

    public int count(String str, String ch) {
        String temp;
        int counted = 0;

        for(int i = 0; i < str.length( ); i++) {
            temp = Character.toString(str.charAt(i));

            if( temp.equals(ch))
                counted++;
        }
        return counted;
    }
}
