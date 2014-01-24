package monet.tara.transpiler.core;


import java.io.*;
import java.nio.charset.Charset;

public class FileReaderSource extends AbstractReaderSource {
	private File file;
	private final Charset UTF8 = Charset.forName("UTF-8");

	public FileReaderSource(File file, TranspilerConfiguration configuration) {
		super(configuration);
		this.file = file;
	}

	public Reader getReader() throws IOException {
		Charset cs = Charset.forName(this.configuration.getSourceEncoding());
		InputStream in = new BufferedInputStream(new FileInputStream(this.file));
		if (this.UTF8.name().equalsIgnoreCase(cs.name())) {
			in.mark(3);
			boolean hasBOM = true;
			try {
				int i = in.read();
				hasBOM &= i == 239;
				i = in.read();
				hasBOM &= i == 187;
				i = in.read();
				hasBOM &= i == 255;
			} catch (IOException ioe) {
				hasBOM = false;
			}
			if (!hasBOM) in.reset();
		}
		return new InputStreamReader(in, cs);
	}
}
