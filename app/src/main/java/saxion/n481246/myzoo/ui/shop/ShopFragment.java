package saxion.n481246.myzoo.ui.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;
import saxion.n481246.myzoo.ShopItem;
import saxion.n481246.myzoo.ui.shop.dialog.AddAnimal;

public class ShopFragment extends Fragment {

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_shop, container, false);
            GridView gridView = view.findViewById(R.id.grid_view);
            gridView.setOnItemClickListener((parent, view1, position, id) -> {
                String type = ((TextView) view1.findViewById(R.id.itemName)).getText().toString();
                boolean isAnimal = true;
                ArrayList<ShopItem> shopItems = shopDataItems();
                for (int i = 0; i < shopItems.size(); i++) {
                    if (shopItems.get(i).getItemName().equalsIgnoreCase(type)) {
                        isAnimal = false;
                        break;
                    }
                }

                if (isAnimal) {
                    DialogFragment dialog = new AddAnimal(type);
                    dialog.show(getParentFragmentManager(), "AddAnimal");
                } else {
                    Toast.makeText(getContext(), "ShopItem", Toast.LENGTH_SHORT).show();
                }
            });

            GridViewAdapter adapter = new GridViewAdapter(view.getContext(), shopDataAnimals());
            gridView.setAdapter(adapter);

            ToggleButton tb = view.findViewById(R.id.toggleButton);
            tb.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().equals("Animals")) {
                        GridViewAdapter adapter = new GridViewAdapter(view.getContext(), shopDataAnimals());
                        gridView.setAdapter(adapter);
                    } else {
                        GridViewAdapter adapter = new GridViewAdapter(view.getContext(), shopDataItems());
                        gridView.setAdapter(adapter);
                    }

                }
            });
        }
        return view;
    }

    private ArrayList<ShopItem> shopDataAnimals() {
        ArrayList<ShopItem> list = new ArrayList<>();
        list.add(new ShopItem(getString(R.string.bunny), 50, MainActivity.BUNNY, Color.YELLOW));
        list.add(new ShopItem(getString(R.string.chicken), 50, MainActivity.CHICKEN, Color.CYAN));
        list.add(new ShopItem(getString(R.string.cow), 50, MainActivity.COW, Color.BLUE));
        list.add(new ShopItem(getString(R.string.elephant), 50, MainActivity.ELEPHANT, Color.MAGENTA));
        list.add(new ShopItem(getString(R.string.hippo), 50, MainActivity.HIPPO, Color.GREEN));
        list.add(new ShopItem(getString(R.string.child), 50, MainActivity.CHILD, Color.YELLOW));
        list.add(new ShopItem(getString(R.string.monkey), 50, MainActivity.MONKEY, Color.BLUE));
        list.add(new ShopItem(getString(R.string.pig), 50, MainActivity.PIG, Color.RED));
        list.add(new ShopItem(getString(R.string.tiger), 50, MainActivity.TIGER, Color.LTGRAY));
        list.add(new ShopItem(getString(R.string.turtle), 50, MainActivity.TURTLE, Color.CYAN));
        return list;
    }

    private ArrayList<ShopItem> shopDataItems() {
        ArrayList<ShopItem> list = new ArrayList<>();
        list.add(new ShopItem(getString(R.string.baby_bones), 10, MainActivity.BABY_BONES, Color.WHITE));
        list.add(new ShopItem(getString(R.string.meat), 20, MainActivity.MEAT, Color.BLUE));
        list.add(new ShopItem(getString(R.string.water), 100, MainActivity.WATER, Color.GREEN));
        list.add(new ShopItem(getString(R.string.bread), 30, MainActivity.BREAD, Color.RED));
        list.add(new ShopItem(getString(R.string.donut), 50, MainActivity.DONUT, Color.CYAN));
        list.add(new ShopItem(getString(R.string.pea), 1, MainActivity.PEA, Color.YELLOW));
        return list;
    }
}