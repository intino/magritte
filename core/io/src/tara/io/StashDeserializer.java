package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class StashDeserializer extends Deserializer {

	private static final Logger LOG = Logger.getLogger(StashDeserializer.class.getName());

	static {
		Log.ERROR();
	}

	private StashDeserializer() {

	}

	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(bytesFrom(inputStream));
	}

	private static Stash stashFrom(byte[] bytes) {
		Stash result = null;
		try (Input input = new Input(bytes)) {
			final Kryo kryo = new Kryo();
			kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
			result = kryo.readObject(input, Stash.class);
		} catch (KryoException e) {
			LOG.severe(e.getMessage());
		}
		return result;
	}
}
