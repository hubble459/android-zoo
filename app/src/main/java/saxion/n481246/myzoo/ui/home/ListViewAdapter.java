package saxion.n481246.myzoo.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.R;

public class ListViewAdapter extends ArrayAdapter<Animal> {

    private Context mContext;
    private List<Animal> animalList;

    public ListViewAdapter(@NonNull Context context, @NonNull ArrayList<Animal> list) {
        super(context, 0, list);
        mContext = context;
        animalList = list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, parent, false);

        Animal currentAnimal = animalList.get(position);

        ImageView image = listItem.findViewById(R.id.image_view);
        image.setImageResource(currentAnimal.getImageId());

        TextView name = listItem.findViewById(R.id.animal_name);
        name.setText(currentAnimal.getName());

        TextView type = listItem.findViewById(R.id.animal_type);
        type.setText(currentAnimal.getType());

        TextView gender = listItem.findViewById(R.id.animal_gender);
        gender.setText(currentAnimal.getGender());

        TextView age = listItem.findViewById(R.id.age_animal_counter);
        age.setText(currentAnimal.getAge() + "");

        TextView food = listItem.findViewById(R.id.food_animal_counter);
        food.setText(currentAnimal.getFood() + "");

        TextView id = listItem.findViewById(R.id.animalID);
        id.setText(currentAnimal.getId() + "");

        return listItem;
    }
}
