package platform.entities.buildings;

import platform.entities.Player;

public abstract class Building {
    public Player owner;
    public Building(Player owner) {
        this.owner = owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
