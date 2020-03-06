package saxion.n481246.myzoo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import saxion.n481246.myzoo.ui.breeding.BreedingFragment;
import saxion.n481246.myzoo.ui.home.HomeFragment;
import saxion.n481246.myzoo.ui.map.MapFragment;
import saxion.n481246.myzoo.ui.notifications.NotificationsFragment;
import saxion.n481246.myzoo.ui.shop.ShopFragment;

public class MainActivity extends AppCompatActivity {

    public static final int BUNNY = R.drawable.bunny;
    public static final int CHICKEN = R.drawable.chicken;
    public static final int COW = R.drawable.cow;
    public static final int ELEPHANT = R.drawable.elephant;
    public static final int HIPPO = R.drawable.hippo;
    public static final int CHILD = R.drawable.human_child;
    public static final int MONKEY = R.drawable.monkey;
    public static final int PIG = R.drawable.pig;
    public static final int TIGER = R.drawable.tiger;
    public static final int TURTLE = R.drawable.turtle;

    public static final int BABY_BONES = R.drawable.bones;
    public static final int WATER = R.drawable.water;
    public static final int MEAT = R.drawable.meat;
    public static final int PEA = R.drawable.pea;
    public static final int DONUT = R.drawable.donut;
    public static final int BREAD = R.drawable.bread;

    private static int coins = 0;
    private static int food = 0;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        MainActivity.coins = coins;
    }

    public static int getFood() {
        return food;
    }

    public static void setFood(int food) {
        MainActivity.food = food;
    }

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        ((TextView) findViewById(R.id.screen_title)).setText(R.string.title_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
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
    };

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

    public static ArrayList<Move> getMoveList() {
        ArrayList<Move> list = new ArrayList<>();
        list.add(new Move("3.1415", Move.LOWER_DEF, 30, 3, "Your intelligence increases! The opponent will be left defenceless.", 0.1));
        list.add(new Move("Boob Flash", Move.LOWER_DEF, 15, 1, "By showing off your nipples, the opponent will be left in a trance.", 0.5));
        list.add(new Move("Bruh Moment", Move.ATK, 5, 10, "Bruh", 0.3));
        list.add(new Move("Cafe KUM", Move.ATK, 5, 10, "Will make the opponent cringe.", 0.1));
        list.add(new Move("Cry", Move.SELF_ATK, 1, -1, "Will do 1 damage to yourself, making the opponent pity you.", 0));
        list.add(new Move("Dig", Move.UNKNOWN, 0, 5, "By digging into the ground, you will be immune to the opponents next move", 0));
        list.add(new Move("Distract", Move.LOWER_DEF, 5, 10, "Will distract your opponent, resulting in a weaker punch.", 0.2));
        list.add(new Move("Emancipation", Move.LOWER_DEF, 50, 1, "Using this move you will make the opponent lose confidence.", 0));
        list.add(new Move("Fake Surrender", Move.ATK, 30, 6, "Stab the opponent in the back when they think it's over", 0.7));
        list.add(new Move("Feminism", Move.ATK, 10, 5, "Will increase attack. Has a 50% chance of being bullshit.", 0.5));
        list.add(new Move("Fornicate", Move.LOWER_DEF, 25, 2, "By fornicating right in front of the opponent, they will freeze in their place.", 0.1));
        list.add(new Move("Hug", Move.LOWER_DEF, 5, -1, "Make opponent lose their will to attack.", 0.2));
        list.add(new Move("Knife", Move.ATK, 20, 5, "Stab a knife in their flesh.", 0));
        list.add(new Move("Leer", Move.LOWER_DEF, 5, 5, "Useful against tough, armored Pokémon.", 0.3));
        list.add(new Move("Make a scene", Move.LOWER_DEF, 5, 10, "Will make your opponent self-conscious", 0.6));
        list.add(new Move("Metronome", Move.UNKNOWN, 0, 5, "Will use a random move", 0));
        list.add(new Move("Nibble", Move.ATK, 2, 30, "Nibble the opponents hp away.", 0));
        list.add(new Move("null", Move.UNKNOWN, 0, 1, "Hidden move! Might delete your opponent...", 0));
        list.add(new Move("Nuzzle", Move.LOWER_DEF, 15, 10, "By pretending to be innocent, their defence will drop.", 0.2));
        list.add(new Move("Pounce", Move.ATK, 5, 10, "Pounce on the opponent.", 0));
        list.add(new Move("Rawr x3", Move.DEF, 10, 30, "That's \"I love you\" in dinosaur", 0));
        list.add(new Move("Richard", Move.UNKNOWN, 0, 1, "Summon Richard to battle for you.", 0.6));
        list.add(new Move("Roll", Move.DEF, 10, 5, "Rolling will make you faster and will increase defence.", 0));
        list.add(new Move("Russian Roulette", Move.UNKNOWN, 5, 10, "Has a 50/50 chance of killing either you or the opponent.", 0));
        list.add(new Move("Spook", Move.LOWER_DEF, 5, 7, "Spook the foe.", 0.1));
        list.add(new Move("Striptease", Move.LOWER_DEF, 5, 10, "Will make your opponent lose their will to fight", 0.2));
        list.add(new Move("Suicide", Move.SELF_ATK, 100, 1, "Will do 100 damage to yourself, resulting in death. Has a 70% success rate.", 0.3));
        list.add(new Move("Suck", Move.HP_STEAL, 10, 5, "Steal 10 HP of the opponent", 0.4));
        list.add(new Move("Tackle", Move.ATK, 5, 20, "Be a normie and tackle.", 0));
        list.add(new Move("Talk it out", Move.UNKNOWN, 0, 1, "Try to use words to make the opponent surrender.", 0));
        list.add(new Move("Thunderbolt", Move.ATK, 20, 5, "The copyright infringement might do more damage.", 0.6));
        return list;
    }

    public static int getImageFromAnimalType(String type) {
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
            case "human child":
                return R.drawable.human_child;
            case "monkey":
                return R.drawable.monkey;
            case "pig":
                return R.drawable.pig;
            case "tiger":
                return R.drawable.tiger;
            default:
                return R.drawable.turtle;
        }
    }

    public void refreshCounters() {
        ((TextView) findViewById(R.id.coin_counter)).setText(String.format("%s", coins));
        ((TextView) findViewById(R.id.food_counter)).setText(String.format("%s", food));
    }
}
