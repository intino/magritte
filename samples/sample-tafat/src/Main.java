import magritte.ontology.PlayGameMain;
import tafat.control.TafatEngine;

public class Main {

    public static void main(String[] args) {
        TafatEngine tafatEngine = new TafatEngine(PlayGameMain.box);
        PlayGameMain.box = null;
        tafatEngine.execute();
    }

}
