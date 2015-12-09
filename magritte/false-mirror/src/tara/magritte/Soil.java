package tara.magritte;

class Soil extends Instance {

    Model model;

    Soil() {}

    @Override
    public Model model() {
        return model;
    }

    @SuppressWarnings("unused")
    public Engine engine() {
        return model.engine;
    }

    @SuppressWarnings("unused")
    public Domain domain() {
        return model.domain;
    }

    @SuppressWarnings("unused")
    public <T extends Engine> T engine(Class<T> class_) {
        return (T) model.engine;
    }

    @SuppressWarnings("unused")
    public <T extends Domain> T domain(Class<T> class_) {
        return (T) model.domain;
    }

}
