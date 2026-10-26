package fr.oreo.modernjobs.models;
import org.bukkit.configuration.ConfigurationSection;
import java.util.List;
public class LevelReward {
    private final int level; private final double money; private final List<String> commands,items,perks,permissions; private final boolean fireFirework; private final String sound; private final float soundVolume,soundPitch;
    public LevelReward(int level,double money,List<String> commands,List<String> items,List<String> perks,List<String> permissions,boolean fireFirework,String sound,float soundVolume,float soundPitch){this.level=level;this.money=money;this.commands=List.copyOf(commands);this.items=List.copyOf(items);this.perks=List.copyOf(perks);this.permissions=List.copyOf(permissions);this.fireFirework=fireFirework;this.sound=sound;this.soundVolume=soundVolume;this.soundPitch=soundPitch;}
    public static LevelReward fromSection(int level,ConfigurationSection s){return new LevelReward(level,s.getDouble("money"),s.getStringList("commands"),s.getStringList("items"),s.getStringList("perks"),s.getStringList("permissions"),s.getBoolean("firework"),s.getString("sound"),(float)s.getDouble("sound-volume",1),(float)s.getDouble("sound-pitch",1));}
    public int getLevel(){return level;} public double getMoney(){return money;} public List<String> getCommands(){return commands;} public List<String> getItems(){return items;} public List<String> getPerks(){return perks;} public List<String> getPermissions(){return permissions;} public boolean isFireFirework(){return fireFirework;} public String getSound(){return sound;} public float getSoundVolume(){return soundVolume;} public float getSoundPitch(){return soundPitch;}
}
