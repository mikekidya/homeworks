package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;

public class BulletConfiguration {
    private Texture image;
    private float gravity;
    private float speed;
    private float damage;
    private float damageRadius;
    private String name;

    public BulletConfiguration(String name, Texture image, float gravity, float speed, float damage, float damageRadius) {
        this.name = name;
        this.image = image;
        this.gravity = gravity;
        this.speed = speed;
        this.damage = damage;
        this.damageRadius = damageRadius;
    }

    public Texture getImage() {
        return image;
    }

    public float getGravity() {
        return gravity;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    public float getDamageRadius() {
        return damageRadius;
    }

    public String getName() {
        return name;
    }
}
