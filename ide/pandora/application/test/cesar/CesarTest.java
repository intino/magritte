package cesar;

import org.junit.Test;
import tara.magritte.Graph;
import pandora.PandoraApplication;
import pandora.codegeneration.FullRenderer;

import java.io.File;

public class CesarTest {

	private static final String CESAR = "cesar";
	private static final String CONSUL = "consul";


	@Test
	public void testCesar() throws Exception {
		File gen = new File("test-gen", CESAR);
		new FullRenderer(Graph.load("Cesar").wrap(PandoraApplication.class), gen, gen, CESAR).execute();

	}

	@Test
	public void testConsul() throws Exception {
		File gen = new File("test-gen", CONSUL);
		new FullRenderer(Graph.load("Consul").wrap(PandoraApplication.class), gen, gen, CONSUL).execute();

	}


}
