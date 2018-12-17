package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.geom.Circle;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/** Class realising pool for bullets produced by gun */
public class BulletsController {

    private List<Bullet> bullets = new LinkedList<Bullet>();
    private Collection<Gun> guns;
    private Landscape landscape;

    /** Creates a bullet controller on given landscape */
    public BulletsController(Landscape landscape, Collection<Gun> guns) {
        this.landscape = landscape;
        this.guns = guns;
    }


    public void addBullet(BulletConfiguration configuration, Gun parentGun) {
        bullets.add(new Bullet(
                configuration.getImage(),
                parentGun.getPosition().getX(),
                parentGun.getPosition().getY(),
                parentGun.getRotation(),
                configuration.getGravity(),
                configuration.getSpeed(),
                configuration.getDamage(),
                configuration.getDamageRadius()
        ));
    }

    /** Recalculates the coordinates of all bullets */
    public void updateAll() {
        List<Bullet> removeList = new LinkedList<Bullet>();
        for (Bullet bullet : bullets) {
            bullet.updatePosition();
            if (landscape.isUnderGround(bullet.getPosition().getX(), bullet.getPosition().getY())) {
                removeList.add(bullet);
                for (Gun gun : guns) {
                    gun.damage(bullet);
                }
            }
        }
        bullets.removeAll(removeList);
    }

    /** Renders all bullets in game */
    public void renderAll(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.render(g);
        }

    }

    /** Interpolates all bullets positions */
    public void interpolateAll(float alpha) {
        for (Bullet bullet : bullets) {
            bullet.interpolate(alpha);
        }
    }

}
