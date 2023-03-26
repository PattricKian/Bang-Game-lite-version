package sk.stuba.fei.uim.oop.tiles;

import sk.stuba.fei.uim.oop.player.Player;

public class PlayerObject extends Tile{
    private final Player player;

    public PlayerObject(Player player) {
        this.player = player;
    }

    @Override
    public String print() {
        return player.getName();
    }

    public Player getPlayer() {
        return player;
    }
}
