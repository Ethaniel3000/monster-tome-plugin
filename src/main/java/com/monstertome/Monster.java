package com.monstertome;

import net.runelite.api.NPC;
import net.runelite.api.NpcID;

public class Monster {
    private String name;
    private int id;
    private String status;

    public Monster(){
        name = "default";
        id = 0;
        status = "Not Collected";
    }
    public Monster(NPC npc){
        name = npc.getName();
        id = npc.getId();
        status = "Not Collected";
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    public boolean equals(Monster m){
        if(this.name == m.getName() && this.id == m.getId() && this.status == m.getStatus()){
            return true;
        }
        else {
            return false;
        }
    }
}
