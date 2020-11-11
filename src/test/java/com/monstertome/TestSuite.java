package com.monstertome;

import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import net.runelite.api.Actor;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

public class TestSuite {

    /**
    @Inject
    private Client client;

    @Inject
    private TestSuite(Client client){
        this.client = client;
    }
     */

    @Test
    public void testTest(){
        Assertions.assertTrue(true == true);
    }
    @Test
    public void NPCFetch(){
        MonsterTomePlugin monsterTomePlugin = new MonsterTomePlugin();
        Actor actor = monsterTomePlugin.getLastOpponent();
        Assertions.assertTrue(actor instanceof NPC);
    }
    @Test
    public void MonsterIdTest(){
        Monster monster = new Monster();
        monster.setId(200);
        Assertions.assertTrue(monster.getId() == 200);

    }
    @Test
    public void MonsterEqualsTest(){
        Monster monster1 = new Monster();
        monster1.setName("Goblin");
        monster1.setId(1);
        monster1.setStatus("COLLECTED");
        Monster monster2 = new Monster();
        monster2.setName("Goblin");
        monster2.setId(1);
        monster2.setStatus("COLLECTED");
        Monster differentMonster = new Monster();
        Assertions.assertTrue(monster1.equals(monster2));
        Assertions.assertFalse(monster1.equals(differentMonster));
    }
    @Test
    public void getStatusTest(){
        MonsterTomeOverlay monsterTomeOverlay = null;
        String status = monsterTomeOverlay.getStatus();
        Assertions.assertTrue(status.equals("Not Collected") || status.equals("Collecting") || status.equals("COLLECTED"));

    }
}
