package com.adventure.GameLogic;

import com.adventure.Entities.Item;

import java.util.ArrayList;
import java.util.List;

public class Combinator {
    private List<Combo> combos = new ArrayList<>();

    public Combo findCombo(Item obj, Item subj) {

        for (Combo comb : combos) {
            if (comb.getObject().equals(obj) && comb.getSubject().equals(subj)) {
                System.out.println(comb.getMessage());
                return comb;
            }
        }
        return null;
    }

    void addCombo(Item obj, Item subj, Item result, String message) {
        combos.add(new Combo(obj, subj, result, message));
    }
}
