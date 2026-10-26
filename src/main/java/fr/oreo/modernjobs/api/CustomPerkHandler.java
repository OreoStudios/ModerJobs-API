package fr.oreo.modernjobs.api;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/** Implement only the callbacks needed by a custom perk. */
public interface CustomPerkHandler {
    String getKey();
    default void onBlockBreak(BlockBreakEvent event) {}
    default void onBlockPlace(BlockPlaceEvent event) {}
    default void onInteract(PlayerInteractEvent event) {}
    default void onMobKill(EntityDeathEvent event) {}
    default void onFish(PlayerFishEvent event) {}
    default void onCraft(CraftItemEvent event) {}
    default void onFurnaceExtract(FurnaceExtractEvent event) {}
    default void onDamage(EntityDamageByEntityEvent event) {}
}
