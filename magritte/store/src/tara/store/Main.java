package tara.store;

import tara.builder.StashBuilder;

import java.io.File;
import java.nio.charset.Charset;

import static spark.Spark.*;

public class Main {

    private File root = new File("D:/Users/jevora/datos andreas/datos/Model/");

    public static void main(String[] args) {
        new Main().execute();
    }

    private void execute() {
        initStore();
//        initServer();
    }

    private void initStore() {
        new StashBuilder(root).build("Scene", Charset.forName("UTF-8"));
                //buildAll(Charset.forName("UTF-8"));
    }

    private boolean checkFile(File f) {
        if(!f.isDirectory())
            return false;
        int day = Integer.valueOf(f.getName().replace(".tara", ""));
        return day > 0 && day <32;
    }

    private void initServer() {
        port(8080);
        get("/*", (req, res) -> "Hello World");
    }

}
