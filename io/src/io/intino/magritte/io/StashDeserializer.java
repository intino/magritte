package io.intino.magritte.io;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.minlog.Log;
import io.intino.magritte.io.model.Stash;

import java.io.File;
import java.io.InputStream;

import static io.intino.magritte.io.Bytes.from;

public class StashDeserializer {

	public static Stash stashFrom(File file) {
		return stashFrom(from(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(Bytes.from(inputStream));
	}

	public static Stash stashFrom(byte[] bytes) {
		Stash result = null;
		try (Input input = new Input(bytes)) {
			result = KryoFactory.get().readObject(input, Stash.class);
		} catch (Throwable e) {
			Log.error(e.getMessage());
		}
		return result;
	}
}