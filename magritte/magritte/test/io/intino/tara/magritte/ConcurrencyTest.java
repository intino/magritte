package io.intino.tara.magritte;

import io.intino.tara.magritte.layers.MockLayer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static io.intino.tara.magritte.TestHelper.m1;

public class ConcurrencyTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		File temp = new File("temp");
		FileUtils.deleteDirectory(temp);
		temp.mkdirs();
		MockLayer x = new Graph(TestHelper.fileSystemMockStore(temp)).load(m1 + "#x").as(MockLayer.class);
		MockLayer y = x.core$().graph().load(m1 + "#y").as(MockLayer.class);
//		modifyVariablesWhileSaving(x, y);
		modifyChildrenWhileSaving(x, y);
	}

	private static void modifyChildrenWhileSaving(MockLayer x, MockLayer y) {
		new Thread(() -> {
			while (true) {
				x.core$().graph().concept("Mock").createNode(x.core$());
			}
		}).start();
		new Thread(() -> {
			while (true) {
				x.save$();
			}
		}).start();
	}

	private static void modifyVariablesWhileSaving(MockLayer x, MockLayer y) {
		new Thread(() -> {
			while (true) {
				x.varMockList().add(y);
			}
		}).start();
		new Thread(() -> {
			while (true) {
				x.save$();
			}
		}).start();
	}
}
