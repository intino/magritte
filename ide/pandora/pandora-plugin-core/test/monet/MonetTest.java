package monet;

import org.junit.Test;
import pandora.PandoraApplication;
import tara.magritte.Graph;
import pandora.codegeneration.FullRenderer;

import java.io.File;

public class MonetTest {

	private static final String MONET = "monet";

    @Test
	public void test() {
		final File gen = new File("test-gen", MONET);
		new FullRenderer(Graph.load("Monet").wrap(PandoraApplication.class), gen, gen, MONET).execute();
	}
}
