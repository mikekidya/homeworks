package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;

/** Class stores information about concrete bullet type */
public class BulletConfiguration {
    private Texture image;
    private float gravity;
    private float speed;
    private float damage;
    private float damageRadius;
    private String name;

    /**
     * Creates bullet prototype with given properties
     * @param name the name of the bullet type
     * @param image the texture represents the image pg the bullet
     * @param gravity gravity coefficient of the bullet
     * @param speed the speed of bullet flight
     * @param damage the damage this type of bullets can produce
     * @param damageRadius the damage radius of the bullet this type
     */
    public BulletConfiguration(String name, Texture image, float gravity, float speed, float damage, float damageRadius) {
        this.name = name;
        this.image = image;
        this.gravity = gravity;
        this.speed = speed;
        this.damage = damage;
        this.damageRadius = damageRadius;
    }

    /** Returns the texture of the bullet */
    public Texture getImage() {
        return image;
    }

    /** Returns gravity coefficient of the bullet */
    public float getGravity() {
        return gravity;
    }

    /** Returns the speed of bullet flight */
    public float getSpeed() {
        return speed;
    }

    /** Returns the damage this type of bullets can produce */
    public float getDamage() {
        return damage;
    }

    /** Returns the damage radius of the bullet this type */
    public float getDamageRadius() {
        return damageRadius;
    }

    /** Returns the name of the bullet type */
    public String getName() {
        return name;
    }
}
