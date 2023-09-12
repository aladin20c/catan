package Components;

public class Card {

    public static final String vp ="vp";
    public static final String robber="robber";
    public static final String progress="progress";
    public static final String expansion="expansion";
    public static final String monopoly="monopoly";

    private String name;
    private boolean canBeUsed;

    public Card(String name) {
        this.name = name;
        canBeUsed=false;
    }

    public String getName() {
        return name;
    }
    public boolean canBeUsed() {return canBeUsed;}
    public void setCanBeUsed(boolean canBeUsed) {this.canBeUsed = canBeUsed;}
}
