package group244.kidyankin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.security.Key;
import java.util.Collections;
import java.util.Timer;

/** Main controller class of the game */
public class TanksGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "group244.kidyankin";

    private final float DUCK_GRAVITY = 0.05f;
    private final float DUCK_SPEED = 10;
    private final float DUCK_DAMAGE_RADIUS = 100;
    private final float DUCK_DAMAGE = 5;

    private final float PIANO_GRAVITY = 0.1f;
    private final float PIANO_SPEED = 10;
    private final float PIANO_DAMAGE_RADIUS = 100;
    private final float PIANO_DAMAGE = 15;


    private BulletConfiguration duckBullet;
    private BulletConfiguration pianoBullet;
    private BulletConfiguration currentConfiguration;

    private Gun gun;
    private Landscape landscape;
    private BulletsController bulletsController;
    private long lastTimeBulletProduced = 0;
    private long lastTimeBulletChanged = 0;

    @Override
    public void initialise() {
        landscape = new LandscapeSample();
        gun = new Gun(landscape);
        duckBullet = new BulletConfiguration(new Texture("duck.png"), DUCK_GRAVITY, DUCK_SPEED, DUCK_DAMAGE, DUCK_DAMAGE_RADIUS);
        pianoBullet = new BulletConfiguration(new Texture("piano.png"), PIANO_GRAVITY, PIANO_SPEED, PIANO_DAMAGE, PIANO_DAMAGE_RADIUS);
        currentConfiguration = duckBullet;
        bulletsController = new BulletsController(landscape, Collections.singleton(gun));
    }
    
    @Override
    public void update(float delta) {
        bulletsController.updateAll();

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            gun.rotate(2);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            gun.rotate(-2);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            gun.move(-2);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            gun.move(2);
        }
        if (Gdx.input.isKeyPressed(Keys.C)) {
            if (System.currentTimeMillis() - lastTimeBulletChanged > 300) {
                lastTimeBulletChanged = System.currentTimeMillis();
                if (currentConfiguration == duckBullet) {
                    currentConfiguration = pianoBullet;
                } else {
                    currentConfiguration = duckBullet;
                }
            }

        }
        if (Gdx.input.isKeyPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.SPACE)) {
            if (System.currentTimeMillis() - lastTimeBulletProduced > 100) {
                lastTimeBulletProduced = System.currentTimeMillis();
                bulletsController.addBullet(currentConfiguration, gun);
            }

        }
    }
    
    @Override
    public void interpolate(float alpha) {
        gun.interpolate(alpha);
        bulletsController.interpolateAll(alpha);
    }
    
    @Override
    public void render(Graphics g) {
        landscape.render(g);
        bulletsController.renderAll(g);
        gun.render(g);
    }
}
