package dungeShooter;

import dungeShooter.entity.Bullet;
import dungeShooter.entity.Entity;
import java.util.List;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.animator.Animator;
import dungeShooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * this class represents the drawing area. it is backed by {@link Canvas} class.
 * this class itself does not handle any of the drawing. this task is
 * accomplished by the {@link AnimationTimer}.
 *
 * @author Shahriar (Shawn) Emami
 * @version Jan 13, 2019
 */
public class CanvasMap {

    private Canvas map;
    // private IntegerProperty raycount;
    //   private AbstractAnimator animator;
    // private ArrayList<PolyShape> shapes;
    private BooleanProperty drawBounds;
    private BooleanProperty drawFPS;
    private List<Entity> projectiles;
    private List<Entity> players;
    private PolyShape border;
    private Animator animator;
    private List<Entity> buffer;
    private List<PolyShape> staticshapes;

    /**
     * <p>
     * create a {@link Canvas} object call board. it provides the tools to draw
     * in JavaFX. this is also a {@link Node} which means can be added to our
     * JavaFX application. the object needed to draw on a {@link Canvas} is
     * {@link GraphicsContext} which is retrieved using
     * {@link Canvas#getGraphicsContext2D()} method.
     * </p>
     */
    /**
     * create a {@link AbstractAnimator} called animator. {@link AnimationTimer}
     * provides most common functionally needed to draw animations of ray
     * casting.
     */
    /**
     * <p>
     * create an {@link IntegerProperty} called rayCount to keep track of ray
     * count changes.<br>
     * this variable can be initialized with {@link SimpleBooleanProperty}
     * </p>
     *
     * <pre>
     * IntegerProperty i1 = new SimpleIntegerProperty( 1);
     * IntegerProperty i2 = new SimpleIntegerProperty();
     * i1.bind( i2);
     * i2.set( 100);
     * System.out.println( i1.get()); // prints 100
     * </pre>
     * <p>
     * create a getter that returns {@link IntegerProperty and a method that returns {@link IntegerProperty#get()}
     * </p>
     */
    // public BooleanProperty drawLightSource, drawInteresectPoint, drawSectors, drawBounds, drawFPS,drawShapeJoints;
    /**
     * <p>
     * create a set of {@link BooleanProperty}s to track some drawing
     * options.<br>
     * create: drawLightSource, drawIntersectPoint, drawShapeJoints,
     * drawSectors, drawBounds, drawFPS<br>
     * these variables can be initialized with {@link SimpleBooleanProperty}
     * </p>
     *
     * <pre>
     * BooleanProperty b1 = new SimpleBooleanProperty( false);
     * BooleanProperty b2 = new SimpleBooleanProperty();
     * b1.bind( b2);
     * b2.set( true);
     * System.out.println( b1.get()); // prints true
     * </pre>
     * <p>
     * create a getter that returns {@link BooleanProperty and a method that returns {@link BooleanProperty#get()}
     * for each BooleanProperty.
     * </p>
     *
     * @return
     */
    public BooleanProperty drawBoundsProperty() {
        return drawBounds;
    }

    public boolean getdrawBounds() {

        return this.drawBoundsProperty().get();

    }

    public BooleanProperty drawFpspProperty() {
        return drawFPS;
    }

    public boolean getdrawFPS() {

        return this.drawFpspProperty().get();

    }

    public CanvasMap() {
//staticshapes=new List<PolyShape>(400);
        staticshapes = new ArrayList<>(20);
        buffer = new ArrayList<>(20);
        players = new ArrayList<>(20);
        projectiles = new ArrayList<>(20);
        drawBounds = new SimpleBooleanProperty();
        drawFPS = new SimpleBooleanProperty();
        border = new PolyShape().setpoints(0,0,700,0,700,700,0,700);
        border.getDrawable().setFill(new ImagePattern(new Image("file:assets/floor/floor_ground.jpg"), 0, 0, 256, 256, false));
        /**
         *
         */
    map=new Canvas();
    
    }

    public CanvasMap setDrawingCanvas(Canvas map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.map = map;
        map.widthProperty().addListener((obs, oldVal, newVal) -> {
            border.setpoints(0, 0, w(), 0, w(), h(), 0, h());
        });
        map.heightProperty().addListener((obs, oldVal, newVal) -> {
            border.setpoints(0, 0, w(), 0, w(), h(), 0, h());
        });
        border.setpoints(0, 0, w(), 0, w(), h(), 0, h());
        return this;
    }

    public List<PolyShape> staticShapes() {
        return staticshapes;
    }

    public List<Entity> players() {
        return players;
    }

    public List<Entity> projectiles() {
        return projectiles;
    }

    public void addSampleShapes() {

        staticshapes.add(new PolyShape().setpoints(100, 100, 200, 100, 200, 200, 100, 200));
        staticshapes.add(new PolyShape().setpoints(600, 500, 300, 400, 200, 300, 200, 400));
        staticshapes.add(new PolyShape().setpoints(400, 150, 400, 50, 200, 50));
      

    }

    

    public void updateProjectilesList() {
projectiles.addAll(buffer);
		buffer.clear();
    }

    public PolyShape getMapShape() {
        return border;
    }

    public boolean inmap(HitBox hitbox) {
       return border.getHitBox().containsBounds(hitbox);
    }

    /**
     * create a constructor and initialize all class variables.
     */
    /**
   2  * create the property class variables functions here
     *
     *
     */
    /**
     * create a method called setAnimator. set an {@link AbstractAnimator}. if
     * an animator exists {@link CanvasMap#stop()} it and call
     * {@link CanvasMap#removeMouseEvents()}. then set the new animator and call
     * {@link CanvasMap#start()} and {@link CanvasMap#registerMouseEvents()}.
     *
     * @param newAnimator - new {@link AbstractAnimator} object
     * @return the current instance of this object
     */
    public CanvasMap setAnimator(AbstractAnimator newAnimator) {
        if (animator != null) {
            this.stop();
           this.removeMouseEvents();
        }
        this.animator = (Animator) newAnimator;
        this.start();
       this.registerMouseEvents();
        return this;

    }

    /**
     * <p>
     * create a method called registerMouseEvents. register the mouse events for
     * when the mouse is {@link MouseEvent#MOUSE_MOVED} or
     * {@link MouseEvent#MOUSE_DRAGGED}.<br>
     * call {@link CanvasMap#addEventHandler} twice and pass to it
     * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
     * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.</p>
     * <p>
     * a method can be passed directly as an argument if the method signature
     * matches the functional interface. in this example you will pass the
     * animator method using object::method syntax.</p>
     */
    public void registerMouseEvents() {
        map.addEventHandler(MouseEvent.MOUSE_MOVED, (e) -> {
            animator.mouseMoved(e);
        });

        map.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            animator.mouseDragged(e);
        });
    }

    /**
     * <p>
     * create a method called removeMouseEvents. remove the mouse events for
     * when the mouse is {@link MouseEvent#MOUSE_MOVED} or
     * {@link MouseEvent#MOUSE_DRAGGED}.<br>
     * call {@link CanvasMap#addEventHandler} twice and pass to it
     * {@link MouseEvent#MOUSE_DRAGGED}, {@link animator#mouseDragged} and
     * {@link MouseEvent#MOUSE_MOVED}, {@link animator#mouseMoved}.</p>
     * <p>
     * a method can be passed directly as an argument if the method signature
     * matches the functional interface. in this example you will pass the
     * animator method using object::method syntax.</p>
     */
    public void removeMouseEvents() {
        map.removeEventHandler(MouseEvent.MOUSE_MOVED, (e) -> {
            animator.mouseMoved(e);
        });

        map.removeEventHandler(MouseEvent.MOUSE_DRAGGED, animator::mouseDragged);
    }

    /**
     * <p>
     * register the given {@link EventType} and {@link EventHandler}
     * </p>
     *
     * @param <E>
     * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
     * @param handler - a lambda to be used when registered event is triggered.
     */
    public < E extends Event> void addEventHandler(EventType< E> event, EventHandler< E> handler) {
        map.addEventHandler(event, handler);
    }

    /**
     * <p>
     * remove the given {@link EventType} registered with {@link EventHandler}
     * </p>
     *
     * @param <E>
     * @param event - an event such as {@link MouseEvent#MOUSE_MOVED}.
     * @param handler - a lambda to be used when registered event is triggered.
     */
    public < E extends Event> void removeEventHandler(EventType< E> event, EventHandler< E> handler) {
        map.removeEventHandler(event, handler);
    }

    /**
     * create a method called start. start the animator.
     * {@link AnimationTimer#start()}
     */
    /**
     * create a method called stop. stop the animator.
     * {@link AnimationTimer#stop()}
     *
     * @return
     */
    public Canvas getCanvas() {
        return map;
    }

    /**
     * create a method called getCanvas. get the JavaFX {@link Canvas} node
     *
     * @return {@link Canvas} node
     */
    /**
     * create a method called gc. get the {@link GraphicsContext} of
     * {@link Canvas} that allows direct drawing.
     *
     * @return {@link GraphicsContext} of {@link Canvas}
     */
    public GraphicsContext gc() {
        return map.getGraphicsContext2D();
    }

    /**
     *
     * @return
     */
    public double h() {
        return map.getHeight();

    }

    public double w() {
        return map.getWidth();
    }

    public void start() {
        animator.start();
    }

    public void stop() {
        animator.stop();
    }

    public void fireBullet(Bullet bullet) {
        buffer.add(bullet);
         }
    /**
     * create a method called w. get the height of the map,
     * {@link Canvas#getHeight()}
     *
     * @return height of canvas
     */

    /**
     * create a method called w. get the width of the map,
     * {@link Canvas#getWidth()}
     *
     * @return width of canvas
     */
}
