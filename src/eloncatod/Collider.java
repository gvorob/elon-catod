/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

/**
 *
 * @author George
 */
public class Collider {//handles collisions, also basic movement
    
    public static void moveTowards(Vector2 loc, Vector2 tar, float distance)
    {
        Vector2 temp = Vector2.vecSubt(tar, loc);
        if(temp.length() < distance)
        {
            loc.add(temp);
        }
        else
        {
            temp.setLength(distance);
            loc.add(temp);
        }
    }
}
