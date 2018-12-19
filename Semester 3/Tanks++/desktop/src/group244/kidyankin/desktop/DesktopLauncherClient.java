package group244.kidyankin.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import group244.kidyankin.TanksGame;

import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Class realising method to launch game on desktop */
public class DesktopLauncherClient {
    public static void main (String[] arg) {
        System.out.println("Tanks++. Client");
        System.out.println("Enter the port:");
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", scanner.nextInt());
            DesktopMini2DxConfig config = new DesktopMini2DxConfig(TanksGame.GAME_IDENTIFIER);
            config.title = "Tank";
            config.width = 800;
            config.height = 600;
            config.vSyncEnabled = true;
            new DesktopMini2DxGame(new TanksGame(socket, false), config);
        } catch (IOException e) {
            System.out.println("Port is not open");
        } catch (IllegalArgumentException |InputMismatchException e) {
            System.out.println("Wrong value");
        }
    }
}
