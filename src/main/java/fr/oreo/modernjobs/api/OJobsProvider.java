package fr.oreo.modernjobs.api;

import org.bukkit.Bukkit;

import java.util.Optional;


public final class OJobsProvider {

    private OJobsProvider() {}

    public static Optional<OJobsApi> get() {
        org.bukkit.plugin.Plugin plugin = Bukkit.getPluginManager().getPlugin("ModernJobs");
        if (plugin == null || !plugin.isEnabled()) return Optional.empty();
        try { return Optional.ofNullable((OJobsApi) plugin.getClass().getMethod("getApi").invoke(plugin)); }
        catch (ReflectiveOperationException | ClassCastException exception) { return Optional.empty(); }
    }

    public static OJobsApi getOrThrow() {
        return get().orElseThrow(() -> new IllegalStateException("ModernJobs API is not available"));
    }
}
