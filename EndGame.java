import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndGame extends World
{
    public EndGame()
    {    
        super(600, 400, 1);
        setBackground("Background.png");
        prepare();
    }
    public void prepare() {
        showText("Press 'R' to restart", getWidth() / 2, getHeight() / 2);
    }
    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
