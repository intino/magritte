package tara.compiler.core;


import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReaderSource extends AbstractReaderSource {

	private static final Logger LOG = Logger.getLogger(FileReaderSource.class.getName());

	private static final Charset UTF8 = Charset.forName("UTF-8");
	private File file;

	public FileReaderSource(File file, CompilerConfiguration configuration) {
		super(configuration);
		this.file = file;
	}

	public Reader getReader() throws IOException {
		Charset cs = Charset.forName(this.configuration.sourceEncoding());
		InputStream in = new BufferedInputStream(new FileInputStream(this.file));
		if (UTF8.name().equalsIgnoreCase(cs.name())) {
			in.mark(3);
			boolean hasBOM = true;
			try {
				int i = in.read();
				hasBOM &= i == 239;
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
