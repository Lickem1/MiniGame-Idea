package tk.lickem.minigameidea.data.game.ticks;

import lombok.Getter;
import tk.lickem.minigameidea.data.game.Game;
import tk.lickem.minigameidea.data.game.GameTick;

@Getter
public class OngoingTick implements GameTick {

    private final Game game;

    public OngoingTick(Game game) {
        this.game = game;
    }

    @Override
    public void doTick() {
        // You get the point

    }
}
