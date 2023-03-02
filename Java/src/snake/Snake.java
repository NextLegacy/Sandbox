package snake;

import engine.Script;
import engine.graphics.Image;

public class Snake extends Script
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
        image().mesh(scene.camera, scene.cube, transform().transformationMatrix(), Image.fromFile("./rsc/snake/snake.jpeg"));
    }
}
