package saxion.n481246.myzoo.ui.home.pokemonbattle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import saxion.n481246.myzoo.Animal;
import saxion.n481246.myzoo.Move;
import saxion.n481246.myzoo.R;

public class FightAttackFragment extends Fragment {
    private View mView;
    private Animal animal;

    public FightAttackFragment(Animal animal) {
        this.animal = animal;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_attack_fight, container, false);

        ListView listView = mView.findViewById(R.id.moveList);
        ArrayList<String> moves = new ArrayList<>();
        for (Move move : animal.getMoveSet()) {
            moves.add(move.getName());
        }
        ArrayAdapter<String> movesAdapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_list_item_1, moves);
        listView.setAdapter(movesAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String move = ((TextView) view.findViewById(android.R.id.text1)).getText().toString();
            for (Move m : animal.getMoveSet()) {
                if (m.getName().equals(move)) {
                    TextView damageText = mView.findViewById(R.id.damageTextView);
                    if (m.getMoveType() == Move.ATK)
                        damageText.setText(String.format("%s DMG", m.getDamage()));
                    else
                        damageText.setText(Move.getMoveTypeName(m.getMoveType()));
                    ((TextView) mView.findViewById(R.id.descriptionTextView)).setText(String.valueOf(m.getDescription()));

                    TextView ppText = mView.findViewById(R.id.usesTextView);
                    if (m.getMaxPP() == -1) {
                        ppText.setText("PP ∞");
                    } else {
                        ppText.setText(String.format("PP %s/%s", m.getLeftPP(), m.getMaxPP()));
                    }

                    Button b = mView.findViewById(R.id.attackButton);
                    b.setVisibility(View.VISIBLE);
                    b.setTag(m);
                }
            }
        });

        return mView;
    }
}
