package group244.kidyankin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Main controller class of the game */
public class TanksGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "group244.kidyankin";

    /** Enum representing all possible states of the game */
    public enum GameStatuses {FINISHED, WAITING_FOR_TURN, WAITING_FOR_BULLET, PLAYING, FAILED}

    /** Stores the current status of the game */
    public static GameStatuses gameStatus;

    private final float DUCK_GRAVITY = 0.05f;
    private final float DUCK_SPEED = 10;
    private final float DUCK_DAMAGE_RADIUS = 100;
    private final float DUCK_DAMAGE = 5;

    private final float PIANO_GRAVITY = 0.1f;
    private final float PIANO_SPEED = 10;
    private final float PIANO_DAMAGE_RADIUS = 100;
    private final float PIANO_DAMAGE = 15;

    private Socket socket;
    private Controller controller;
    private boolean isServer;

    private Gun gun;
    private Gun otherGun;
    private Landscape landscape;
    private BulletsController bulletsController;

    private long lastTimeBulletChanged = 0;

    /**
     * Initialises the game
     * @param socket socket using for connection between two players
     * @param isServer if this player is a server player
     */
    public TanksGame(Socket socket, boolean isServer) {
        this.socket = socket;
        this.isServer = isServer;
    }

    @Override
    public void initialise() {
        landscape = new LandscapeSample();
        BulletConfiguration duckBullet = new BulletConfiguration(
                "DUCK", new Texture("duck.png"),
                DUCK_GRAVITY, DUCK_SPEED,
                DUCK_DAMAGE, DUCK_DAMAGE_RADIUS
        );
        BulletConfiguration pianoBullet = new BulletConfiguration(
                "PIANO", new Texture("piano.png"),
                PIANO_GRAVITY, PIANO_SPEED,
                PIANO_DAMAGE, PIANO_DAMAGE_RADIUS
        );
        List<BulletConfiguration> bulletConfigurations = new ArrayList<BulletConfiguration>();
        bulletConfigurations.add(duckBullet);
        bulletConfigurations.add(pianoBullet);
        gun = new Gun(landscape, isServer ? 20 : 500, bulletConfigurations);
        otherGun = new Gun(landscape, isServer ? 500 : 20, bulletConfigurations);
        gameStatus = isServer ? GameStatuses.PLAYING : GameStatuses.WAITING_FOR_TURN;
        Collection<Gun> guns = new ArrayList<Gun>();
        guns.add(gun);
        guns.add(otherGun);
        bulletsController = new BulletsController(landscape, guns);
        try {
            controller = new Controller(socket, gun, otherGun, bulletsController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(float delta) {
        try {
            controller.evaluateOtherPlayerEvents();
            bulletsController.updateAll();
            if (gameStatus == GameStatuses.WAITING_FOR_BULLET && bulletsController.numberOfBullets() == 0) {
                gameStatus = GameStatuses.PLAYING;
            }
            if (gameStatus == GameStatuses.PLAYING) {
                if (Gdx.input.isKeyPressed(Keys.UP)) {
                    controller.evaluateEvent(Controller.EventType.ROTATE_GUN_LEFT);
                }
                if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                    controller.evaluateEvent(Controller.EventType.ROTATE_GUN_RIGHT);
                }
                if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                    controller.evaluateEvent(Controller.EventType.MOVE_GUN_LEFT);
                }
                if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                    controller.evaluateEvent(Controller.EventType.MOVE_GUN_RIGHT);
                }
                if (Gdx.input.isKeyPressed(Keys.C)) {
                    if (System.currentTimeMillis() - lastTimeBulletChanged > 300) {
                        lastTimeBulletChanged = System.currentTimeMillis();
                        controller.evaluateEvent(Controller.EventType.CHANGE_BULLET);
                    }
                }
                if (Gdx.input.isKeyPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.SPACE)) {
                        controller.evaluateEvent(Controller.EventType.PRODUCE_BULLET);
                }
            }
        } catch (ConnectionException e) {
            gameStatus = GameStatuses.FAILED;
        }
    }
    
    @Override
    public void interpolate(float alpha) {
        gun.interpolate(alpha);
        otherGun.interpolate(alpha);
        bulletsController.interpolateAll(alpha);
    }
    
    @Override
    public void render(Graphics g) {
        landscape.render(g);
        bulletsController.renderAll(g);
        gun.render(g);
        otherGun.render(g);
        g.drawString(
                String.format("Your gun health: %s, chosen bullet: %s\n", gun.getHealth(), gun.getBulletConfiguration().getName()) +
                String.format("Opponent gun health: %s, chosen bullet: %s\n", otherGun.getHealth(), otherGun.getBulletConfiguration().getName()) +
                String.format("Now %s turn", gameStatus == GameStatuses.PLAYING ? "your" : "opponent"),
                0, 0
        );
        if (gameStatus == GameStatuses.FINISHED) {
            g.drawString("GAME OVER", g.getWindowWidth() / 2 - 50, g.getWindowHeight() / 2 - 50);
        }
        if (gameStatus == GameStatuses.FAILED) {
            g.drawString("CONNECTION LOST", g.getWindowWidth() / 2 - 50, g.getWindowHeight() / 2 - 50);
        }
    }
}
