package group244.kidyankin;

import java.io.*;
import java.net.Socket;

/** Class controlling the actions in game and synchronization between players */
public class Controller {

    private final float MOVEMENT_SPEED = 2;
    private final float ROTATION_SPEED = 2;

    private InputStream inputStream;
    private OutputStream outputStream;
    private Gun playerGun;
    private Gun otherGun;
    private BulletsController bulletsController;

    /** Enum represents all possible actions player can produce */
    public enum EventType {MOVE_GUN_LEFT, MOVE_GUN_RIGHT, ROTATE_GUN_LEFT, ROTATE_GUN_RIGHT, CHANGE_BULLET, PRODUCE_BULLET}

    /**
     * Creates controller with given properties
     * @param socket socket using for connection between two players
     * @param playerGun the gun of player
     * @param otherGun the gun of the opponent
     * @param bulletsController the bullets controller of the game
     * @throws IOException if some socket issues
     */
    public Controller(Socket socket, Gun playerGun, Gun otherGun, BulletsController bulletsController) throws IOException {
        outputStream = socket.getOutputStream();
        outputStream.flush();
        inputStream = socket.getInputStream();
        this.playerGun = playerGun;
        this.otherGun = otherGun;
        this.bulletsController = bulletsController;
    }

    /**
     * Processes the game action of certain type and sends information to opponent
     * @param eventType the type of game action
     * @throws ConnectionException if the are any issues with connection between two players
     */
    public void evaluateEvent(EventType eventType) throws ConnectionException {
        sendEvent(eventType);
        evaluate(eventType, playerGun);
        if (eventType == EventType.PRODUCE_BULLET) {
            TanksGame.gameStatus = TanksGame.GameStatuses.WAITING_FOR_TURN;
        }
    }

    /**
     * Evaluates the actions produced by opponent
     * @throws ConnectionException  if the are any issues with connection between two players
     */
    public void evaluateOtherPlayerEvents() throws ConnectionException {
        try {
            while (inputStream.available() > 0) {
                int input = inputStream.read();
                for (EventType eventType : EventType.values()) {
                    if (eventType.ordinal() == input) {
                        evaluate(eventType, otherGun);
                        if (eventType == EventType.PRODUCE_BULLET) {
                            TanksGame.gameStatus = TanksGame.GameStatuses.WAITING_FOR_BULLET;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new ConnectionException();
        }
    }

    private void evaluate(EventType eventType, Gun gun) {
        switch (eventType) {
            case MOVE_GUN_LEFT:
                gun.move(-MOVEMENT_SPEED);
                break;
            case MOVE_GUN_RIGHT:
                gun.move(MOVEMENT_SPEED);
                break;
            case ROTATE_GUN_LEFT:
                gun.rotate(-ROTATION_SPEED);
                break;
            case ROTATE_GUN_RIGHT:
                gun.rotate(ROTATION_SPEED);
                break;
            case CHANGE_BULLET:
                gun.changeBulletConfiguration();
                break;
            case PRODUCE_BULLET:
                bulletsController.addBullet(gun);
                break;
        }
    }

    private void sendEvent(EventType eventType) throws ConnectionException {
        try {
            outputStream.write(eventType.ordinal());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException();
        }
    }

}
