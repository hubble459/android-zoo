package saxion.n481246.myzoo;

public class Animal {

    private String name;
    private int imageId;
    private String type;
    private String gender;
    private int age;
    private int food;
    private static int idCount;
    private int id;

    public Animal() {
    }

    public Animal(String name, int imageId, String type, String gender) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
        this.gender = gender;
        this.food = 100;
        this.id = idCount++;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void addFood(int foodAmount) {
        this.food += foodAmount;
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

    public int getAge() {
        return age;
    }

    public int getFood() {
        return food;
    }

    public String getGender() {
        return gender;
    }
}
