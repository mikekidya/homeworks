package group244.kidyankin;


import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.geom.Point;
import org.mini2Dx.core.graphics.Graphics;

/** Class realizing sample landscape */
public class LandscapeSample implements Landscape {

    private Point[] nodes;
    private Texture background = new Texture("land.png");

    public LandscapeSample() {
        nodes = new Point[9];
        nodes[0] = new Point(0, 391);
        nodes[1] = new Point(58, 391);
        nodes[2] = new Point(216, 562);
        nodes[3] = new Point(351, 386);
        nodes[4] = new Point(392, 438);
        nodes[5] = new Point(482, 315);
        nodes[6] = new Point(547, 392);
        nodes[7] = new Point(668, 237);
        nodes[8] = new Point(801, 408);
    }

    @Override
    public float getYCoordinate(float x) {
        for (int i = 0; i < nodes.length - 1; i++) {
            if (nodes[i].getX() <= x && nodes[i + 1].getX() > x)
            {
                return getPositionOnLineBetweenPoints(x, nodes[i], nodes[i + 1]);
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isOutside(float x, float y) {
        return x < 0 || x > 800 || y < 0 || y > 600;
    }

    @Override
    public boolean isUnderGround(float x, float y) {
        return x < 0 || x > 800 || getYCoordinate(x) < y;
    }

    @Override
    public void render(Graphics g) {
        g.drawTexture(background, 0, 0);
    }

    private float getPositionOnLineBetweenPoints(float x, Point p1, Point p2) {
        return p1.getY() + (x - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

}
