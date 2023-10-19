package main.app;

import java.util.UUID;

public class Player {
    private final String name;
    private final String id;
    public Player(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
}
