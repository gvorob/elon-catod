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
public class Player extends ObjectController implements UIListener{
    DrawComp drawer;
    UIRegion interactRegion;
    Vector2 target;
    Vector2 location;
    
    final float speed = 6;
    
    public Player()
    {
        SpriteData temp = new SpriteData(1, 0, 0, 64, 128);
        drawer = new DrawComp(temp,-32,-116);
        interactRegion = new UIRegion(new Rectangle(-50000, -50000, 100000, 100000), 0, this);
        location = new Vector2(0,0);
        World.w.add(drawer);
        World.w.add(interactRegion);
    }
    
    public void update(float t)
    {
        if(target != null)
        {
            Collider.moveTowards(location, target, t * speed);
        }
        drawer.move(location);
    }
    
    public Point getView()
    {
        Point view =  drawer.fromIso();
        view.y -= 250;
        view.x -= 250;
        return view;
    }

    @Override
    public void informClicked(int i, Mouse m) {
        switch(i)
        {
            case 0:
                target = DrawComp.toIso(m.getX(),m.getY());
                break;
        }
    }

    public void clickedOn(Attackable att, Mouse m) {
        if(!m.getL())
        {
            createProjectile(att);
        }
    }
    
    private void createProjectile(Attackable att)
    {
        Projectile p = new Projectile(att, 100, 0, 50, location.clone());
        World.w.add(p);
    }
}