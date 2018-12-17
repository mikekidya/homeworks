package group244.kidyankin.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import group244.kidyankin.TanksGame;

/** Class realising method to launch game on desktop */
public class DesktopLauncher {
    public static void main (String[] arg) {
        DesktopMini2DxConfig config = new DesktopMini2DxConfig(TanksGame.GAME_IDENTIFIER);
        config.title = "Tank";
        config.width = 800;
        config.height = 600;
        config.vSyncEnabled = true;
        new DesktopMini2DxGame(new TanksGame(), config);
    }
}
