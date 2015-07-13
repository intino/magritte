package tara.magritte.sources;

import tara.magritte.Reference;

import java.io.InputStream;

public class BoxSource extends StreamSource {
    private String path;

    public static BoxSource in(String path) {
        return new BoxSource(path);
    }

    public BoxSource(String path) {
        this.path = path;
    }

    @Override
    protected InputStream inputStream() {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(path);
    }

    @Override
    public Reference uid() {
        return () -> path;
    }
}
