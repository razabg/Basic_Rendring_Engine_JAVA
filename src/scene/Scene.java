package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 *  the class represents a scene in the real life - containing different geometries that captured by the camera
 */
public class Scene {

    public final String name;   //the name of the scene
    public  Color background; //the background color of the scene
    public  AmbientLight ambientLight;//environmental light in the scene
    public  Geometries geometries;//list of geometries
    public  List<LightSource> lights = new LinkedList<>(); //list of light source



    private Scene(SceneBuilder builder){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;
    }

    /**
     * the method return the background color
     * @return background
     */
    public Color getBackground() {
        return background;
    }



    /**
     * builder pattern
     */
    public static class SceneBuilder {

        private final String name;
        private List<LightSource> lights;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }
        // chaining methods

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        public Scene build(){
            Scene scene = new Scene(this);
            //....
            return scene;
        }


    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
}
