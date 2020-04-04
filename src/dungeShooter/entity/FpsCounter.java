/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.Point;

/**
 *
 * @author bansri
 */
public abstract class FpsCounter implements Entity {

    public double ONE_SECOND = 1000000000L;
    public double HALF_SECOND = ONE_SECOND / 2F;
    private Font fpsFont;
    private String fpsDisplay;
    private int frameCount;
    private double lastTime;
    private Point pos;
    private Sprite sprite;

    public FpsCounter(double x, double y) {

        //fpsFont=Font.font(Font.getDefault().getFamily(),FontWeight.BOLD,26);
        setPos(x, y);
        setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));
        sprite= new Sprite() {
            @Override
            public void draw(GraphicsContext gc) {
                Font f=gc.getFont();
                gc.setFont(fpsFont);
                gc.setFill(getFill());
                gc.fillText(fpsDisplay, x, y);
                gc.setStroke(getStroke());
                gc.setLineWidth(getWidth());
                gc.strokeText(fpsDisplay, x, y);
                gc.setFont(f);
            }
        };
    }

    public void calculateFPS(long now) {
        if (now - lastTime > HALF_SECOND) {
            fpsDisplay = Integer.toString(frameCount * 2);
            frameCount = 0;
            lastTime = now;
        } else {
            frameCount++;
        }
    }

    public FpsCounter setFont(Font font) {
        fpsFont = Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24);
        return this;
    }

    public FpsCounter setPos(double x, double y) {
        if (pos == null) {
            pos = new Point();
        }
        pos.set(x, y);
        return this;
    }

    public void update() {

    }

    public boolean hasHitbox() {
        return false;
    }

    public HitBox getHitBox() {
        return null;
    }

    public boolean isDrawable() {
        return true;
    }

    public Sprite getDrawable() {
        return sprite;
    }

}
