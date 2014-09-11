package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Test;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

public class LoadingModelTest {

	String basePath = "/Users/oroncal/workspace/sandbox/plugins/tara_models/tafat/";
//	String basePath = "/Users/octavio/workspace/tara/rt/res_test/";

	@Test
	public void loadModel() throws Exception {
		try {
			Model model = ModelLoader.load(basePath, "tafat.siani");
			Assert.assertNotNull(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
