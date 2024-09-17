import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Zombie extends Actor
{
    private Player player;
    private final int MOV_RIGHT = 1;
    private final int MOV_LEFT = -1;
    private final int DISTANCE_MIN = 30;
    private int prev_move = MOV_LEFT;
    private final int MAX_LIFE = 30;
    public int life = MAX_LIFE;
    public int damage = 2;
    
    public final int INITIAL_X = 580;
    public final int INITIAL_Y = 355;
    
    public boolean attackPlayer = false;
    private final int ATTACK_COOLDOWN = 120;
    private int currentCooldown = 0;
    
    private LifeLabel lifeLabel;
    private boolean isAlive = true;
    
    Zombie(Player player) {
        this.player = player;
        setImage("ZombieLeft.png");
    }
    protected void addedToWorld(World world) {
        this.lifeLabel = new LifeLabel();
        getWorld().addObject(lifeLabel, getX(), getY() - 30);
    }
    public void act()
    {
        if (isAlive) {
            move();
            checkColisionWithPlayer();
            attack();
            if (lifeLabel != null) {
                updateLifeLabel();
            }   
        }
    }
    private void respawn() {
        this.life = this.MAX_LIFE;
        this.isAlive = true;
        setImage("ZombieLeft.png");
        setLocation(this.INITIAL_X, this.INITIAL_Y);
        
        this.lifeLabel = new LifeLabel();
        
        World world = getWorld();
        if (world != null) {
            world.addObject(this, this.INITIAL_X, this.INITIAL_Y);
            world.addObject(lifeLabel, getX(), getY() - 30);
        }
    }
    private void move() {
        if (getWorld() == null || player.getWorld() == null) {
            return;
        }
        
        int zombieX = getX();
        int playerX = player.getX();
        
        int distanceX = Math.abs(playerX - zombieX);
        if (distanceX > DISTANCE_MIN) {
            if (zombieX < playerX) {
                setLocation(zombieX + MOV_RIGHT, getY());
                setImage("Zombie.png");
            } else if (zombieX > playerX) {
                setLocation(zombieX + MOV_LEFT, getY());
                setImage("ZombieLeft.png");
            }
        }
    }
    private void checkColisionWithPlayer() {
        if (getWorld() == null || player.getWorld() == null) {
            return;
        }
        
        if (isTouching(Player.class) && player.attackEnemy) {
            this.life = this.life - player.damage;
            if (this.life <= 0) {
                setImage("ZombieDeath.png");
                this.isAlive = false;
                ((MyWorld) getWorld()).addPoints(1);
                if (lifeLabel != null) {
                    getWorld().removeObject(lifeLabel);
                    lifeLabel = null;
                }
                
                
                Greenfoot.delay(60);
                respawn();
            }
        }
    }
    private void updateLifeLabel() {
        if (lifeLabel != null) {
            lifeLabel.setLocation(getX(), getY() - 30);
            lifeLabel.updateLife(life);
        }
    }
    private void attack() {
        if (getWorld() == null || player.getWorld() == null) {
            return;
        }
        
        if (isTouching(Player.class)) {
            if (currentCooldown > 0) {
                currentCooldown = currentCooldown - 1;
            }
            if (currentCooldown == 0) {
                this.attackPlayer = true;
            }
            if (attackPlayer) {
                if (prev_move == MOV_LEFT) {
                    setImage("ZombieAttackLeft.png");
                } else if (prev_move == MOV_RIGHT) {
                    setImage("ZombieAttack.png");
                }
                player.life -= this.damage;
                currentCooldown = this.ATTACK_COOLDOWN;
                attackPlayer = false;
            } else {
                if (prev_move == MOV_LEFT) {
                    setImage("ZombieLeft.png");
                } else if (prev_move == MOV_RIGHT) {
                    setImage("Zombie.png");
                }
            }
        }
    }
}
