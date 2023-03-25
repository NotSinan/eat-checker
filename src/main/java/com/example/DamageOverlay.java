package com.example;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import java.awt.*;
import java.text.DecimalFormat;

public class DamageOverlay extends Overlay {

    private int totalDamage;

    public DamageOverlay() {
        setPosition(OverlayPosition.BOTTOM_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    public void updateDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }


    @Override
    public Dimension render(Graphics2D graphics) {
        DecimalFormat df = new DecimalFormat("#,###");
        String damageString = "Total damage dealt: " + df.format(totalDamage);
        graphics.setColor(Color.WHITE);
        graphics.drawString(damageString, 20, 20);
        return null;
    }
}
