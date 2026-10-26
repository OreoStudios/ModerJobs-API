package fr.oreo.modernjobs.models;

public class ActionEntry {
    private final String key; private final double xp, money, chance; private final int levelRequirement, maxLevelRequirement;
    public ActionEntry(String key, double xp, double money, double chance, int levelRequirement, int maxLevelRequirement) { this.key=key.toUpperCase(); this.xp=xp; this.money=Math.max(0,money); this.chance=Math.max(0,Math.min(1,chance)); this.levelRequirement=Math.max(0,levelRequirement); this.maxLevelRequirement=maxLevelRequirement>0?Math.max(this.levelRequirement,maxLevelRequirement):-1; }
    public boolean roll(){return chance>=1||Math.random()<=chance;} public String getKey(){return key;} public double getXp(){return xp;} public double getMoney(){return money;} public double getChance(){return chance;} public int getLevelRequirement(){return levelRequirement;} public int getMaxLevelRequirement(){return maxLevelRequirement;} public boolean isAvailableForLevel(int level){return level>=levelRequirement&&(maxLevelRequirement<0||level<=maxLevelRequirement);}
}
