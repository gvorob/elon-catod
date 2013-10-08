/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author George Vorobyev <quaffle97@gmail.com>
 */
public class World implements UIListener{
    public Mouse mouse;
    //public ArrayList<GameObject> objects;
    public ArrayList<DrawComp> drawComps;
    public ArrayList<ObjectController> controllers;
    public ArrayList<UIRegion> ui;
    
    public Player player;
    public DrawComp map;
    
    
    
    public static final int MODE_PLAY = 0;
    //public static final int MODE_EDITOR = 1;
    //public static final int MODE_ASSEMBLE = 2;
    
    
    //public BufferedImage assembleBG;
    //public ArrayList<UIRegion> ui;
    
    public BufferedImage[] sprites;
    //public ArrayList<Entity> entities;
    //public Tile[][][] tiles;//x,y,z
    public int mode;
    int[] xyz;//holds the map size
    
    public int viewX = 250;
    public int viewY = 250;
    
    //private int editx,edity,editz;
    //private Tile editTile,editAlt;//editAlt is the tile currently under the cursor, used for swapping-out reasons
    
    public World(Mouse m)
    {
        mode = MODE_PLAY;
        //createUI(MODE_ASSEMBLE);
        //editTile = new Tile(0,0);
        try {
            sprites = new BufferedImage[3];
            sprites[0] = ImageIO.read(new File("drones.png"));
            sprites[1] = ImageIO.read(new File("entities.png"));
            sprites[2] = ImageIO.read(new File("map.png"));
            //assembleBG = ImageIO.read(new File("assemble.png"));
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mouse = m;
        
        drawComps = new ArrayList<>();
        ui = new ArrayList<>();
        controllers = new ArrayList<>();
        
        map = new DrawComp(new SpriteData(2, 0, 0, 560, 280), 0, -200);
        drawComps.add(map);
        player = new Player(this);
        controllers.add(new Drone(this, true, player));
        controllers.add(player);
        //DrawComp temp = new DrawComp(new SpriteData(1, 64 * 1 , 0, 64, 128));
        //temp.x = 5;
        //drawComps.add(temp);
        
        //entities = new ArrayList<>();
        
    }
    
    private void createUI(int mode)
    {
        /*switch(mode)
        {
            case MODE_ASSEMBLE:
                ui = new ArrayList<>();
                Color[] colors = new Color[3];
                colors[0] = new Color(0, 0, 0, 0);
                colors[1] = new Color(255, 255, 255, 128);
                colors[2] = new Color(0, 0, 0, 128);
                
                ui.add(new UIRegion(new Rectangle(175,125,40,40),0,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(100,200,40,40),1,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(175,200,40,40),2,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(250,200,40,40),3,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(100,275,40,40),4,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(175,275,40,40),5,colors.clone(),this));
                ui.add(new UIRegion(new Rectangle(250,275,40,40),6,colors.clone(),this));
                
                colors[1] = new Color(128,255,128,128);
                colors[2] = new Color(60,200,60,160);
                
                ui.add(new UIRegion(new Rectangle(61,435,120,42),7,colors.clone(),this));
                break;
        }*/
    }
    
    private void loadLevel()
    {
        
    }
    
    public void update(float time, Keyboard keys, Mouse m)//per-frame game updates
    {
        Point view = player.getView();
        boolean flag = false;//used for click blocking for ui
        if(mode == MODE_PLAY)
        {
            for(ObjectController o : controllers)
            {
                o.update(time, this);
            }
            for(UIRegion r : ui)
            {
                if(!flag)
                {
                    mouse.setShift(view.x, view.y);
                    if(r.update(mouse))
                        flag = true;
                }
                
            }
            //if(keys.getKey(KeyEvent.VK_F1))
                //mode = MODE_EDITOR;
        }
        /*else if(mode == MODE_EDITOR)
        {
            if(keys.getKeyPressed(KeyEvent.VK_SPACE))
            {}
                //tiles[editx][edity][editz] = editTile.clone();
            
            
            if(keys.getKeyPressed(KeyEvent.VK_W))
                edity += 1;
            if(keys.getKeyPressed(KeyEvent.VK_S))
                edity -= 1;
            if(keys.getKeyPressed(KeyEvent.VK_A))
                editx -= 1;
            if(keys.getKeyPressed(KeyEvent.VK_D))
                editx += 1;
            if(keys.getKeyPressed(KeyEvent.VK_SHIFT))
                editz += 1;
            if(keys.getKeyPressed(KeyEvent.VK_CONTROL))
                editz -= 1;
            if(keys.getKeyPressed(KeyEvent.VK_MINUS))
                editTile.sprite -= 1;
            if(keys.getKeyPressed(KeyEvent.VK_EQUALS))
                editTile.sprite += 1;
            if(keys.getKeyPressed(KeyEvent.VK_OPEN_BRACKET))
                editTile.type -= 1;
            if(keys.getKeyPressed(KeyEvent.VK_CLOSE_BRACKET))
                editTile.type += 1;
            
            //fix any out-of-bounds errors
            if(editx < 0)
                editx = 0;
            if(editx >= xyz[0])
                editx = xyz[0] - 1;
            if(edity < 0)
                edity = 0;
            if(edity >= xyz[1])
                edity = xyz[1] - 1;
            if(editz < 0)
                editz = 0;
            if(editz >= xyz[2])
                editz = xyz[2] - 1;
            
            
            if(keys.getKey(KeyEvent.VK_ESCAPE))
                mode = MODE_PLAY;
        }*/
        
        /*else if(mode == MODE_ASSEMBLE)
        {
            Iterator<UIRegion> i = ui.iterator();
            while(i.hasNext())
            {
                i.next().update(m);
            }
        }*/
    }
    
    public void draw(BufferedImage b)
    {
        Graphics2D g = b.createGraphics();
        Point view = player.getView();
        AffineTransform at = new AffineTransform();
        at.translate(-1 * view.x, -1 * view.y);
        g.setTransform(at);
        for(DrawComp d : drawComps)
        {
            if(d.isIso())
            {
                g.setColor(Color.red);
                Point p = DrawComp.fromIso(d.x, d.y, d.z);
                //int xcor = (int)(d.refx + 16 * d.x + 16 * d.y);
                //int ycor = (int)(d.refy + (8 * d.x) + (-8 * d.y) + (-16 * d.z));
                d.draw().drawSprite(g, p.x + d.refx, p.y + d.refy, sprites);
                /*Point p1,p2,p3,p4;
                p1 = DrawComp.fromIso(0,0,0);
                p2 = DrawComp.fromIso(10,0,0);
                p3 = DrawComp.fromIso(10,10,0);
                p4 = DrawComp.fromIso(0,10,0);
                p1.x -= view.x;
                p1.y -= view.y;
                p2.x -= view.x;
                p2.y -= view.y;
                p3.x -= view.x;
                p3.y -= view.y;
                p4.x -= view.x;
                p4.y -= view.y;
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p3.x, p3.y, p2.x, p2.y);
                g.drawLine(p3.x, p3.y, p4.x, p4.y);
                g.drawLine(p1.x, p1.y, p4.x, p4.y);*/
                
            }
            else
            {
                
            }
        }
        for(UIRegion r:ui)
        {
            r.draw(g);
        }
        //entities = new ArrayList<Entity>();
        if(mode == MODE_PLAY)
        {
            //int xcor = (int)(playersprite.refx + 16 * playersprite.xpos + 16 * playersprite.ypos);
            //int ycor = (int)(playersprite.refy + (8 * playersprite.xpos) + (-8 * playersprite.ypos) + (-16 * playersprite.zpos));
            //viewX = 250 - xcor; 
            //viewY = 250 - ycor;
        }
            
        /*if(mode == MODE_EDITOR)
        {
            
        }*/
        
        
        /*if(mode == MODE_EDITOR || mode == MODE_PLAY)
        {
            int staggerHeight = xyz[0] + xyz[1] - 1;
            int x = 0;
            int y = xyz[1] - 1;
            for(int i = 1; i <= staggerHeight;i++)
            {
                int diff = staggerHeight-i + 1;
                int staggerWidth = diff>=i?i:diff;
                for(int j = 0; j < staggerWidth;j++)
                {
                    for(int z = 0; z < xyz[2];z++)
                    {
                        int ax = x + j;
                        int ay = y + j; 
                        //Misc.prln(String.valueOf(ax) + ' ' + String.valueOf(ay));

                        int xcor = viewX + 0 + 16 * ax + 16 * ay;
                        int ycor = viewY + -55 + (8 * ax) + (-8 * ay) + (-16 * z);
                        int tileSprite = tiles[ax][ay][z].sprite;
                        g.drawImage(sprites[0],  xcor, ycor, xcor + 32, ycor + 64,tileSprite * 32, 0, tileSprite * 32 + 32, 64, null);
                    }

                }

                drawEntities( x - y,g);//draws all entities at z-level z, x-y defines the depth so it occludes each other

                if(y > 0)
                    y--;
                else
                    x++;

            }
        }*/
        
        //if(mode == MODE_EDITOR){}      
        
        
        /*if(mode == MODE_ASSEMBLE)
        {
            g.drawImage(assembleBG, 0, 0, null);
            Iterator<UIRegion> i = ui.iterator();
            while(i.hasNext())
            {
                i.next().draw(g);
            }
        }*/
        
    }
    
    public void add(DrawComp d)
    {drawComps.add(d); 
    }
    public void add(UIRegion r)
    {ui.add(r);Collections.sort(ui, new Comparator<UIRegion>(){
        public int compare(UIRegion a, UIRegion b)
        {return b.uiid- a.uiid;}
    });}
    
    private void drawEntities(int camDistance, Graphics2D g)//camdistance is the distance from the top-left of the grid, given by x - y
    {
        //Iterator<Entity> i = entities.iterator();
        //while(i.hasNext())
        //{
        //    Entity current = i.next();
        //    
        //    if(camDistance == current.getCamDistance())
        //    {
        //        //Misc.prln("--" + String.valueOf(current.getCamDistance()) + "--");
        //        int xcor = (int)(viewX - current.refx + 16 * current.xpos + 16 * current.ypos);
        //        int ycor = (int)(viewY - current.refy + (8 * current.xpos) + (-8 * current.ypos) + (-16 * current.zpos));
        //        int tileCor = current.spriteid * current.spriteWidth;
        //        g.drawImage(sprites[current.spritesheet],  xcor, ycor, xcor + current.spriteWidth, ycor + current.spriteHeight, tileCor, 0, tileCor + current.spriteWidth, current.spriteHeight, null);     
        //    }
        //}
    }
    
    public void informClicked(int i, Mouse m2)
    {
        /*for(UIRegion r : ui)
        {
            if(r.update(mouse))
                break;
        }*/
        /*switch(mode)
        {
            case MODE_ASSEMBLE:
                switch(i)
                {
                    case 7:
                    break;
                }
                break;
        }*/
    }
}
