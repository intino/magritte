package monet;

import pandora.PandoraApplication;
import tara.magritte.Graph;
import pandora.codegeneration.FullRenderer;

import java.io.File;

public class MonetTest {

	private static final String MONET = "monet";

	public static void main(String[] args) {
		final File gen = new File("test-gen", MONET);
		new FullRenderer(Graph.load("Monet").wrap(PandoraApplication.class), gen, gen, MONET).execute();
	}
}
