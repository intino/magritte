package tara.intellij.framework;

import com.intellij.openapi.application.ApplicationManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.nio.channels.Channels.newChannel;

public class LanguageNetImporter {

	private final String source;

	public LanguageNetImporter(String source) {
		this.source = source;
	}

	public void downloadTo(File destiny) {
		ApplicationManager.getApplication().invokeLater(() -> {
			try {
				destiny.getParentFile().mkdirs();
				new FileOutputStream(destiny).getChannel().transferFrom(newChannel(new URL(source).openStream()), 0, Long.MAX_VALUE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
}
