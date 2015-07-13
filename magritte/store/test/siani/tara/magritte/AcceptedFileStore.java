package siani.tara.magritte;

import magritte.Shop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import siani.tara.magritte.helper.FileSystem;
import siani.tara.magritte.schema.Capsule;
import siani.tara.magritte.stores.FileStore;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayWithSize;

public class AcceptedFileStore {

	public static final String TemporalStore = "test.res/temp";
	public static final String LocalStore = "test.res/store";

	@Before
	@After
	public void deleteStore() {
		FileSystem.deleteFolder(TemporalStore);
	}

	@Test
	public void should_create_gates() throws Exception {
		Capsule capsule = new Capsule(new FileStore(TemporalStore)).with(m0());

		assertThat(capsule.get("vibur").toString(), is("Gate[//jerlos/vibur:Customer]"));
		assertThat(capsule.get("pleope").toString(), is("Gate[//miyun/pleope:People]"));
		assertThat(capsule.get("bag").toString(), is("Gate[//secre/bag:Product]"));
	}

	@Test
	public void shold_save_root_nodes() throws Exception {
		new Capsule(new FileStore(TemporalStore)).with(m0());

		checkDirectory(TemporalStore, new String[]{"zisvoros", "jerlos", "miyun", "secre", "zutlu"});
		checkDirectory(TemporalStore + "/zisvoros", new String[]{"tacalon.mnode"});
		checkDirectory(TemporalStore + "/miyun", new String[]{"pleope.mnode"});
		checkDirectory(TemporalStore + "/jerlos", new String[]{"belyus.mnode", "brorde.mnode", "vibur.mnode", "vibur#1.png", "vibur#2.png"});
		checkDirectory(TemporalStore + "/zutlu", new String[]{"frelon.mnode", "jarnat.mnode"});
		checkDirectory(TemporalStore + "/secre", new String[]{"bag.mnode", "bag#1.jpg", "ball.mnode", "ball#1.jpg", "coffee.mnode", "coffee#1.jpg", "sugar.mnode", "sugar#1.jpg"});
		checkFile(TemporalStore + "/zisvoros/tacalon.mnode", 379);
		checkFile(TemporalStore + "/miyun/pleope.mnode", 426);
		checkFile(TemporalStore + "/jerlos/belyus.mnode", 529);
		checkFile(TemporalStore + "/jerlos/brorde.mnode", 527);
		checkFile(TemporalStore + "/jerlos/vibur.mnode", 701);
		checkFile(TemporalStore + "/jerlos/vibur#1.png", 33186);
		checkFile(TemporalStore + "/jerlos/vibur#2.png", 1399);
	}

	//@Test
	public void should_load_gates() throws Exception {
		Capsule capsule = new Capsule(new FileStore(TemporalStore)).with(m0()).load();

		assertThat(capsule.get("vibur").toString(), is("Case[vibur:Customer:Form:Concept]"));
		assertThat(capsule.get("vibur").members(Node.Member.Component).size(), is(5));
		assertThat(capsule.get("vibur").fanIn().size(), is(1));
		assertThat(capsule.get("vibur").fanIn().get(0).toString(), is("Case[pleope:People:Collection:Concept]"));
		assertThat(capsule.get("vibur").fanOut().size(), is(1));
		assertThat(capsule.get("vibur").fanOut().get(0).toString(), is("Case[bag:Product:Form:Concept]"));
		assertThat(capsule.get("vibur").owner(), is(nullValue()));
	}

	private void checkFile(String path, long size) {
		File file = file(path);
		assertThat("File " + path + " exist", file.exists());
		assertThat("File " + path + " size", file.length(), is(size));
	}


	private void checkDirectory(String path, String[] files) {
		File file = file(path);
		assertThat(file.list(), arrayWithSize(files.length));
		assertThat(Arrays.asList(file.list()), hasItems(files));
	}

	private File file(String path) {
		return new File(path);
	}

	private Graph m0() {
		return Shop.create().m0();
	}

}