package saxion.n481246.myzoo;

import java.util.List;

public class Animal {

    private int level = 1;
    private double hp;
    private int imageId;
    private int id;
    private double defence = 1;
    private String name;
    private String type;
    private String gender;

    private static int idCount;

    private List<Move> moveSet;

    public Animal() {
        this.hp = 100;
        this.id = idCount++;
    }

    public Animal(String name, int imageId, String type, String gender) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
        this.gender = gender;
        this.hp = 100;
        this.id = idCount++;

        // TODO Random Moves
        // ATK = damage points
        // STR = Strength points [%]
        // LSTR = Lower Strength of Opponent [%]
        // DEF = Defense points [%]
        // LDEF = Lower Defense of Opponent [%]
        MoveSet moveSet = new MoveSet();
        this.moveSet = moveSet.getFourMoves();
    }

    public List<Move> getMoveSet() {
        return moveSet;
    }

    public void setMoveSet(List<Move> moveSet) {
        this.moveSet = moveSet;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void addHP(int foodAmount) {
        this.hp += foodAmount;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public double getHP() {
        return hp;
    }

    public String getGender() {
        return gender;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        if (defence < 0.5) defence = 0.5;
        if (defence > 2) defence = 2;
        this.defence = defence;
    }
}
