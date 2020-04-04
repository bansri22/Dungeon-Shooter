/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;

import static com.sun.javafx.scene.control.skin.TextFieldSkin.BULLET;
import dungeonshooter.entity.geometry.RectangleBounds;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author bansri
 */
public class Bullet  implements Entity
{
    static final Image Bullet =new Image("file:assets\\bullet\\b_3.png");
    private double angle;
    private Sprite sprite;
    private HitBox hitbox;
    
    public Bullet(double angle,double x,double y)
    {
     this(angle,x,y,6,6);
    }
    public Bullet(double angle,double x,double y,double w,double h)
    {
       
		hitbox = new HitBox();
       hitbox.setBounds(x,y,w,h);
       sprite = new Sprite() {
 private RectangleBounds bounds = hitbox.getBound();
 public void draw( GraphicsContext gc){
 gc.drawImage( Bullet, bounds.x(), bounds.y(), bounds.w(), bounds.h());
 }
};
       
       
        
    }
    public void update()
    {
        double x = Math.cos( Math.toRadians( angle)) * 7;
double y = Math.sin( Math.toRadians( angle)) * 7;
hitbox.translate( x, y);
    }
    public boolean isDrawable()
    {
        return true;
    }
    public String toString()
    {
        return null;
    }
    public boolean hasHitbox()
    {
        return true;
    }
    public HitBox getHitBox()
    {
        return hitbox;
    }
    public Drawable<?> getDrawable()
    {
        return sprite;
    }
    
}
