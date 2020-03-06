package saxion.n481246.myzoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoveSet {

    private static HashMap<Integer, List<Move>> hashMap = new HashMap<>();

    public MoveSet() {
        if (hashMap.size() == 0) {
            for (Move m : MainActivity.getMoveList()) {
                if (!hashMap.containsKey(m.getMoveType())) {
                    ArrayList<Move> list = new ArrayList<>();
                    list.add(m);
                    hashMap.put(m.getMoveType(), list);
                } else {
                    hashMap.get(m.getMoveType()).add(m);
                }
            }
        }
    }

    public List<Move> getFourMoves() {
        ArrayList<Move> moves = new ArrayList<>();

        // Make sure you have at least one attack move
        int rand2 = (int) (Math.random() * hashMap.get(Move.ATK).size());
        Move move = hashMap.get(Move.ATK).get(rand2);
        moves.add(move);

        // get three random moves
        for (int i = 0; i < 3; i++) {
            int rand = (int) (Math.random() * 6);
            rand2 = (int) (Math.random() * hashMap.get(rand).size());
            move = hashMap.get(rand).get(rand2);
            moves.add(move);
        }

        return moves;
    }
}
