/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author George
 */
public class Drone extends ObjectController implements ClickListener{
    DrawComp drawer;
    Vector2 location;
    Attackable att;
    final float speed = 2;
    Vector2 target;
    
    private void init(World w, boolean blueTeam, UIListener l, Vector2 loc)
    {
        drawer = new DrawComp(new SpriteData(0,0,0,32,64), -15, -57);
        att = new Attackable(-8, -33, 19, 38, 500, this);
        w.add(att.ui);
        w.add(drawer);
        
        location = loc.clone();
        target =  new Vector2(10,25);//location.clone();
    }
    
    public Drone(World w, boolean blueTeam, UIListener l)
    {init(w,blueTeam,l,new Vector2(10, blueTeam?0.5f:49.5f));}
    
    public Drone(World w, boolean blueTeam, UIListener l, Vector2 loc)
    {init(w,blueTeam,l,loc);}
    
    public void update(float t, World w)
    {
        drawer.move(location);
        if(target != null)
        {
            Vector2 temp = Vector2.vecSubt(target, location);
            if(temp.length() < speed * t)
            {
                target = null;
            }
            else
            {
                temp.setLength(speed * t);
                location.add(temp);
            }
        }
        Point UICorner = drawer.fromIso();
        att.move(UICorner,location);
        
    }

    @Override
    public void updateAttackable(Mouse m) {
        if(m.getL())
        {
            
        }
    }
    
    public boolean checkRemove()
    {
        return att.alive();
    }
    
    public void remove(World w)
    {
        att.remove(w);
        drawer.remove(w);
    }
}
