package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StashDeserializer {


	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	private static byte[] bytesFrom(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
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
			kryo.register(Case[].class, 3);
			kryo.register(Variable.class, 4);
			kryo.register(Variable[].class, 5);
			kryo.register(Variable[].class, 5);
			result = kryo.readObject(input, Stash.class);
		} catch (KryoException e) {
			result = new Stash();
			e.printStackTrace();
		}
		return result;
	}

}
