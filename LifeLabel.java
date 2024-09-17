import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LifeLabel extends Actor
{
    private GreenfootImage lifeImage;
    
    public LifeLabel() {
        lifeImage = new GreenfootImage(50, 20);
        setImage(lifeImage);
    }
    
    public void updateLife(int life) {
        lifeImage.clear();
        lifeImage.setColor(Color.WHITE);
        lifeImage.drawString("Life: " + life, 5, 15);
        setImage(lifeImage);
    }
}
