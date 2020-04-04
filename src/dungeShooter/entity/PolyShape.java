/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;

import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Point;
import utility.RandUtil;

/**
 *
 * @author bansri
 */
public class PolyShape implements Entity {

    private int pointCount;
    private double[][] points;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private HitBox hitbox;
    private Sprite sprite;

    public PolyShape() {
        hitbox = new HitBox() {
            protected boolean hasIntersectFull() {
                return true;
            }

            protected double[][] getPoints() {
                return points;
            }
        };
        sprite = new Sprite() {
            {
                setFill(new ImagePattern(new Image("file:assets/concrete/dsc_1621.png")));
            }

            public void draw(GraphicsContext gc) {
                gc.setLineWidth(getWidth());
                if (getStroke() != null) {
                    gc.setStroke(getStroke());
                    gc.strokePolygon(points[0], points[1], pointCount);
                }
                if (getFill() != null) {
                    gc.setFill(getFill());
                    gc.fillPolygon(points[0], points[1], pointCount);
                }
            }

        };

    }

    public PolyShape randomize(double centerX, double centerY, double size, int minpoints, int maxPoints) {

      return null;
    }

    public PolyShape setpoints(double... nums) {
        minX = maxX = nums[0];
        minY = maxY = nums[1];
        pointCount = nums.length / 2;
        points = new double[2][pointCount];
        for (int i = 0, j = 0; i < nums.length; j++, i += 2) {
            updateMinMax(nums[i], nums[i + 1]);
            points[0][j] = nums[i];
            points[1][j] = nums[i + 1];
        }
        
        hitbox.setBounds(minX, minY, maxX - minX, maxY - minY);
      
        return this;
    }

    private void updateMinMax(double x, double y) {
        if (x > maxX) {
            maxX = x;
        }
        if (x < minX) {
            minX = x;
        }
        if (y > maxY) {
            maxY = y;
        }
        if (y < minY) {
            minY = y;
        }
    }

    public int pointCount() {
        return pointCount;
    }

    public double[][] points() {
        return points;
    }

    public double pX(int index) {
        return points[0][index];

    }

    public double pY(int index) {
        return points[1][index];
    }

    public void update() {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasHitbox() {
        return true;
    }

    public HitBox getHitBox() {
        return hitbox;
    }

    public boolean isDrawable() {
        return true;
    }

    public Sprite getDrawable() {
        return sprite;
    }

}
