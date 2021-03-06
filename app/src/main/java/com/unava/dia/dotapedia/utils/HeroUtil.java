package com.unava.dia.dotapedia.utils;

/**
 * Created by Dia on 08.11.2018.
 */

public class HeroUtil {
    static String[] heroList = new String[] {
            "abaddon",
            "alchemist",
            "axe",
            "beastmaster",
            "brewmaster",
            "bristleback",
            "centaur",
            "chaos_knight",
            "rattletrap",
            "doom_bringer",
            "dragon_knight",
            "earth_spirit",
            "earthshaker",
            "elder_titan",
            "huskar",
            "wisp",
            "kunkka",
            "legion_commander",
            "life_stealer",
            "lycan",
            "magnataur",
            "night_stalker",
            "omniknight",
            "phoenix",
            "pudge",
            "sand_king",
            "slardar",
            "spirit_breaker",
            "sven",
            "tidehunter",
            "shredder",
            "tiny",
            "treant",
            "tusk",
            "abyssal_underlord",
            "undying",
            "skeleton_king",
            "antimage",
            "arc_warden",
            "bloodseeker",
            "bounty_hunter",
            "broodmother",
            "clinkz",
            "drow_ranger",
            "ember_spirit",
            "faceless_void",
            "gyrocopter",
            "juggernaut",
            "lone_druid",
            "luna",
            "medusa",
            "meepo",
            "mirana",
            "monkey_king",
            "morphling",
            "naga_siren",
            "nyx_assassin",
            "pangolier",
            "phantom_assassin",
            "phantom_lancer",
            "razor",
            "riki",
            "nevermore",
            "slark",
            "sniper",
            "spectre",
            "templar_assassin",
            "terrorblade",
            "troll_warlord",
            "ursa",
            "vengefulspirit",
            "venomancer",
            "viper",
            "weaver",
            "ancient_apparition",
            "bane",
            "batrider",
            "chen",
            "crystal_maiden",
            "dark_seer",
            "dark_willow",
            "dazzle",
            "death_prophet",
            "disruptor",
            "enchantress",
            "enigma",
            "grimstroke",
            "invoker",
            "jakiro",
            "keeper_of_the_light",
            "leshrac",
            "lich",
            "lina",
            "lion",
            "furion",
            "necrolyte",
            "ogre_magi",
            "oracle",
            "obsidian_destroyer",
            "puck",
            "pugna",
            "queenofpain",
            "rubick",
            "shadow_demon",
            "shadow_shaman",
            "silencer",
            "skywrath_mage",
            "storm_spirit",
            "techies",
            "tinker",
            "visage",
            "warlock",
            "windrunner",
            "winter_wyvern",
            "witch_doctor",
            "zuus"
    };

    static String[] imageList = new String[] {
            "antimage_blink",
            "tiny_toss",
            "tiny_avalanche",
            "tiny_craggy_exterior"

    };

    public static String getHeroName(int heroId) {
        return heroList[heroId];
    }

    public static String getSkillId(int imageId) {
        return imageList[imageId];
    }
}
