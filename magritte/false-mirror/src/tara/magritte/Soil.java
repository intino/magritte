package tara.magritte;

class Soil extends Instance {

    Model model;

    Soil() {}

    @Override
    public Model model() {
        return model;
    }

    @SuppressWarnings("unused")
    public Platform engine() {
        return (Platform) model.platform;
    }

    @SuppressWarnings("unused")
    public Application domain() {
        return (Application) model.application;
    }

    @SuppressWarnings("unused")
    public <T extends Platform> T engine(Class<T> class_) {
        return (T) model.platform;
    }

    @SuppressWarnings("unused")
    public <T extends Application> T domain(Class<T> class_) {
        return (T) model.application;
    }

}
