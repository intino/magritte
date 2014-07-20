package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Test;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

public class LoadingModelTest {

//	String basePath = "/Users/oroncal/workspace/sandbox/plugins" + separator + "tara" + separator + "classes" + separator + "Tafat" + separator;
	String basePath = "/Users/octavio/workspace/tara/rt/res_test/";

	@Test
	public void loadModel() throws Exception {
		try {
			Model model = ModelLoader.load(basePath, "siani");
			Assert.assertNotNull(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
