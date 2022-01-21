package tk.lickem.minigameidea.data.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.lickem.minigameidea.Plugin;
import tk.lickem.minigameidea.data.player.PlayerData;
import tk.lickem.minigameidea.manager.PlayerDataManager;
import tk.lickem.minigameidea.manager.dynamics.anno.Init;
import tk.lickem.minigameidea.manager.dynamics.impl.DynamicListener;

@Init
public class ConnectionListener extends DynamicListener {

    private final Plugin plugin = Plugin.getInstance();
    private final PlayerDataManager pdm = plugin.getPlayerManager();

    @EventHandler
    public void join(PlayerJoinEvent e) {
        // Create a set a player profile
        Player player = e.getPlayer();
        PlayerData data = pdm.createProfile(player.getUniqueId());
        data.setPlayer(player);
    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        // Delete the profile
        Player player = e.getPlayer();
        pdm.deleteProfile(player.getUniqueId());
    }
}
