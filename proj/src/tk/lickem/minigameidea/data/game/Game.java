package tk.lickem.minigameidea.data.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import tk.lickem.minigameidea.Plugin;
import tk.lickem.minigameidea.data.player.PlayerData;
import tk.lickem.minigameidea.manager.GameManager;

import java.util.LinkedList;
import java.util.UUID;

@Getter
@Setter
public class Game {

    @Getter(AccessLevel.NONE)
    private final GameManager gm = Plugin.getInstance().getGameManager();

    private final UUID game_ID = UUID.randomUUID();
    private final LinkedList<PlayerData> players = new LinkedList<>();
    private final LinkedList<PlayerData> spectators = new LinkedList<>();

    private GameStatus gameState = GameStatus.WAITING;
    private GameTick gameTick;

    public Game() {
        gm.add(this);
    }

    public void sendMessage(String... message) {
        players.forEach(p -> {
            for(String m : message) p.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', m));
        });
    }

    public void sendMessage(String message) {
        players.forEach(p -> {
            p.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        });
    }

    public void join(PlayerData data) {

        if(gameState == GameStatus.ONGOING) {
            spectators.add(data);
        } else {
            players.add(data);
        }
    }
}
