package tk.lickem.minigameidea.data.player;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import tk.lickem.minigameidea.Plugin;
import tk.lickem.minigameidea.data.game.Game;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {

    // Simple player data class

    // This is here so i don't have to keep referencing Plugin.getInstance
    private static final Plugin plugin = Plugin.getInstance();

    private final UUID UUID;

    private Player player;
    private PlayerStatus playerStatus = PlayerStatus.LOBBY;
    private String prefix = "[Member] ";
    private Game game;

    public PlayerData(UUID uuid) {
        this.UUID = uuid;
    }

    public boolean isInGame(){
        return game != null;
    }

    public static PlayerData of(UUID uuid) {
        return plugin.getPlayerManager().getProfile(uuid);
    }

    public static PlayerData of(Player player) {
        return plugin.getPlayerManager().getProfile(player.getUniqueId());
    }
}
