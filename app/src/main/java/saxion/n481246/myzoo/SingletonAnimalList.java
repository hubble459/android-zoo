package saxion.n481246.myzoo;

import java.util.ArrayList;

public class SingletonAnimalList extends ArrayList<Animal> {

    private static final SingletonAnimalList instance = new SingletonAnimalList();

    private SingletonAnimalList() {
        this.animalList();
    }

    public static SingletonAnimalList getInstance() {
        return instance;
    }

    private void animalList() {
        this.add(new Animal("Berry", R.drawable.bunny, "Bunny", "Male"));
        this.add(new Animal("Chuckle", R.drawable.chicken, "Chicken", "Male"));
        this.add(new Animal("Betsy", R.drawable.cow, "Cow", "Female"));
        this.add(new Animal("Trunk", R.drawable.elephant, "Elephant", "Non-Binary"));
        this.add(new Animal("Moto Moto", R.drawable.hippo, "Hippo", "Alpha Male"));
        this.add(new Animal("Child", R.drawable.human_child, "Human Child", "Female"));
        this.add(new Animal("Saru", R.drawable.monkey, "Monkey", "Male"));
        this.add(new Animal("Ur mum", R.drawable.pig, "Pig", "Female"));
        this.add(new Animal("Tigre", R.drawable.tiger, "Tiger", "Male"));
        this.add(new Animal("God", R.drawable.turtle, "Turtle", "Androgynous"));
    }
}
