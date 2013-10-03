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
    
    final float speed = 4;
    
    public Player(World w)
    {
        SpriteData temp = new SpriteData(1, 0, 0, 64, 128);
        drawer = new DrawComp(temp,-32,-116);
        interactRegion = new UIRegion(new Rectangle(500, 500), 0, this);
        w.add(drawer);
        w.add(interactRegion);
    }
    
    public void update(float t, World w)
    {
        if(target != null)
        {
            Vector2 temp = Vector2.vecSubt(target, new Vector2(drawer.x,drawer.y));
            if(temp.length() < speed * t)
            {
                target = null;
            }
            else
            {
                temp.setLength(speed * t);
                drawer.x += temp.x;
                drawer.y += temp.y;
            }
        }
        //System.out.println("x,y:");
        //System.out.println(drawer.x);
        //System.out.println(drawer.y);
    }
    
    public Point getView()
    {
        Point view =  DrawComp.fromIso(drawer.x, drawer.y, drawer.z);
        view.x -= 250;
        view.y -= 250;
        return view;
    }

    @Override
    public void informClicked(int i, Mouse m) {
        Point temp = getView();
        target = DrawComp.toIso(m.getX() + temp.x,m.getY() + temp.y);
        /*
        System.out.println("Coords");
        System.out.println(m.getX());
        System.out.println(temp.x);
        System.out.println(m.getY());
        System.out.println(temp.y);
        System.out.println(target.x);
        System.out.println(target.y);
        */
    }
}
