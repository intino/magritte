package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.IOException;
import java.time.LocalDateTime;

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
			kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
			kryo.writeObject(output, stash);
			output.flush();
			output.close();
			return output.toBytes();
		}
	}
}
