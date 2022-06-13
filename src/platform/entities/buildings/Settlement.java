package platform.entities.buildings;

import platform.entities.Player;
import platform.utils.Ressources;

public class Settlement extends Building{
    public Settlement(Player owner) {
        super(owner);
    }

    public void harnessRessource(Ressources r){
        owner.addRessource(r);
    }
}
