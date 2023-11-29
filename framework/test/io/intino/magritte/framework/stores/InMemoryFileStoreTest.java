package io.intino.magritte.framework.stores;

import io.intino.magritte.framework.TestHelper;
import io.intino.magritte.io.Stash;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryFileStoreTest {

	@Test
	public void should_return_uses_with_size_5() throws Exception {
		File temp = new File("temp");
		FileUtils.deleteDirectory(temp);
		FileSystemStore store = new FileSystemStore(temp);
		store.writeStash(TestHelper.oneMockStash(), TestHelper.oneMockStash + TestHelper.Extension);
		store.writeStash(TestHelper.firstStash(), TestHelper.firstStash + TestHelper.Extension);
		store.writeStash(TestHelper.secondStash(), TestHelper.secondStash + TestHelper.Extension);
		store.writeStash(TestHelper.thirdStash(), TestHelper.thirdStash + TestHelper.Extension);
		store.writeStash(TestHelper.dependantStashByUse(), TestHelper.dependantStashByUse + TestHelper.Extension);
		store.writeStash(TestHelper.independentStashInSubStash(), TestHelper.independentStash + TestHelper.Extension);
		Stash stash = new InMemoryFileStore(temp).stashFrom(TestHelper.secondStash + TestHelper.Extension);
		assertThat(stash.uses.size(), is(5));
	}

}