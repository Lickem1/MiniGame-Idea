package tk.lickem.minigameidea.data.game.ticks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import tk.lickem.minigameidea.data.game.Game;
import tk.lickem.minigameidea.data.game.GameStatus;
import tk.lickem.minigameidea.data.game.GameTick;
import tk.lickem.minigameidea.data.player.PlayerData;

public class BestOf3Tick implements GameTick {


    // FOR EXAMPLE
    // How would we use this system in a best of 3 1v1 game
    // Obviously the code below doesn't work, it's just an example but it should be something along these lines

    //Initiate the fields we need
    private final Game game;
    private int countdown = 5;
    private int round = 0;

    private int team1 = 0;
    private int team2 = 0;
    private int duration = 0;

    public BestOf3Tick(Game game) {
        this.game = game;
    }

    @Override
    public void doTick() {

        if(team1 == 3) { // You can change this into however much you want, it can be best of 4, 6, etc.
            // Finish the game
            game.setGameState(GameStatus.FINISH);
            game.sendMessage("Team 1 has won the match!");
        }
        else if(team2 == 3) { // You can change this into however much you want, it can be best of 4, 6, etc.
            // Finish the game
            game.setGameState(GameStatus.FINISH);
            game.sendMessage("Team 2 has won the match!");
        }


        if(game.getGameState() == GameStatus.STARTING) {
            countdown--;

            if(countdown == 0) {
                // Start the match
                game.setGameState(GameStatus.ONGOING);
            }
            else if(countdown <= 5) {
                if(countdown == 1) game.sendMessage("Starting game in " + countdown + " second...");
                else game.sendMessage("Starting game in " + countdown + " seconds...");
            }
        }
        else if(game.getGameState() == GameStatus.ONGOING) {
            duration++;
        }

    }

    // Obviously you'd make a system to handle player deaths
    // For this example im just gonna let team1 win.
    public void reset() {
        team1++;
        round++;
        countdown = 5;
        game.setGameState(GameStatus.STARTING);
        // teleport players back to spawn points
    }

    @EventHandler
    public void move(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        PlayerData data = PlayerData.of(p);

        if(game != null && game.getPlayers().contains(data)) {
            if(game.getGameState() == GameStatus.STARTING) {
                //p.teleport(Arena team spawn location);

            } else if(game.getGameState() == GameStatus.ONGOING) {
                if(e.getTo().getBlock().getType() == Material.WATER) {
                    // Set player dead and reset the match
                    reset();
                }
            }
        }
    }
}
