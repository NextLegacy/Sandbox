package snake;

import engine.Script;
import engine.graphics.DrawableImage;
import engine.math.Vector;

public class Apple extends Script
{
    SnakeScene scene;

    @Override
    protected void start() 
    {
        scene = scene();
    }

    @Override
    protected void render() 
    {
        DrawableImage image = new DrawableImage(new Vector(1, 1));

        image.drawPixel(0, 0, 0xffff0000);

        image().mesh(scene.camera, scene.sphere, transform().transformationMatrix(), image);
    }
}
