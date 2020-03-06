package saxion.n481246.myzoo.ui.home;

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
    private View listItem;

    public ListViewAdapter(@NonNull Context context, @NonNull ArrayList<Animal> list) {
        super(context, 0, list);
        mContext = context;
        animalList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listItem = convertView;
        ViewHolder holder;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, parent, false);
            holder = new ViewHolder();
            holder.image = listItem.findViewById(R.id.animalImage3);
            holder.name = listItem.findViewById(R.id.animal_name);
            holder.type = listItem.findViewById(R.id.animal_type);
            holder.gender = listItem.findViewById(R.id.animal_gender);
            holder.level = listItem.findViewById(R.id.level_animal_counter);
            holder.hp = listItem.findViewById(R.id.hp_animal_counter);
            holder.id = listItem.findViewById(R.id.animalID);
            listItem.setTag(holder);
        } else {
            holder = (ViewHolder) listItem.getTag();
        }

        Animal currentAnimal = animalList.get(position);

        holder.image.setImageResource(currentAnimal.getImageId());
        holder.name.setText(currentAnimal.getName());
        holder.type.setText(currentAnimal.getType());
        holder.gender.setText(currentAnimal.getGender());
        holder.level.setText(String.format("%s", currentAnimal.getLevel()));
        holder.hp.setText(String.format("%s", currentAnimal.getHP()));
        holder.id.setText(String.format("%s", currentAnimal.getId()));

        return listItem;
    }

    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView type;
        TextView gender;
        TextView level;
        TextView hp;
        TextView id;
    }
}
