/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author George
 */
public class DrawComp extends Component{
    float x, y, z; 
    int refx, refy;
    SpriteData sprite;
    boolean iso;
    
    private void init(SpriteData s, int rx, int ry)
    {
        sprite = s;
        refx = rx;
        refy = ry;
        iso = true;
    }
    
    public DrawComp(SpriteData s)
    {
        init(s,0,0);
    }
    
    public DrawComp(SpriteData s, int rx, int ry)
    {
        init(s,rx,ry);
    }
    
    public SpriteData draw()
    {
        return sprite;
    }
    
    public boolean isIso()
    {return iso;}
    
    
    public static Point fromIso(float x, float y, float z)
    {
        int xcor = (int)(16 * x + 16 * y);
        int ycor = (int)((8 * x) + (-8 * y) + (-16 * z));
        return new Point(xcor, ycor);
    }
    
    public static Vector2 toIso(int x, int y)
    {
        return new Vector2(x/32.0f + y/16.0f, x/32.0f - y/16.0f);
    }
}
