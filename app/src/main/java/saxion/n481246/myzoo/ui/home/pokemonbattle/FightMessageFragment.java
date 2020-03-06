package saxion.n481246.myzoo.ui.home.pokemonbattle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import saxion.n481246.myzoo.R;

public class FightMessageFragment extends Fragment {
    private View mView;
    private String message;


    FightMessageFragment(String message) {
        this.message = message;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_message_fight, container, false);

        ((TextView) mView.findViewById(R.id.messageText)).setText(message);

        return mView;
    }


}
