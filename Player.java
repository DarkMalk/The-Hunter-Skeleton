import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private final int MOVE_LEFT = -1;
    private final int MOVE_RIGHT = 1;
    private int prev_move = MOVE_RIGHT;
    private final int MAX_LIFE = 20;
    public int life = MAX_LIFE;
    public int damage = 5;
    public boolean attackEnemy = false;
    
    private final int ATTACK_COOLDOWN = 15;
    private int currentCooldown = 0;
    
    public void act()
    {
        move();
        attack();
        checkLife();
        checkColisionWithHeal();
        if (currentCooldown > 0) {
            currentCooldown = currentCooldown - 1;
        }
    }
    private void checkLife() {
        if (getWorld() == null) {
            return;
        }
        getWorld().showText("", 40, 14);
        
        getWorld().showText("Life: " + this.life, 40, 14);
        
        if (this.life <= 0) {
            getWorld().removeObject(this);
            Greenfoot.setWorld(new EndGame());
        }
    }
    private void move() {
        if (getWorld() == null) {
            return;
        }
        
        int x = getX();
        int y = getY();
        
        if (Greenfoot.isKeyDown("left")) {
            setLocation(x + this.MOVE_LEFT, y);
            setImage("PlayerSkeletonLeft.png");
            this.prev_move = MOVE_LEFT;
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(x + this.MOVE_RIGHT, y);
            setImage("PlayerSkeleton.png");
            this.prev_move = MOVE_RIGHT;
        }
    }
    private void attack() {
        if (currentCooldown > 0) {
            this.attackEnemy = false;
            return;
        }
        if (!Greenfoot.isKeyDown("space")) {
            this.attackEnemy = false;
            if (this.prev_move == MOVE_RIGHT) {
                setImage("PlayerSkeleton.png");
            } else {
                setImage("PlayerSkeletonLeft.png");
            }
            return;
        }
        if (prev_move == MOVE_RIGHT) {
            setImage("PlayerSkeletonAttackRight.png");
        }
        if (prev_move == MOVE_LEFT) {
            setImage("PlayerSkeletonAttackLeft.png");
        }
        this.attackEnemy = true;
        currentCooldown = this.ATTACK_COOLDOWN;
    }
    private void checkColisionWithHeal() {
        if (getWorld() == null) {
            return;
        }
        
        Heal heal = (Heal) getOneIntersectingObject(Heal.class);
        if (heal == null) {
            return;
        }
        
        int amountHeal = heal.amountHeal();
        if (this.life + amountHeal > this.MAX_LIFE) {
            this.life = this.MAX_LIFE;
        } else {
            this.life += amountHeal;
        }
        
        heal.removeHeal();
    }
}
