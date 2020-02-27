package saxion.n481246.myzoo.ui.home;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.ListViewAdapter;
import saxion.n481246.myzoo.MainActivity;
import saxion.n481246.myzoo.R;

public class HomeFragment extends Fragment {

    private ListViewAdapter adapter;
    private ArrayList<Animal> shownList = new ArrayList<>(MainActivity.animalList);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView listView = view.findViewById(R.id.list);
        adapter = new ListViewAdapter(view.getContext(), shownList);
        adapter.setNotifyOnChange(true);
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "UWU", Toast.LENGTH_SHORT).show();
                listView.showContextMenuForChild(view);
                view.showContextMenu();
                return true;
            }
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
                    List filteredList = Stream.of(shownList).filter(new Predicate<Animal>() {
                        @Override
                        public boolean test(Animal animal) {
                            if (animal.getType().toLowerCase().contains(query2)) return true;
                            if (animal.getName().toLowerCase().contains(query2)) return true;
                            if (String.valueOf(animal.getAge()).contains(query2)) return true;
                            return String.valueOf(animal.getFood()).contains(query2);
                        }
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

        return view;
    }

    private void refreshList() {
        ArrayList<Animal> list = new ArrayList<>(shownList);
        adapter.clear();
        Collections.sort(list, new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        shownList = new ArrayList<>(list);
        adapter.addAll(shownList);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Upload");
        menu.add(0, v.getId(), 0, "Search");
        menu.add(0, v.getId(), 0, "Share");
        menu.add(0, v.getId(), 0, "Bookmark");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(getContext(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}