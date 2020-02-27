package saxion.n481246.myzoo.ui.shop.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;

public class AddAnimal extends DialogFragment {
    private String type;

    public AddAnimal(String type) {
        this.type = type;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.make_animal, null);

        final RadioButton button = view.findViewById(R.id.other);
        final EditText customBox = view.findViewById(R.id.custom_gender);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    customBox.setVisibility(View.VISIBLE);
                else
                    customBox.setVisibility(View.INVISIBLE);
            }
        });

        builder.setView(view)
                .setPositiveButton("Make", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        makeAnimal(view);
                        MainActivity.coins -= MainActivity.getPriceFromAnimalType(type);
                        Toast.makeText(getContext(), "Animal added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Objects.requireNonNull(getDialog()).cancel();
                    }
                });

        return builder.create();
    }

    public void makeAnimal(View view) {
        String name = ((EditText) view.findViewById(R.id.animal_name)).getText().toString();
        RadioButton checkedButton = view.findViewById(((RadioGroup) view.findViewById(R.id.radio_group)).getCheckedRadioButtonId());
        String gender;
        if (checkedButton.getText().toString().equals("Custom")) {
            gender = ((EditText) view.findViewById(R.id.custom_gender)).getText().toString();
        } else if (checkedButton.getText().toString().equals("Random")) {
            int rand = (int) (Math.random() * 2);
            if (rand == 0) gender = "Male";
            else gender = "Female";
        } else {
            gender = checkedButton.getText().toString();
        }

        int imageId = MainActivity.getIdFromAnimalType(type);

        MainActivity.animalList.add(new Animal(name, imageId, type, gender));
    }
}
