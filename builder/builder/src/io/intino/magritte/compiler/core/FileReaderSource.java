package io.intino.magritte.compiler.core;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileReaderSource extends AbstractReaderSource {

	private static final Logger LOG = Logger.getGlobal();

	private static final Charset UTF8 = StandardCharsets.UTF_8;
	private File file;

	FileReaderSource(File file, CompilerConfiguration configuration) {
		super(configuration);
		this.file = file;
	}

	public Reader getReader() throws IOException {
		Charset cs = Charset.forName(this.configuration.sourceEncoding());
		InputStream in = new BufferedInputStream(new FileInputStream(this.file));
		if (UTF8.name().equalsIgnoreCase(cs.name())) {
			in.mark(3);
			boolean hasBOM;
			try {
				int i = in.read();
				hasBOM = i == 239;
				i = in.read();
				hasBOM &= i == 187;
				i = in.read();
				hasBOM &= i == 255;
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
				hasBOM = false;
			}
			if (!hasBOM) in.reset();
		}
		return new InputStreamReader(in, cs);
	}

	public File getFile() {
		return file;
	}
}
