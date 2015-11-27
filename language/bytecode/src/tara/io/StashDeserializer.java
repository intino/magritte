package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class StashDeserializer {

	private static final Logger LOG = Logger.getLogger(StashDeserializer.class.getName());

	private StashDeserializer() {
	}

	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(bytesFrom(inputStream));
	}

	private static byte[] bytesFrom(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			return new byte[0];
		}
	}

	private static byte[] bytesFrom(InputStream inputStream) {
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) buffer.write(data, 0, nRead);
			buffer.flush();
			inputStream.close();
			buffer.close();
			return buffer.toByteArray();
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			return new byte[0];
		}
	}

	private static Stash stashFrom(byte[] bytes) {
		Stash result;
		try (Input input = new Input(bytes)) {
			final Kryo kryo = new Kryo();
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(Stash.class, 1);
			kryo.register(Case.class, 2);
			kryo.register(Variable.class, 3);
			result = kryo.readObject(input, Stash.class);
		} catch (KryoException e) {
			LOG.severe(e.getMessage());
			result = new Stash();
		}
		return result;
	}

}
