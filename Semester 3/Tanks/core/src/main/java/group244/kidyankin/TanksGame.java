package group244.kidyankin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

/** Main controller class of the game */
public class TanksGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "group244.kidyankin";

    private final float DUCK_GRAVITY = 0.05f;
    private final float DUCK_SPEED = 10;

    private Gun gun;
    private Landscape landscape;
    private BulletsController bulletsController;

    private Texture duckPic;

    @Override
    public void initialise() {
        landscape = new LandscapeSample();
        gun = new Gun(landscape);
        duckPic = new Texture("duck.png");
        bulletsController = new BulletsController(landscape);
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
        if (Gdx.input.isKeyPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.SPACE)) {
            bulletsController.addBullet(duckPic, gun, DUCK_GRAVITY, DUCK_SPEED);
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
