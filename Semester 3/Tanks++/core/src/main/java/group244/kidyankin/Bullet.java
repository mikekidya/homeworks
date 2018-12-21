package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;


/** CLass realising the bullet produced bu gun */
public class Bullet {

    private Texture image;
    private float gravityCoefficient;
    private CollisionPoint position;
    private double angle;
    private float time = 0;
    private float speed;
    private float damage;
    private float damageRadius;

    /**
     * Creates new bullet with given properties.
     * @param image the texture of the bullet
     * @param x the start X coordinate of the bullet
     * @param y the start Y coordinate of the bullet
     * @param angle the start angle of the bullet in degrees
     * @param gravityCoefficient the coefficient used for control bullet "weight"
     * @param speed the coefficient used for control bullet velocity
     */
    public Bullet(Texture image, float x, float y, float angle,
                  float gravityCoefficient, float speed, float damage, float damageRaduis) {
        this.image = image;
        this.angle = angle * Math.PI / 180;
        position = new CollisionPoint(x, y);
        this.gravityCoefficient = gravityCoefficient;
        this.speed = speed;
        this.damage = damage;
        this.damageRadius = damageRaduis;
    }

    /** Return the center position of the bullet */
    public CollisionPoint getPosition() {
        return position;
    }

    /** Recalculates the position of the bullet */
    public void updatePosition() {
        position.preUpdate();
        position.set(
                position.getX() + (float) Math.cos(angle) * speed,
                position.getY() + (float) Math.sin(angle) * speed + time * time
        );
        updateTime();
    }

    /** Returns the damage radius of the bullet */
    public float getDamageRadius() {
        return damageRadius;
    }

    /** Returns the damage of the bullet */
    public float getDamage() {
        return damage;
    }

    /** Interpolates the position of the bullet */
    public void interpolate(float alpha) {
        position.interpolate(null, alpha);
    }

    /** Renders the bullet */
    public void render(Graphics g) {
        g.drawTexture(image, position.getRenderX() - image.getWidth() / 2, position.getRenderY() - image.getHeight() / 2);
    }

    private void updateTime() {
        time += gravityCoefficient;
    }
}