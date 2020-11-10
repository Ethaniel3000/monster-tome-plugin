package com.monstertome;

import net.runelite.api.NPC;
import net.runelite.api.NpcID;

public class Monster {
    private String name;
    private int id;
    private boolean isCollected;

    public Monster(NPC npc){
        name = npc.getName();
        id = npc.getId();
        isCollected = false;
    }
}
