package saxion.n481246.myzoo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import saxion.n481246.myzoo.ui.breeding.BreedingFragment;
import saxion.n481246.myzoo.ui.home.HomeFragment;
import saxion.n481246.myzoo.ui.map.MapFragment;
import saxion.n481246.myzoo.ui.notifications.NotificationsFragment;
import saxion.n481246.myzoo.ui.shop.ShopFragment;
import saxion.n481246.myzoo.ui.shop.dialog.AddAnimal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static ArrayList<Animal> animalList = new ArrayList<>();
    public static int coins = 0;
    public static int food = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure Bottom NavBar
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_home);

        // Initialize the Coin and Food counter
        refreshCounters();

        // Start on Animals screen
        demoData();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        ((TextView) findViewById(R.id.screen_title)).setText(R.string.title_home);

        Log.d(TAG, "onCreate: Started.");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment;

            TextView textView = findViewById(R.id.screen_title);
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    selectedFragment = new ShopFragment();
                    textView.setText(R.string.title_shop);
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = new NotificationsFragment();
                    textView.setText(R.string.title_notifications);
                    break;
                case R.id.navigation_breeding:
                    selectedFragment = new BreedingFragment();
                    textView.setText(R.string.title_breeding);
                    break;
                case R.id.navigation_map:
                    selectedFragment = new MapFragment();
                    textView.setText(R.string.title_map);
                    break;
                default:
                    selectedFragment = new HomeFragment();
                    textView.setText(R.string.title_home);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    public void buyItem(View view) {
        String type = view.getContentDescription().toString();
        DialogFragment dialog = new AddAnimal(type);
        dialog.show(getSupportFragmentManager(), "AddAnimal");
        refreshCounters();
    }

    public void demoData() {
        animalList.add(new Animal("Berry", R.drawable.bunny, "Bunny", getString(R.string.male)));
        animalList.add(new Animal("Chuckle", R.drawable.chicken, "Chicken", "Male"));
        animalList.add(new Animal("Betsy", R.drawable.cow, "Cow", "Female"));
        animalList.add(new Animal("Trunk", R.drawable.elephant, "Elephant", "Non-Binary"));
        animalList.add(new Animal("Moto Moto", R.drawable.hippo, "Hippo", "Alpha Male"));
        animalList.add(new Animal("Child", R.drawable.human_child, "Human Child", "Female"));
        animalList.add(new Animal("Saru", R.drawable.monkey, "Monkey", "Male"));
        animalList.add(new Animal("Ur mum", R.drawable.pig, "Pig", "Female"));
        animalList.add(new Animal("Tigre", R.drawable.tiger, "Tiger", "Male"));
        animalList.add(new Animal("God", R.drawable.turtle, "Turtle", "Androgynous"));
    }

    public static int getIdFromAnimalType(String type) {
        switch (type.toLowerCase()) {
            case "bunny":
                return R.drawable.bunny;
            case "chicken":
                return R.drawable.chicken;
            case "cow":
                return R.drawable.cow;
            case "elephant":
                return R.drawable.elephant;
            case "hippo":
                return R.drawable.hippo;
            case "child":
                return R.drawable.human_child;
            case "monkey":
                return R.drawable.monkey;
            case "pig":
                return R.drawable.pig;
            case "tiger":
                return R.drawable.tiger;
            case "turtle":
                return R.drawable.turtle;
            default:
                return 0;
        }
    }

    public static int getPriceFromAnimalType(String type) {
        switch (type.toLowerCase()) {
            case "child":
                return 100;
            case "pig":
                return 25;
            default:
                return 50;
        }
    }

    public void refreshCounters() {
        ((TextView) findViewById(R.id.coin_counter)).setText(String.format("%s", coins));
        ((TextView) findViewById(R.id.food_counter)).setText(String.format("%s", food));
    }
}
