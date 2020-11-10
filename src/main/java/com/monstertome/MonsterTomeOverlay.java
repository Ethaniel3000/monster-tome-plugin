package com.monstertome;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class MonsterTomeOverlay extends Overlay {

    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private MonsterTomeOverlay(Client client){
        setPosition(OverlayPosition.BOTTOM_LEFT);
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
                .right("FILLSTATUS")
                .build());

        return panelComponent.render(graphics);
    }
}
