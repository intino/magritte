package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.IOException;
import java.time.LocalDateTime;

public class StashSerializer {
	private static final Kryo kryo = new Kryo();

	static {
		kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
		kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
	}

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
			kryo.writeObject(output, stash);
			output.flush();
			output.close();
			return output.toBytes();
		}
	}
}