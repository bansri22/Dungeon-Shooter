/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonshooter.animator;


import java.util.Iterator;
//import java.util.List;

import dungeonshooter.entity.property.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import dungeShooter.entity.Bullet;
import dungeShooter.entity.Entity;
import dungeShooter.entity.Player;
//import dungeonshooter.entity.PolyShape;

public class Animator extends AbstractAnimator {

	private Color background = Color.ANTIQUEWHITE;

	
	protected void handle(GraphicsContext gc,long now) {
		updateEntities();
		clearAndFill(gc, background);
		drawEntities(gc);
	}

	//update all entities first and then draw them
	public void updateEntities() {


		map.updateProjectilesList();
		map.projectiles().forEach((projectile) -> projectile.update());
		map.players().forEach((player) -> player.update());
		map.staticShapes().forEach((staticShape) -> staticShape.update());

		if (map.getdrawBounds()) {
			map.projectiles().forEach((projectile) -> projectile.getHitBox().getDrawable().setStroke(Color.RED));
			map.players().forEach((player) -> player.getHitBox().getDrawable().setStroke(Color.RED));
		}

		map.staticShapes().forEach((staticShape) -> {
			processEntityList(map.projectiles().iterator(), staticShape.getHitBox());
			processEntityList(map.players().iterator(), staticShape.getHitBox());
		});
	}
	
	public void processEntityList(Iterator<Entity> iterator, HitBox shapeHitBox ) {
		
		while (iterator.hasNext()){
            Entity entity = iterator.next();
            HitBox bounds = entity.getHitBox();

            if (!map.inmap(bounds)) {
                if (entity instanceof Player) {
                    ((Player) entity).stepBack();
                } else if (entity instanceof Bullet) {
                    iterator.remove();
                }
            } else if (shapeHitBox.intersectBounds(bounds)) {
                if (map.getdrawBounds()) {
                    bounds.getDrawable().setStroke(Color.BLUEVIOLET);
                }

                if (shapeHitBox.intersectFull(bounds)){
                    if (entity instanceof Player){
                        ((Player) entity).stepBack();
                    } else if (entity instanceof Bullet) {
                        iterator.remove();
                    }
                }
            }
        }
    }
		
		
//		while (iterator.hasNext()) {
//			iterator.next();
//			shapeHitBox.getHitBox();
//
//			if (map.inMap( bounds))
//				if 
//		}	
//	}

    
}
