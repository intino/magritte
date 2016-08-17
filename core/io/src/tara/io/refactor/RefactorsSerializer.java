package tara.io.refactor;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import tara.io.StoreException;

import java.io.IOException;

public class RefactorsSerializer {

	private RefactorsSerializer() {
	}

	public static byte[] serialize(Refactors refactors) {
		try {
			return doSerialize(refactors);
		} catch (IOException e) {
			throw new StoreException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Refactors refactors) throws IOException {
		try (Output output = new Output(4096, -1)) {
			Kryo kryo = new Kryo();
			kryo.register(Refactors.class, new DeflateSerializer(kryo.getDefaultSerializer(Refactors.class)));
			kryo.register(Refactors.Refactor.class, 1);
			kryo.writeObject(output, refactors);
			output.flush();
			return output.toBytes();
		}
	}

}
