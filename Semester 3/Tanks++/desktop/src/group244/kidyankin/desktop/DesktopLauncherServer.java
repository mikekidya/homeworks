package group244.kidyankin.desktop;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;
import group244.kidyankin.TanksGame;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

import java.io.IOException;
import java.net.ServerSocket;

/** Class realising method to launch game on desktop */
public class DesktopLauncherServer {
    public static void main (String[] arg) {
        System.out.println("Tanks++. Server");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(0); // creates socket on random free port
            System.out.println("Port: " + serverSocket.getLocalPort());
            DesktopMini2DxConfig config = new DesktopMini2DxConfig(TanksGame.GAME_IDENTIFIER);
            config.title = "Tank";
            config.width = 800;
            config.height = 600;
            config.vSyncEnabled = true;
            new DesktopMini2DxGame(new TanksGame(serverSocket.accept(), true), config);
        } catch (IOException e) {
            System.out.println("Problems with creating socket");
            e.printStackTrace();
        }

    }
}
