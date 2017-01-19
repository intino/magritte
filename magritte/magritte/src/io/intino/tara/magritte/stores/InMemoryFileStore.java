package io.intino.tara.magritte.stores;

import io.intino.tara.io.Stash;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class InMemoryFileStore extends FileSystemStore {

    boolean firstLoad = true;

    public InMemoryFileStore(File file) {
        super(file);
    }

    @Override
    public Stash stashFrom(String path) {
        Stash stash = super.stashFrom(path);
        if (stash == null) return null;
        return firstLoad ? includeAllTheStore(stash, path) : stash;
    }

    private Stash includeAllTheStore(Stash stash, String path) {
        firstLoad = false;
        stash.uses = allStashesInStore();
        stash.uses.remove(path);
        stash.uses.remove(stash.language + ".stash");
        return stash;
    }

    private List<String> allStashesInStore() {
        try {
            List<String> result = new ArrayList<>();
            Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().toLowerCase().endsWith(".stash")) result.add(relativePathOf(file.toUri().toURL()));
                    return FileVisitResult.CONTINUE;
                }
            });
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
