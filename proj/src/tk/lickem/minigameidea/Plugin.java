package tk.lickem.minigameidea;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import tk.lickem.minigameidea.manager.GameManager;
import tk.lickem.minigameidea.manager.PlayerDataManager;
import tk.lickem.minigameidea.manager.dynamics.DynamicManager;

@Getter
public class Plugin extends JavaPlugin {

    @Getter private static Plugin instance;

    private PlayerDataManager playerManager;
    private GameManager gameManager;

    public void onEnable() {
        instance = this;
        playerManager = new PlayerDataManager();
        gameManager = new GameManager();
        getServer().getConsoleSender().sendMessage("Plugin Enabled");

        DynamicManager.init();
    }

    public void onDisable() {
        instance = null;
        getServer().getConsoleSender().sendMessage("Plugin Disabled");
    }
}
