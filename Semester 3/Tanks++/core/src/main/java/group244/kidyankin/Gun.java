package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.Positionable;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.geom.Point;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.List;


/** Class realising gun */
public class Gun {

    private CollisionPoint position;
    private Sprite sprite = new Sprite(new Texture("gun.png"));
    private Landscape landscape;
    private float health = 100;
    private List<BulletConfiguration> bulletConfigurations;
    private BulletConfiguration currentBulletConfiguration;

    /** Creates a gun on given landscape */
    public Gun(Landscape landscape, float startPosition, List<BulletConfiguration> bulletConfigurations) {
        this.landscape = landscape;
        position = new CollisionPoint(startPosition, landscape.getYCoordinate(startPosition));
        this.bulletConfigurations = bulletConfigurations;
        currentBulletConfiguration = bulletConfigurations.get(0);
    }

    /** Returns the center position of the gun */
    public CollisionPoint getPosition() {
        position.preUpdate();
        return position;
    }

    public void damage(Bullet bullet) {
        if (bullet.getDamageRadius() > (getPosition().getDistanceTo((Positionable) bullet.getPosition()))) {
            health -= bullet.getDamage();
            if (health <= 0) {
                TanksGame.gameStatus = TanksGame.GameStatuses.FINISHED;
            }
        }
    }

    public float getHealth() {
        return health;
    }

    /** Return the angle of the gun in degrees */
    public float getRotation() {
        return sprite.getRotation();
    }

    /** Rotates the gun with given angle in degrees */
    public void rotate(float angle) {
        sprite.rotate(angle);
    }

    /** Moves the gun with given delta on X coordinate */
    public void move(float deltaX) {
        if (!landscape.isOutside(getPosition().getX() + deltaX, getPosition().getY())) {
            position.set(getPosition().getX() + deltaX, landscape.getYCoordinate(getPosition().getX() + deltaX));
        }
    }

    /** Interpolates the position of the gun */
    public void interpolate(float alpha) {
        position.interpolate(null, alpha);

    }

    /** Renders the gun */
    public void render(Graphics g) {
        g.drawSprite(sprite, position.getRenderX() - sprite.getWidth() / 2, position.getRenderY() - sprite.getHeight() / 2);
        g.drawString(Integer.toString((int) health), position.getRenderX() - 10, position.getRenderY() - 10);
    }

    public BulletConfiguration getBulletConfiguration() {
        return currentBulletConfiguration;
    }

    public void changeBulletConfiguration() {
        int currentIndex = bulletConfigurations.indexOf(currentBulletConfiguration);
        currentBulletConfiguration = bulletConfigurations.get((currentIndex + 1) % bulletConfigurations.size());
    }
}
