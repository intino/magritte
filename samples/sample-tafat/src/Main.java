import magritte.ontology.PlayGameMain;
import tafat.control.TafatEngine;

public class Main {

    public static void main(String[] args) {
        TafatEngine tafatEngine = new TafatEngine(new PlayGameMain());
        tafatEngine.execute();
    }

}
