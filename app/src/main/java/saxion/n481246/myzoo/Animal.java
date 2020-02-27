package saxion.n481246.myzoo;

public class Animal {

    private String name;
    private int imageId;
    private String type;
    private String gender;
    private int age;
    private int food;

    public Animal() {
    }

    public Animal(String name, int imageId, String type, String gender) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
        this.gender = gender;
        this.food = 100;
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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
