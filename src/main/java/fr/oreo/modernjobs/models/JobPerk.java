package fr.oreo.modernjobs.models;
import org.bukkit.configuration.ConfigurationSection;
import java.util.List;
public class JobPerk {
    private final int level; private final boolean enabled; private final String featureKey,permission,name,icon; private final List<String> description;
    public JobPerk(int level,boolean enabled,String featureKey,String permission,String name,List<String> description,String icon){this.level=level;this.enabled=enabled;this.featureKey=featureKey;this.permission=permission;this.name=name;this.description=List.copyOf(description);this.icon=icon;}
    public static JobPerk fromSection(int level,ConfigurationSection s){return new JobPerk(level,s.getBoolean("enabled",true),s.getString("feature-key","").trim().toLowerCase(),s.getString("permission","").trim(),s.getString("name","").trim(),s.getStringList("description"),s.getString("icon","NETHER_STAR").trim().toUpperCase());}
    public int getLevel(){return level;} public boolean isEnabled(){return enabled;} public String getFeatureKey(){return featureKey;} public String getPermission(){return permission;} public String getName(){return name;} public List<String> getDescription(){return description;} public String getIcon(){return icon;}
}
