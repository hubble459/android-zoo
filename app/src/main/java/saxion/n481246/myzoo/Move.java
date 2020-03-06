package saxion.n481246.myzoo;

public class Move {
    public static final int ATK = 0;
    public static final int DEF = 1;
    public static final int LOWER_DEF = 2;
    public static final int HP_STEAL = 3;
    public static final int SELF_ATK = 4;
    public static final int UNKNOWN = 5;
    private double miss;
    private int damage;
    private int moveType;
    private int maxPP;
    private int leftPP;
    private String description;
    private String name;

    public Move(String name, int moveType, int damage, int maxPP, String description, double missChance) {
        this.name = name;
        this.moveType = moveType;
        this.damage = damage;
        this.maxPP = maxPP;
        this.leftPP = maxPP;
        this.description = description;
        this.miss = missChance;
    }

    public static String getMoveTypeName(int moveType) {
        switch (moveType) {
            case ATK:
                return "ATTACK";
            case DEF:
                return "DEFENCE";
            case LOWER_DEF:
                return "LOWER DEFENCE";
            case HP_STEAL:
                return "HP STEAL";
            case SELF_ATK:
                return "SELF ATTACK";
            default:
                return "SPECIAL";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoveType() {
        return moveType;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public int getLeftPP() {
        return leftPP;
    }

    public void setLeftPP(int usableTimes) {
        this.leftPP = usableTimes;
    }

    public String getDescription() {
        return description;
    }

    public double getMiss() {
        return miss;
    }
}
