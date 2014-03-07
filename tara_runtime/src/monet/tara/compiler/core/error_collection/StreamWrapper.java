package monet.tara.compiler.core.error_collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamWrapper extends Thread {
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
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			message = buffer.toString();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public InputStream getIs() {
		return is;
	}

	public String getType() {
		return type;
	}
}
