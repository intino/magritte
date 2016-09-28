package channels;

import org.junit.Test;
import pandora.PandoraApplication;
import tara.magritte.Graph;
import pandora.codegeneration.FullRenderer;

import java.io.File;

public class ChannelsGenerationTest {

	private static final String CHANNELS = "channels";

	@Test
	public void testChannelsGeneration() throws Exception {
		File gen = new File("test-gen", CHANNELS);
		new FullRenderer(Graph.load("Channels").wrap(PandoraApplication.class), gen, gen, CHANNELS).execute();
	}

}
