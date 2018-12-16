package group244.kidyankin;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;
import java.util.List;


/** Class realising pool for bullets produced by gun */
public class BulletsController {

    private List<Bullet> bullets = new LinkedList<Bullet>();
    private Landscape landscape;

    /** Creates a bullet controller on given landscape */
    public BulletsController(Landscape landscape) {
        this.landscape = landscape;
    }

    /**
     * Creates and adds in bullet controller new bullet with given properties
     * @param image the texture image of the bullet
     * @param parentGun the gun bullet is produced by
     * @param gravityCoefficient the coefficient used for control bullet "weight"
     * @param speed the coefficient used for control bullet velocity
     */
    public void addBullet(Texture image, Gun parentGun, float gravityCoefficient, float speed) {
        bullets.add(new Bullet(
                image,
                parentGun.getPosition().getX(),
                parentGun.getPosition().getY(),
                parentGun.getRotation(),
                gravityCoefficient,
                speed
        ));
    }

    /** Recalculates the coordinates of all bullets */
    public void updateAll() {
        List<Bullet> removeList = new LinkedList<Bullet>();
        for (Bullet bullet : bullets) {
            bullet.updatePosition();
            if (landscape.isUnderGround(bullet.getPosition().getX(), bullet.getPosition().getY())) {
                removeList.add(bullet);
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
