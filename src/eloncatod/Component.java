/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

import java.awt.image.BufferedImage;

/**
 *
 * @author George
 */
public class Component {
    public ObjectController parent;
    
    public Component()
    {
        
    }
    
    public void registerParent(ObjectController p)
    {
        if(parent == null)
            parent = p;
    }
    
    public void update(World w)
    {
        
    }
    
    public void draw(BufferedImage b)
    {
        
    }
}
