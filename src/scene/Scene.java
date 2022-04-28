package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 *  the class represents a scene in the real life - containing different geometries that captured by the camera
 */
public class Scene {

    public final String name;   //the name of the scene
    public final Color background; //the background color of the scene
    public final AmbientLight ambientLight;//environmental light in the scene
    public final Geometries geometries;//list of geometries



    private Scene(SceneBuilder builder){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    /**
     * the method return the background color
     * @return background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * bulider pattern
     */
    public static class SceneBuilder {

        private final String name;
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

        public Scene build(){
            Scene scene = new Scene(this);
            //....
            return scene;
        }


    }
}
