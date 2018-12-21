package group244.kidyankin;

import org.mini2Dx.core.graphics.Graphics;

/**
 * Landscape interface.
 * Used to store and get information about the ground.
 */
public interface Landscape {

    /**
     * Returns the Y coordinate of the ground
     * @param x the X coordinate of the ground
     * @return the Y coordinate of the ground
     */
    float getYCoordinate(float x);

    /**
     * Returns if given point is not on the game screen.
     * @param x the X coordinate of the point
     * @param y the Y coordinate of the point
     * @return <tt>true</tt> if point is not on the game screen, <tt>false</tt> otherwise.
     */
    boolean isOutside(float x, float y);

    /**
     * Returns if given point is not under the ground or not on the game screen.
     * @param x the X coordinate of the point
     * @param y the Y coordinate of the point
     * @return  <tt>true</tt> if point is not under the ground, <tt>false</tt> otherwise.
     */
    boolean isUnderGround(float x, float y);

    /** Renders the landscape */
    void render(Graphics g);
}
