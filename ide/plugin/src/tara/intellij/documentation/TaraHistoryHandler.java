package tara.intellij.documentation;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.impl.TaraUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;

public class TaraHistoryHandler {

	private static final String HISTOTY_JSON = "history.json";
	private final Module module;
	private final File file;


	public TaraHistoryHandler(Module module) {
		this.module = module;
		this.file = getHistoryFile();
	}

	public File getHistoryFile() {
		final String resourcesRoot = TaraUtil.getResourcesRoot(module);
		if (resourcesRoot.isEmpty()) {
			error("resources root not found");
			return null;
		}
		return new File(resourcesRoot, HISTOTY_JSON);
	}


	public List<SimpleEntry<String, String>> extractHistory() {
		try {
			if (file == null) return null;
			if (!file.exists()) {
				file.createNewFile();
				return new LinkedList<>();
			}
			Type type = new TypeToken<List<SimpleEntry<String, String>>>() {
			}.getType();
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(file), type);
		} catch (IOException e) {
			error(e.getMessage());
		}
		return null;
	}

	public boolean saveHistory(List<SimpleEntry<String, String>> history) {
		if (file == null) return false;
		Type type = new TypeToken<List<SimpleEntry<String, String>>>() {
		}.getType();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		final Gson gson = builder.create();
		try {
			final String content = gson.toJson(history, type);
			Files.write(content.getBytes(), file);
			return true;
		} catch (IOException e) {
			error(e.getMessage());
			return false;
		}
	}

	public void error(String message) {
		Notifications.Bus.notify(new Notification("Tara", "Tara", message, NotificationType.ERROR), null);
	}


	public void addEntry(String prev, String post) {
		List<SimpleEntry<String, String>> history = extractHistory();
		if (history == null) history = new LinkedList<>();
		history.add(new SimpleEntry<>(prev, post));
		saveHistory(history);
	}
}
