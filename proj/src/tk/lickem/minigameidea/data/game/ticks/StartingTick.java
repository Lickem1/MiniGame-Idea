package tk.lickem.minigameidea.data.game.ticks;

import lombok.Getter;
import tk.lickem.minigameidea.data.game.Game;
import tk.lickem.minigameidea.data.game.GameStatus;
import tk.lickem.minigameidea.data.game.GameTick;

@Getter
public class StartingTick implements GameTick {

    // Initiate the fields we need
    private final Game game;
    private int countdown = 20;

    public StartingTick(Game game) {
        this.game = game;
    }

    @Override
    public void doTick() {

        // Checks if game has enough players to start
        if(game.getPlayers().size() >= 2) {

            if (game.getGameState() == GameStatus.STARTING) {

                // Just a sample countdown system
                // You can do anything you want here
                if(countdown == 20) game.sendMessage("Starting game in " + countdown + " seconds...");
                countdown--;

                if(countdown == 0) {
                    // Once the countdown hits 0, we can switch the gametick
                    game.setGameState(GameStatus.ONGOING);
                    game.setGameTick(new OngoingTick(game));

                } else {
                    if(countdown % 5 == 0) {
                        game.sendMessage("Starting game in " + countdown + " seconds...");

                    } else if(countdown <= 5) {
                        if(countdown == 1) game.sendMessage("Starting game in " + countdown + " second...");
                        else game.sendMessage("Starting game in " + countdown + " seconds...");
                    }
                }

            } else {
                countdown = 20;
                game.setGameState(GameStatus.STARTING);
            }


        } else game.setGameState(GameStatus.WAITING);

    }
}
