package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Test;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

import static java.io.File.separator;

public class LoadingModelTest {

	String basePath = "/Users/oroncal/workspace/sandbox/plugins" + separator + "tara" + separator + "classes" + separator + "Tafat" + separator;

	@Test
	public void loadModel() throws Exception {
		try {
			Model model = ModelLoader.load(basePath, "engine");
			Assert.assertNotNull(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
