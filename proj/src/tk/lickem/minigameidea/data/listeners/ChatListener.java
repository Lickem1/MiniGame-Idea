package tk.lickem.minigameidea.data.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tk.lickem.minigameidea.data.game.Game;
import tk.lickem.minigameidea.data.player.PlayerData;
import tk.lickem.minigameidea.manager.dynamics.anno.Init;
import tk.lickem.minigameidea.manager.dynamics.impl.DynamicListener;

@Init
public class ChatListener extends DynamicListener {

    // Game chat

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        PlayerData data = PlayerData.of(player);
        Game game = (data.isInGame() ? data.getGame() : null);

        e.setCancelled(true);
        if(game != null) {
            game.sendMessage("(GAME) " + data.getPrefix() + player.getName() + ": " + e.getMessage());

        } else {
            for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.sendMessage(data.getPrefix() + player.getName() + ": " + e.getMessage());
            }
        }
    }
}
