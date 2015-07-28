package tara.compiler.core.errorcollection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamWrapper extends Thread {

	private static final Logger LOG = Logger.getLogger(StreamWrapper.class.getName());

	private InputStream is = null;
	private String type = null;
	private String message = null;

	private StreamWrapper(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public static StreamWrapper getStreamWrapper(InputStream is, String type) {
		return new StreamWrapper(is, type);
	}

	public String getMessage() {
		return message;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder buffer = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				buffer.append(line);
			message = buffer.toString();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public InputStream getIs() {
		return is;
	}

	public String getType() {
		return type;
	}
}
