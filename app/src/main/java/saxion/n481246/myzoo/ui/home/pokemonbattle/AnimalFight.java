package saxion.n481246.myzoo.ui.home.pokemonbattle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.Move;
import saxion.n481246.myzoo.R;
import saxion.n481246.myzoo.SingletonAnimalList;

public class AnimalFight extends AppCompatActivity {
    private Animal myAnimal;
    private Animal opponentAnimal;
    private boolean finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_fight);

        Intent intent = getIntent();
        int myID = intent.getIntExtra("my_id", -1);
        int opponentID = intent.getIntExtra("opponent_id", -1);

        for (Animal a : SingletonAnimalList.getInstance()) {
            if (a.getId() == myID) myAnimal = a;
            if (a.getId() == opponentID) opponentAnimal = a;
        }

        // Opponent init
        TextView opponentNameAndLevel = findViewById(R.id.opponentName);
        opponentNameAndLevel.setText(String.format("%s [Level %s]", opponentAnimal.getName(), opponentAnimal.getLevel()));
        ImageView opponentImage = findViewById(R.id.opponentImage);
        opponentImage.setImageResource(opponentAnimal.getImageId());
        ProgressBar opponentHPBar = findViewById(R.id.opponentHPBar);
        opponentHPBar.setProgress(100);

        // MyAnimal init
        TextView myAnimalNameAndLevel = findViewById(R.id.myAnimalName);
        myAnimalNameAndLevel.setText(String.format("%s [Level %s]", opponentAnimal.getName(), opponentAnimal.getLevel()));
        ImageView myAnimalImage = findViewById(R.id.myAnimalImage);
        myAnimalImage.setImageResource(myAnimal.getImageId());
        ProgressBar myAnimalHPBar = findViewById(R.id.hpBar);
        myAnimalHPBar.setProgress(100);

        // Fragment init
        getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, new MainFightFragment()).commit();
    }

    public void tryToRun(View view) {
        String message;
        finish = false;

        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            message = "You couldn't get away!";
        } else {
            message = "You ran away!";
            finish = true;
        }

        FightMessageFragment fmf = new FightMessageFragment(message);
        getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, fmf).commit();
    }

    /**
     * attack is the on click handler for the attack button on the main panel
     * if you somehow ran out of pp for all your moves, you will automatically use struggle
     *
     * @param view: the attack button
     */
    public void attack(View view) {
        int ppCount = 0;

        for (Move m : myAnimal.getMoveSet()) {
            ppCount += m.getLeftPP();
        }

        if (ppCount <= 0) {
            String message = String.format("%s doesn't have any pp left...\n%s used struggle!", myAnimal.getName(), myAnimal.getName());
            doDamageToFoe(5);
            doDamageToSelf(7);
            getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, new FightMessageFragment(message)).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, new FightAttackFragment(myAnimal)).commit();
        }

    }

    /**
     * After showing a message, the app will wait for a click to continue
     * If finish == true the fight activity will stop
     *
     * @param view: context of message panel
     */
    public void onClickMessageHandler(View view) {
        if (finish) finish();
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, new MainFightFragment()).commit();
        }
    }

    public void attackFoe(View view) {
        Move move = (Move) view.getTag();
        String message;

        if (move.getLeftPP() == 0) {
            message = "You don't have enough PP for this move!";
        } else {
            boolean missed = false;

            if ((move.getMiss() * 10) >= (int) (Math.random() * 10) + 1) {
                missed = true;
            }
            if (move.getMiss() == 0) missed = false;

            if (move.getMaxPP() != -1)
                move.setLeftPP(move.getLeftPP() - 1);

            switch (move.getMoveType()) {
                case Move.ATK:
                    if (missed) {
                        message = String.format("%s missed!", move.getName());
                    } else {
                        doDamageToFoe(move.getDamage());
                        double damageDone = Math.round(move.getDamage() / opponentAnimal.getDefence() * 100) / 100.0;
                        message = String.format("%s HIT! %s took %s hp damage.", move.getName(), opponentAnimal.getName(), damageDone);
                    }
                    break;
                case Move.DEF:
                    if (missed) {
                        message = String.format("%s failed!", move.getName());
                    } else {
                        increaseDef(move.getDamage());
                        message = String.format("%s worked! %s's def raised.", move.getName(), myAnimal.getName());
                    }
                    break;
                case Move.LOWER_DEF:
                    if (missed) {
                        message = String.format("%s failed!", move.getName());
                    } else {
                        decreaseFoeDef(move.getDamage());
                        message = String.format("%s worked! %s lowered %s defence.", move.getName(), opponentAnimal.getName(), (opponentAnimal.getGender().equals(getString(R.string.male)) ? "his" : "her"));
                    }
                    break;
                case Move.HP_STEAL:
                    if (missed) {
                        message = String.format("%s failed!", move.getName());
                    } else {
                        doDamageToFoe(move.getDamage());
                        increaseHPSelf(move.getDamage());
                        message = String.format("%s worked! %s took %s hp", move.getName(), myAnimal.getName(), move.getDamage());
                    }
                    break;
                case Move.SELF_ATK:
                    if (missed) {
                        message = String.format("%s luckily failed!", move.getName());
                    } else {
                        doDamageToSelf(move.getDamage());
                        message = String.format("%s hurt itself!", myAnimal.getName());
                    }
                    break;
                default:
                    if (missed) {
                        message = String.format("%s did not work...", move.getName());
                    } else {
                        message = specialAttack(move);
                    }
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fightFragmentContainer, new FightMessageFragment(message)).commit();
    }

    private void doDamageToFoe(int damage) {
        ProgressBar hpBar = findViewById(R.id.opponentHPBar);
        opponentAnimal.setHp(opponentAnimal.getHP() - damage / opponentAnimal.getDefence());
        hpBar.setProgress((int) opponentAnimal.getHP());
    }

    private void doDamageToSelf(int damage) {
        ProgressBar hpBar = findViewById(R.id.hpBar);
        myAnimal.setHp(myAnimal.getHP() - damage / myAnimal.getDefence());
        hpBar.setProgress((int) myAnimal.getHP());
    }

    private void increaseHPSelf(int hp) {
        if (myAnimal.getHP() >= 100) return;
        ProgressBar hpBar = findViewById(R.id.hpBar);
        myAnimal.setHp(myAnimal.getHP() + hp);
        hpBar.setProgress((int) myAnimal.getHP());
    }

    private void increaseDef(int percent) {
        myAnimal.setDefence(myAnimal.getDefence() * (percent / 100.0 + 1));
        System.out.println(myAnimal.getDefence());
    }

    private void decreaseFoeDef(int percent) {
        opponentAnimal.setDefence(opponentAnimal.getDefence() * (1 - percent / 100.0));
        System.out.println(opponentAnimal.getDefence());
    }

    private String specialAttack(Move move) {
        return "OWO!?";
    }
}
