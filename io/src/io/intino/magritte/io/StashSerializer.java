package io.intino.magritte.io;

import com.esotericsoftware.kryo.io.Output;
import io.intino.magritte.io.model.Stash;

import java.io.IOException;

public class StashSerializer {
	public static byte[] serialize(Stash stash) {
		try {
			return doSerialize(stash);
		} catch (IOException e) {
			throw new StoreException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Stash stash) throws IOException {
		try (Output output = new Output(4096, -1)) {
			KryoFactory.get().writeObject(output, stash);
			output.flush();
			output.close();
			return output.toBytes();
		}
	}
}