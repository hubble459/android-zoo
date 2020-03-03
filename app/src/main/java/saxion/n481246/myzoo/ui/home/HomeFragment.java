package saxion.n481246.myzoo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;

public class HomeFragment extends Fragment {

    public static final String ANIMAL_ID = "animal_id";
    private ListViewAdapter adapter;
    private ArrayList<Animal> shownList = new ArrayList<>(MainActivity.animalList);
    public static final int ANIMAL_MENU = 420;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            final ListView listView = view.findViewById(R.id.list);
            adapter = new ListViewAdapter(view.getContext(), shownList);

            listView.setOnItemLongClickListener((parent, view1, position, id) -> {
                int animalID = Integer.parseInt(((TextView) view1.findViewById(R.id.animalID)).getText().toString());

                Intent intent = new Intent(getContext(), AnimalDetails.class);
                intent.putExtra(ANIMAL_ID, animalID);

                startActivityForResult(intent, ANIMAL_MENU);
                refreshList();
                return true;
            });

            listView.setAdapter(adapter);
            refreshList();

            SearchView searchView = view.findViewById(R.id.search_view);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    query = query.toLowerCase();

                    boolean doesContain = false;
                    for (Animal a : shownList) {
                        if (a.getName().toLowerCase().contains(query)) {
                            doesContain = true;
                            break;
                        }
                        if (a.getType().toLowerCase().contains(query)) {
                            doesContain = true;
                            break;
                        }
                        if (String.valueOf(a.getAge()).contains(query)) {
                            doesContain = true;
                            break;
                        }
                        if (String.valueOf(a.getFood()).contains(query)) {
                            doesContain = true;
                            break;
                        }
                    }

                    final String query2 = query;
                    if (doesContain) {
                        List<Animal> filteredList = Stream.of(shownList).filter(animal -> {
                            if (animal.getType().toLowerCase().contains(query2)) return true;
                            if (animal.getName().toLowerCase().contains(query2)) return true;
                            if (String.valueOf(animal.getAge()).contains(query2)) return true;
                            return String.valueOf(animal.getFood()).contains(query2);
                        }).collect(Collectors.toList());
                        shownList.clear();
                        shownList.addAll(filteredList);
                        refreshList();
                        shownList = new ArrayList<>(MainActivity.animalList);
                    } else {
                        Toast.makeText(getContext(), "No Match found", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    onQueryTextSubmit(newText);
                    return true;
                }
            });
        }
        return view;
    }

    private void refreshList() {
        ArrayList<Animal> list = new ArrayList<>(shownList);
        adapter.clear();
        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        shownList = new ArrayList<>(list);
        adapter.addAll(shownList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        shownList = new ArrayList<>(MainActivity.animalList);
        refreshList();
        refreshCounters();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void refreshCounters() {
        ((TextView) getActivity().findViewById(R.id.coin_counter)).setText(String.format("%s", MainActivity.coins));
        ((TextView) getActivity().findViewById(R.id.food_counter)).setText(String.format("%s", MainActivity.food));
    }
}