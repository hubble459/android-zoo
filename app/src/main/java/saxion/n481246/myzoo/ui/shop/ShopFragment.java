package saxion.n481246.myzoo.ui.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;

public class ShopFragment extends Fragment {

    private View mView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        mView = view;
        ArrayList<ShopItem> shownList = shopData();

        GridView gridView = view.findViewById(R.id.grid_view);
        GridViewAdapter adapter = new GridViewAdapter(view.getContext(), shownList);
        gridView.setAdapter(adapter);

        return view;
    }

    private ArrayList<ShopItem> shopData() {
        ArrayList<ShopItem> list = new ArrayList<>();
        list.add(new ShopItem(mView.getResources().getString(R.string.bunny), 50, MainActivity.getIdFromAnimalType("bunny"), Color.YELLOW));
        list.add(new ShopItem(mView.getResources().getString(R.string.chicken), 50, MainActivity.getIdFromAnimalType("chicken"), Color.CYAN));
        list.add(new ShopItem(mView.getResources().getString(R.string.cow), 50, MainActivity.getIdFromAnimalType("cow"), Color.BLUE));
        list.add(new ShopItem(mView.getResources().getString(R.string.elephant), 50, MainActivity.getIdFromAnimalType("elephant"), Color.MAGENTA));
        list.add(new ShopItem(mView.getResources().getString(R.string.hippo), 50, MainActivity.getIdFromAnimalType("hippo"), Color.GREEN));
        list.add(new ShopItem(mView.getResources().getString(R.string.child), 50, MainActivity.getIdFromAnimalType("child"), Color.YELLOW));
        list.add(new ShopItem(mView.getResources().getString(R.string.monkey), 50, MainActivity.getIdFromAnimalType("monkey"), Color.BLUE));
        list.add(new ShopItem(mView.getResources().getString(R.string.pig), 50, MainActivity.getIdFromAnimalType("pig"), Color.RED));
        list.add(new ShopItem(mView.getResources().getString(R.string.tiger), 50, MainActivity.getIdFromAnimalType("tiger"), Color.LTGRAY));
        list.add(new ShopItem(mView.getResources().getString(R.string.turtle), 50, MainActivity.getIdFromAnimalType("turtle"), Color.CYAN));
        return list;
    }
}