/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;

import dungeShooter.CanvasMap;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
//import static jdk.nashorn.tools.ShellFunctions.input;
import utility.Point;

/**
 *
 * @author bansri
 */
public class Player implements Entity{

    private Rotate rotationPlayer;
    private double angle;
    private double playerFrame = 0;
    private double muzzleFrame = 0;
    private Point pos;
    private Point dimension;
    private Point prev;
    private Sprite sprite;
    private HitBox hitBox;
    private CanvasMap map;

    private PlayerInput input;

    public Player(double x, double y, double w, double h) {
        rotationPlayer = new Rotate();
        pos = new Point(x - w / 2, y - 5 / 2);
        prev = new Point(pos);
        dimension = new Point(w, h);
        //hitBox=new HitBox(x,y,w,h);

        sprite = new Sprite() {
            //player and muzzle each have 20 and 16 set of images than can be loaded
            private final Image[] PLAYER = new Image[20];
            private final Image[] MUZZLE = new Image[16];

            {
                //load the images
                for (int i = 0; i < PLAYER.length; i++) {
                    PLAYER[i] = new Image("file:assets\\rifle\\idle\\survivor-idle_rifle_" + i + ".png");
                }
                for (int i = 0; i < MUZZLE.length; i++) {
                    MUZZLE[i] = new Image("file:assets\\muzzle_flashs\\m_" + i + ".png");
                }
            }

            public void draw(GraphicsContext gc) {
                gc.save();
                //rotate gc for drawing
                gc.setTransform(rotationPlayer.getMxx(), rotationPlayer.getMyx(),
                        rotationPlayer.getMxy(), rotationPlayer.getMyy(), rotationPlayer.getTx(), rotationPlayer.getTy());
                //if left click display fire animation
                if (input.leftClicked()) {
                    gc.drawImage(MUZZLE[(int) muzzleFrame], getRifleMuzzleX() - 8, getRifleMuzzleY() - 25, 50, 50);
                    //this number is how fast the next frame of fire animation will be drawn. The higher the faster.
                    muzzleFrame += .5;
                }
                //darw player image
                gc.drawImage(PLAYER[(int) playerFrame], pos.x(), pos.y(), dimension.x(), dimension.y());
                gc.restore();
                // this number is how fast the next frame of player animation will be drawn. The higher the faster.
                playerFrame += 0.25;
                //reset frame counts if reach the max frame
                if (playerFrame >= PLAYER.length) {
                    playerFrame = 0;
                }
                if (muzzleFrame >= MUZZLE.length || !input.leftClicked()) {
                    muzzleFrame = 0;
                }
            }

        };
        //
        double size = h * .74;
        hitBox = new HitBox().setBounds(pos.x() + dimension.x() * .303 - size / 2, pos.y()
                + dimension.y() * .58 - size / 2, size, size);

    }
public PlayerInput getInput()
{
    return input;
}
public void setInput(PlayerInput input)
{
    this.input=input;
}
    public Player setMap(CanvasMap map) {
        this.map=map;
        return this;
    }

    public double getplayercenterX() {
        return pos.x() + dimension.x() * .303;
    }

    public double getplayercenterY() {
        return pos.y() + dimension.y() * .58;
    }

    public double getRifleMuzzleX() {
        return pos.x() + dimension.x() * .93;
    }

    public double getRifleMuzzleY() {
        return pos.y() + dimension.y() * .73;
    }

    public void calculateAngles() {
        // Math.toDegrees(Math.atan2(input(, angle, pos)., angle))
        angle = Math.toDegrees(Math.atan2(input.y() - getplayercenterY(), input.x() - getplayercenterX()));
        rotationPlayer.setAngle(angle);
        rotationPlayer.setPivotX(getplayercenterX());
        rotationPlayer.setPivotY(getplayercenterY());

    }

    public void stepBack() {
        hitBox.undoTranslate();
        pos.move(prev);

    }

    public void update() {
      	calculateAngles();
		
	double dx = input.leftOrRight() * 7;
        double dy = input.upOrDown() * 7;
        
        prev.move(pos);
        hitBox.translate(dx, dy);
        pos.translate(dx, dy);
 
        if (input.leftClicked()){
            Point2D muzzle = rotationPlayer.transform( getRifleMuzzleX(), getRifleMuzzleY()); 
            map.fireBullet( new Bullet( this.angle, muzzle.getX(), muzzle.getY()));
        }
    }

    public boolean isDrawable() {
        return true;
    }

    public boolean hasHitBox() {
        return true;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Drawable<?> getDrawable() {
        return sprite;
    }

    @Override
    public boolean hasHitbox() {
       return true;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
