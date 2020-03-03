package saxion.n481246.myzoo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;

public class AnimalDetails extends AppCompatActivity {

    private Animal animal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(HomeFragment.ANIMAL_ID, -1);

        for (Animal a : MainActivity.animalList) {
            if (a.getId() == id) {
                animal = a;
            }
        }

        refreshValues();
    }

    private void refreshValues() {
        ((TextView) findViewById(R.id.animalName2)).setText(animal.getName());
        ((TextView) findViewById(R.id.animalType2)).setText(animal.getType());
        ((TextView) findViewById(R.id.animalGender2)).setText(animal.getGender());
        ((TextView) findViewById(R.id.animalAge2)).setText(String.format("%s", animal.getAge()));
        ((TextView) findViewById(R.id.animalFood2)).setText(String.format("%s", animal.getFood()));
        ((ImageView) findViewById(R.id.animalImage2)).setImageResource(MainActivity.getImageFromAnimalType(animal.getType()));
    }

    public void killAnimal(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm)
                .setMessage(String.format(getString(R.string.confirm_kill), animal.getName()))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.yes, (dialog, whichButton) -> {
                    MainActivity.animalList.remove(animal);
                    Toast.makeText(AnimalDetails.this, String.format(getString(R.string.kill_deleted), animal.getName()), Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton(R.string.cancel, null).show();
    }

    public void feedAnimal(View view) {
        // TODO Check if enough food
        MainActivity.food -= 10;
        animal.addFood(10);
        refreshValues();
    }

    public void genderChangeAnimal(View view) {
        // TODO Make dialog for gender change and use animal.setGender();
        // Check if enough coins
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View genderDialog = layoutInflater.inflate(R.layout.gender_change_dialog, null);

        final EditText editTextCustom = genderDialog.findViewById(R.id.customEditText);
        RadioGroup rg = genderDialog.findViewById(R.id.radioGroup);
        if (animal.getGender().equals("Male")) {
            rg.check(R.id.radioButton1);
        } else if (animal.getGender().equals("Female")) {
            rg.check(R.id.radioButton2);
        } else {
            rg.check(R.id.radioButton3);
            editTextCustom.setHint(animal.getGender());
            editTextCustom.setVisibility(View.VISIBLE);
        }

        ((RadioButton) genderDialog.findViewById(R.id.radioButton3)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editTextCustom.setVisibility(View.VISIBLE);
            } else {
                editTextCustom.setVisibility(View.INVISIBLE);
            }
        });

        new AlertDialog.Builder(this)
                .setTitle(R.string.gender)
                .setView(genderDialog)
                .setPositiveButton(R.string.yes, (dialog, whichButton) -> {
                    RadioButton rb = genderDialog.findViewById(rg.getCheckedRadioButtonId());
                    if (rb.getText().toString().equals(animal.getGender())) return;
                    String newGender;

                    if (!rb.getText().toString().equals(getString(R.string.custom))) {
                        newGender = rb.getText().toString();
                    } else {
                        if (!editTextCustom.getText().toString().equals("")) {
                            newGender = editTextCustom.getText().toString();
                        } else return;
                    }
                    animal.setGender(newGender);
                    refreshValues();
                    MainActivity.coins -= 50;
                })
                .setNegativeButton(R.string.cancel, null).show();
    }

    public void renameAnimal(View view) {
        EditText edittext = new EditText(this);
        edittext.setSingleLine(true);

        new AlertDialog.Builder(this)
                .setTitle("Rename")
                .setMessage("Enter new name")
                .setView(edittext)
                .setPositiveButton(R.string.yes, (dialog, whichButton) -> {
                    String newName = edittext.getText().toString();
                    if (!newName.equals("")) {
                        animal.setName(newName);
                        refreshValues();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void fightWithAnimal(View view) {

    }

    public void playWithAnimal(View view) {

    }
}
