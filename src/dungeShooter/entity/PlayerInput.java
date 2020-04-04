/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeShooter.entity;
//import com.sun.xml.internal.ws.addressing.W3CAddressingConstants;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

/**
 *
 * @author bansri
 */
public class PlayerInput extends InputAdapter<Canvas>
{
    private double x;
    private double y;
    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;
    private boolean leftClick=false;
    private boolean rightClick=false;
    private boolean middleClick=false;
    private boolean shift=false;
    private boolean space=false;
  //  private boolean <<space>> =false;
    
    public boolean hasMoved()
    {
        return(up||down||left||right);
    }
    
    public int leftOrRight()
    {
     if(left)
        {
            return 1;
        }
        else if(right)
        {
            return -1;
            
        }
        else
        {
            return 0;
        }
}
    public int upOrDown()
    {
        if(down)
        {
            return 1;
        }
        else if(up)
        {
            return -1;
            
        }
        else
        {
            return 0;
        }
    }
    public boolean leftClicked()
    {
        return leftClick;
    }
    public boolean rightClicked()
    {
        return rightClick;
    }
    public boolean middleClicked()
    {
        return middleClick;
    }
    public double x()
    {
        return x;
    }
    public double y()
    {
        return y;
    }
    protected void mousePressed(MouseEvent e)
    {
        this.x=e.getX();
        this.y=e.getY();
        leftClick=e.isPrimaryButtonDown();
        rightClick=e.isSecondaryButtonDown();
        middleClick=e.isMiddleButtonDown();
        super.mousePressed(e);
    }
        
    
    protected void mouseReleased(MouseEvent e)
    {
leftClick=false;
		rightClick=false;
		middleClick=false;
                super.mouseReleased( e);
        
    }
    public void changeKeyStatus(KeyCode key,boolean isPressed)
    {
        
         switch(key)
        {
            case  W:
                up=isPressed;
                break;
            case A:
                left=isPressed;
                break;
            case S:
                down=isPressed;
                break;
            case D:
                right=isPressed; 
          case SPACE:
			space=isPressed;
			break;
case SHIFT:
			shift=isPressed;
			break;
		default:
			break;

    }
}
    
    protected void keyPressed(KeyEvent key)
    {
        changeKeyStatus(key.getCode(), true);
    }
    protected void keyReleased(KeyEvent key)    {
        changeKeyStatus(key.getCode(), false);
    }
    protected void moved(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    protected void dragged(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    public boolean isSpace()
    {
        return space;
    }
    public boolean isShift()
    {
        return shift;
    }
}