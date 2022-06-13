package platform.entities.buildings;

import platform.entities.Player;
import platform.utils.Ressources;

public class City extends Settlement {
    public City(Player owner) {
        super(owner);
    }

    @Override
    public void harnessRessource(Ressources r){
        owner.addRessource(r);
        owner.addRessource(r);
    }
}
