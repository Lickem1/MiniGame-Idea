package tk.lickem.minigameidea.manager;

import org.bukkit.scheduler.BukkitRunnable;
import tk.lickem.minigameidea.Plugin;
import tk.lickem.minigameidea.data.game.Game;
import tk.lickem.minigameidea.data.player.PlayerData;

import java.util.Iterator;
import java.util.LinkedList;

public class GameManager extends BukkitRunnable {

    // A place to store all the games on the server
    private final LinkedList<Game> gamesMap = new LinkedList<>();

    public GameManager() {
        // This is going to run every second ASYNC
        // Please don't run sync
        runTaskTimerAsynchronously(Plugin.getInstance(), 20 ,20);
    }

    public void add(Game game) {
        gamesMap.add(game);
    }

    public void delete(Game game) {
        gamesMap.remove(game);
    }

    public void joinGame(PlayerData player, Game game) {
        game.join(player);
    }

    @Override
    public void run() {

        Iterator<Game> games = gamesMap.iterator();
        while (games.hasNext()) {
            Game g = games.next();
            // No matter what gametick is being used, whatever you have put into the doTick()
            // will be ran every second (KEEP IN MIND)
            g.getGameTick().doTick();
        }
    }
}
