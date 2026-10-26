package fr.oreo.modernjobs.models;
import org.bukkit.configuration.ConfigurationSection;
import java.util.List;
public class LevelUpEffect {
    private final boolean firework; private final String sound; private final float soundVolume,soundPitch; private final List<String> commands;
    public LevelUpEffect(boolean firework,String sound,float volume,float pitch,List<String> commands){this.firework=firework;this.sound=sound;this.soundVolume=volume;this.soundPitch=pitch;this.commands=List.copyOf(commands);}
    public static LevelUpEffect empty(){return new LevelUpEffect(false,null,1,1,List.of());}
    public static LevelUpEffect fromSection(ConfigurationSection s,LevelUpEffect f){if(s==null)return f;return new LevelUpEffect(s.getBoolean("firework",f.firework),s.getString("sound",f.sound),(float)s.getDouble("sound-volume",f.soundVolume),(float)s.getDouble("sound-pitch",f.soundPitch),s.getStringList("commands").isEmpty()?f.commands:s.getStringList("commands"));}
    public boolean isFirework(){return firework;} public String getSound(){return sound;} public float getSoundVolume(){return soundVolume;} public float getSoundPitch(){return soundPitch;} public List<String> getCommands(){return commands;}
}
