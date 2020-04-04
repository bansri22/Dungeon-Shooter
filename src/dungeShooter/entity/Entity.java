/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;

/**
 *
 * @author bansri
 */
public interface Entity {
    
    public void update();
    public boolean hasHitbox();
    
    public Drawable<?> getDrawable();
   
    public boolean isDrawable();
   
    public HitBox getHitBox();
   
}
