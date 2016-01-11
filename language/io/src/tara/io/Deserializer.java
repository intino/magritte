package tara.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class Deserializer {

	private static final Logger LOG = Logger.getLogger(Deserializer.class.getName());

	protected static byte[] bytesFrom(InputStream inputStream) {
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


	protected static byte[] bytesFrom(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			return new byte[0];
		}
	}

}
