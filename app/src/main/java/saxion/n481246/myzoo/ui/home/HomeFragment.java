package saxion.n481246.myzoo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;
import saxion.n481246.myzoo.SingletonAnimalList;

public class HomeFragment extends Fragment {

    static final String ANIMAL_ID = "animal_id";
    private ListViewAdapter adapter;
    private View view;
    private ListView listView;
    private ArrayList<Animal> shownList = new ArrayList<>(SingletonAnimalList.getInstance());

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            listView = view.findViewById(R.id.list);
            adapter = new ListViewAdapter(view.getContext(), SingletonAnimalList.getInstance());

            listView.setOnItemLongClickListener((parent, view1, position, id) -> {
                int animalID = Integer.parseInt(((TextView) view1.findViewById(R.id.animalID)).getText().toString());

                Intent intent = new Intent(getContext(), AnimalDetails.class);
                intent.putExtra(ANIMAL_ID, animalID);

                startActivity(intent);
                return true;
            });

            listView.setAdapter(adapter);

            SearchView searchView = view.findViewById(R.id.search_view);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    onQueryTextChange(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    if (query.equals("")) {
                        shownList = new ArrayList<>(SingletonAnimalList.getInstance());
                        refreshList();
                        return true;
                    }

                    query = query.toLowerCase();

                    List<Animal> filteredList = new ArrayList<>();
                    for (Animal a : SingletonAnimalList.getInstance()) {
                        if (a.getName().toLowerCase().contains(query)) {
                            filteredList.add(a);
                        } else if (a.getGender().toLowerCase().contains(query)) {
                            filteredList.add(a);
                        } else if (a.getType().toLowerCase().contains(query)) {
                            filteredList.add(a);
                        } else if (String.valueOf(a.getHP()).contains(query)) {
                            filteredList.add(a);
                        } else if (String.valueOf(a.getLevel()).contains(query)) {
                            filteredList.add(a);
                        }
                    }

                    shownList = new ArrayList<>(filteredList);
                    refreshList();
                    shownList = SingletonAnimalList.getInstance();
                    return true;
                }
            });
        }
        return view;
    }

    private void refreshList() {
        adapter.clear();
        Collections.sort(shownList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        adapter.addAll(shownList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        shownList = new ArrayList<>(SingletonAnimalList.getInstance());
        refreshList();
        refreshCounters();
        super.onResume();
    }

    private void refreshCounters() {
        ((TextView) getActivity().findViewById(R.id.coin_counter)).setText(String.format("%s", MainActivity.getCoins()));
        ((TextView) getActivity().findViewById(R.id.food_counter)).setText(String.format("%s", MainActivity.getFood()));
    }
}