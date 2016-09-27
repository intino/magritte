package happysense;

import org.junit.Test;
import pandora.PandoraApplication;
import tara.magritte.Graph;
import pandora.codegeneration.FullRenderer;
import pandora.codegeneration.accessor.rest.RESTAccessorRenderer;
import teseo.framework.web.TeseoSpark;
import pandora.rest.RESTService;

import java.io.File;

public class HappySenseTest {

	private static final String HAPPYSENSE = "happysense";

	@Test
	public void testCreation() {
		Graph happysense = Graph.load("Happysense").wrap(PandoraApplication.class);
		File gen = new File("test-gen", HAPPYSENSE);
		new FullRenderer(happysense, gen, gen, HAPPYSENSE).execute();
		happysense.find(RESTService.class).forEach(a -> new RESTAccessorRenderer(a).execute(new File("test-gen/happysense"), HAPPYSENSE));
	}


	public static void main(String[] args) throws Exception {
		TeseoSpark teseoSpark = new TeseoSpark(8080);
//		RestResources.setup(teseoSpark, null);
	}
}
