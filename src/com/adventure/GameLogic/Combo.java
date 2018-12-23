package com.adventure.GameLogic;

import com.adventure.Entities.Item;

public class Combo {
    private Item subject;
    private Item object;
    private Item result;
    private String message;

    public Combo(Item object, Item subject, Item result, String message) {
        this.object = object;
        this.subject = subject;
        this.result = result;
        this.message = message;
    }

    public Item getSubject() {
        return subject;
    }

    public Item getObject() {
        return object;
    }

    public Item getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
