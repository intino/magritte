package io.intino.tara.magritte.stores;

import io.intino.tara.io.Stash;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static io.intino.tara.magritte.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryFileStoreTest {

    @Test
    public void should_return_uses_with_size_5() throws Exception {
        File temp = new File("temp");
        FileUtils.deleteDirectory(temp);
        FileSystemStore store = new FileSystemStore(temp);
        store.writeStash(oneMockStash(), oneMockStash + Extension);
        store.writeStash(firstStash(), firstStash + Extension);
        store.writeStash(secondStash(), secondStash + Extension);
        store.writeStash(thirdStash(), thirdStash + Extension);
        store.writeStash(dependantStashByUse(), dependantStashByUse + Extension);
        store.writeStash(independentStashInSubStash(), independentStash + Extension);
        Stash stash = new InMemoryFileStore(temp).stashFrom(secondStash + Extension);
        assertThat(stash.uses.size(), is(5));
    }

}