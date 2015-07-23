package tara.store;

import tara.builder.StashBuilder;

import java.io.File;
import java.nio.charset.Charset;

import static spark.Spark.*;

public class Main {

    private File root = new File("D:/Users/jevora/datos andreas/datos/Model");

    public static void main(String[] args) {
        new Main().execute();
    }

    private void execute() {
        initStore();
        initServer();
    }

    private void initStore() {
        new StashBuilder(root).buildAll(Charset.forName("UTF-8"));
    }

    private void initServer() {
        port(8080);
        get("/*", (req, res) -> "Hello World");
    }

}
