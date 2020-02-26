package io.intino.magritte.framework;

import io.intino.magritte.framework.stores.FileSystemStore;
import io.intino.magritte.framework.stores.ResourcesStore;
import io.intino.magritte.io.Stash;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.intino.magritte.io.Helper.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@SuppressWarnings("WeakerAccess")
public class TestHelper {

	public static final String emptyStash = "Empty";
	public static final String oneMockStash = "OneMock";
	public static final String uuidStash = "uuidStash";
	public static final String firstStash = "firstStash";
	public static final String secondStash = "secondStash";
	public static final String thirdStash = "thirdStash";
	public static final String dependantStashByUse = "dependantStashByUse";
	public static final String cyclicDependantStash = "cyclicDependantStash";
	public static final String independentStash = "substash/independant";
	public static final String m1 = "m1";
	public static final String m2 = "m2";
	public static final String m3 = "m3";
	public static final String highHierarchy = "highHierarchy";
	public static final String missingReference = "missingReference";
	public static final String manyReferences = "manyReferences";
	public static final String Extension = ".stash";

	public static Store mockStore() {
		return new MockStore();
	}

	public static Store fileSystemMockStore(File file) {
		return new FileSystemStore(file) {

			Map<String, Stash> store = new HashMap<String, Stash>() {{
				put(m1 + Extension, m1());
				put(m2 + Extension, m2());
				put(m3 + Extension, m3());
			}};

			@Override
			public Stash stashFrom(String stash) {
				return store.containsKey(stash) ? store.get(stash) : super.stashFrom(stash);
			}

			@Override
			public void writeStash(Stash stash, String path) {
				super.writeStash(stash, path);
			}

		};
	}

	public static Stash uuidStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(uuidStash + "#46012fc8-968a-4b0b-b329-9f2e7c48986a", list("Mock"), list(newReference("mockLayer", secondStash + "#y")), emptyList()));
		return stash;
	}

	public static Stash firstStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(firstStash + "#x", list("Mock"), list(newReference("mockLayer", secondStash + "#y")), emptyList()));
		return stash;
	}

	public static Stash secondStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(secondStash + "#y", list("Mock"), list(newReference("mockLayer", thirdStash + "#z")), emptyList()));
		return stash;
	}

	public static Stash thirdStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(thirdStash + "#z", list("Mock"), list(newReference("mockLayer", firstStash + "#x")), emptyList()));
		return stash;
	}

	public static Stash oneMockStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(oneMockStash + "#x", list("Mock"), emptyList(), emptyList()));
		stash.nodes.add(newNode(oneMockStash + "#y", list("Mock"), emptyList(), emptyList()));
		return stash;
	}

	public static Stash dependantStashByUse() {
		Stash stash = emptyStash();
		stash.uses.addAll(asList(oneMockStash, cyclicDependantStash));
		stash.nodes.add(newNode(dependantStashByUse + "#x", list("Mock"), emptyList(), emptyList()));
		return stash;
	}

	public static Stash cyclicDependantStash() {
		Stash stash = emptyStash();
		stash.uses.add(dependantStashByUse);
		stash.nodes.add(newNode(cyclicDependantStash + "#x", list("Mock"), list(newReference("mockLayer", dependantStashByUse + "#x")), emptyList()));
		return stash;
	}

	public static Stash independentStashInSubStash() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(independentStash + "#x", list("Mock"), emptyList(), emptyList()));
		return stash;
	}

	public static Stash m1() {
		Stash stash = newStash("m2", list());
		stash.nodes.add(newNode(m1 + "#x", list("Mock"), emptyList(), emptyList()));
		stash.nodes.add(newNode(m1 + "#y", list("Mock"), emptyList(), emptyList()));
		return stash;
	}

	public static Stash m2() {
		return newStash("m3", list());
	}

	public static Stash m3() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, false, true, "io.intino.magritte.framework.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

	public static Stash highHierarchy() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(
						newConcept("Mock", false, false, false, true, "io.intino.magritte.framework.layers.SubMockLayer", "Super1Mock", list("Concept"), emptyList(), emptyList(), emptyList(), emptyList()),
						newConcept("Super1Mock", false, false, false, true, "io.intino.magritte.framework.layers.Super1MockLayer", "Super2Mock", list("Concept"), emptyList(), emptyList(), emptyList(), emptyList()),
						newConcept("Super2Mock", false, false, false, true, "io.intino.magritte.framework.layers.Super2MockLayer", "Super3Mock", list("Concept"), emptyList(), emptyList(), emptyList(), emptyList()),
						newConcept("Super3Mock", false, false, false, true, "io.intino.magritte.framework.layers.Super3MockLayer", "Super4Mock", list("Concept"), emptyList(), emptyList(), emptyList(), emptyList()),
						newConcept("Super4Mock", false, false, false, true, "io.intino.magritte.framework.layers.Super4MockLayer", "Super5Mock", list("Concept"), emptyList(), emptyList(), emptyList(), emptyList()),
						newConcept("Super5Mock", false, false, false, true, "io.intino.magritte.framework.layers.Super5MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())
				),
				singletonList(newNode(m1 + "#y", list("Mock"), emptyList(), emptyList())));
	}


	public static Stash missingReference() {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(missingReference + "#x", list("Mock"), list(newReference("mockLayer", "nonExistingStash" + "#x")), emptyList()));
		return stash;
	}

	public static Stash manyReferences() {
		Stash stash = emptyStash();
		io.intino.magritte.io.Node node = newNode(missingReference + "#x", list("Mock"), list(), emptyList());
		String[] references = new String[2000];
		for (int i = 0; i < 2000; i++) {
			references[i] = "stash" + i + "#x";
		}
		node.variables.add(newReference("varMockList", references));
		stash.nodes.add(node);
		return stash;
	}

	public static Stash referencedStash(String stashname) {
		Stash stash = emptyStash();
		stash.nodes.add(newNode(stashname.replace(".stash", "") + "#x", list("Mock"), list(newReference("varMockList", "stash1#x")), emptyList()));
		return stash;
	}

	static Stash emptyStash() {
		return newStash("m3", list());
	}

	public static class Test {
		public static void main(String[] args) {
			String hex = "4d2";
			long longHex = parseUnsignedHex(hex);
			double d = Double.longBitsToDouble(longHex);
			System.out.println(d);
		}

		public static long parseUnsignedHex(String text) {
			if (text.length() == 16) {
				return (parseUnsignedHex(text.substring(0, 1)) << 60)
						| parseUnsignedHex(text.substring(1));
			}
			return Long.parseLong(text, 16);
		}
	}

	public static class MockStore implements Store {
		Map<String, Stash> store = new HashMap<String, Stash>() {{
			put(emptyStash + Extension, emptyStash());
			put(oneMockStash + Extension, oneMockStash());
			put(uuidStash + Extension, uuidStash());
			put(firstStash + Extension, firstStash());
			put(secondStash + Extension, secondStash());
			put(thirdStash + Extension, thirdStash());
			put(dependantStashByUse + Extension, dependantStashByUse());
			put(cyclicDependantStash + Extension, cyclicDependantStash());
			put(independentStash + Extension, independentStashInSubStash());
			put(m1 + Extension, m1());
			put(m2 + Extension, m2());
			put(m3 + Extension, m3());
			put(highHierarchy + Extension, highHierarchy());
			put(missingReference + Extension, missingReference());
			put(manyReferences + Extension, manyReferences());
		}};

		@Override
		public Stash stashFrom(String stash) {
			return store.get(stash);
		}

		@Override
		public void writeStash(Stash stash, String path) {
			store.put(path, composeStash(path, stash));
		}

		@Override
		public URL resourceFrom(String path) {
			return new ResourcesStore().resourceFrom(path);
		}

		@Override
		public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
			return null;
		}

		@Override
		public String relativePathOf(URL url) {
			return null;
		}
	}
}
