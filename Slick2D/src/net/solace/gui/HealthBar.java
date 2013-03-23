/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.solace.gui;

import net.solace.entity.HealthEntity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * 
 * @author Johan
 */
public class HealthBar extends ProgressBar {

    private static final Color[] healthColors = new Color[] {
            new Color(.8f, .0f, .0f), new Color(.7f, .6f, .1f), new Color(.2f, .9f, .1f)
    };

    private final HealthEntity owner;

    public HealthBar(HealthEntity owner) {
        super(32, 5);
        this.owner = owner;
    }

    @Override
    public float getProgress() {
        return ((float) owner.getHealth()) / owner.getMaxHealth();
    }

    @Override
    protected Color getProgressColor(float progress) {
        if (progress < .3f) {
            return healthColors[0];
        } else if (progress < .6f) {
            return healthColors[1];
        }
        return healthColors[2];
    }

    public void update(GameContainer slickContainer, int deltaMS) {

    }

    public void render(GameContainer slickContainer, Graphics g) {
        if (owner != null && owner.getHealth() < owner.getMaxHealth()) {
            super.render(slickContainer, g, owner.getX(), owner.getY() + 4 - owner.getZ());
        }
    }
}
