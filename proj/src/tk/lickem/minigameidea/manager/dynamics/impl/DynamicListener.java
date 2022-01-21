package tk.lickem.minigameidea.manager.dynamics.impl;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tk.lickem.minigameidea.Plugin;

public class DynamicListener implements Listener {

    public DynamicListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Plugin.getInstance());
    }
}
