package com.monstertome;

import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.plugins.opponentinfo.OpponentInfoPlugin;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;

public class MonsterTomeOverlay extends Overlay {

    private final Client client;
    private final MonsterTomePlugin monsterTomePlugin;
    private final PanelComponent panelComponent = new PanelComponent();
    private ArrayList<Integer> monsterList = new ArrayList<Integer>();

    @Inject
    private MonsterTomeOverlay(Client client, MonsterTomePlugin monsterTomePlugin){
        setPosition(OverlayPosition.BOTTOM_LEFT);
        this.client = client;
        this.monsterTomePlugin = monsterTomePlugin;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();
        String overlayTitle = "MONSTER";

        panelComponent.getChildren().add(TitleComponent.builder()
            .text(overlayTitle)
            .color(Color.RED)
            .build());
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 80, 0));
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Status:")
                .right(getStatus())
                .build());

        return panelComponent.render(graphics);
    }

    public String getStatus(){
        String status = "Not Collected";
        final Actor actor = monsterTomePlugin.getLastOpponent();
        final NPC enemy;
        if(actor.getName() != null && actor.getHealthScale() > 0){
            if(actor instanceof NPC){
                enemy = (NPC)actor;
                String name = enemy.getName();
                int id = enemy.getId();
                if(monsterList.contains(id)){
                    status = "Collected";
                }
                else if(enemy.getHealthRatio() < 50){
                    status = "Collecting";
                }
                else if(enemy.getHealthRatio() < 10){
                    status = "Collected";
                    monsterList.add(id);
                }
            }
        }
        return status;
    }
}
