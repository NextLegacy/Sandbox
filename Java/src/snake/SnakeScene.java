package snake;

import engine.Scene;
import engine.threed.Camera;
import engine.threed.Mesh;
import engine.utils.ThreedUtils;

public class SnakeScene extends Scene
{
    public Mesh cube;
    public Mesh sphere;
    public Camera camera;
    
    @Override
    protected void init() 
    {
        cube = ThreedUtils.MeshFromObjFile("./rsc/snake/cube.obj");
        sphere = ThreedUtils.MeshFromObjFile("./rsc/snake/sphere.obj");
        
        camera = new Camera(90, 16d/9d, 0.1d, 1000d);
    }    
}
