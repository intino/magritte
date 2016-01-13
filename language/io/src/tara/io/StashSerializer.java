package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;

import java.io.IOException;

public class StashSerializer {

	private StashSerializer() {
	}

	public static byte[] serialize(Stash stash) {
		try {
			return doSerialize(stash);
		} catch (IOException e) {
			throw new StoreException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Stash stash) throws IOException {
		try (Output output = new Output(4096, -1)) {
			Kryo kryo = new Kryo();
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(Instance.class, 2);
			kryo.register(Concept.class, 3);
			kryo.register(Facet.class, 4);
			kryo.register(Concept.Content.class, 5);
			kryo.register(Variable.class, 6);
			kryo.writeObject(output, stash);
			output.flush();
			return output.toBytes();
		}
	}

}
