package tara.intellij.stash;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import tara.io.StashDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StashToJson {


	public static Path createJson(VirtualFile stash, File destiny) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		destiny.deleteOnExit();
		final String json = gson.toJson(StashDeserializer.stashFrom(new File(stash.getPath())));
		Files.write(destiny.toPath(), json.getBytes(), StandardOpenOption.CREATE);
		return destiny.toPath();
	}
}
