package siani.tara.magritte.helper;

import java.io.*;

public class FileSystem {

    private final File file;

    public FileSystem(String path) {
        file = new File(path);
    }

    public static void deleteFolder(String path) {
        new FileSystem(path).delete();
    }

    public static File copy(String source, String target) throws IOException {
        return copy(new File(source), new File(target));
    }

    private static File copy(File source, File target) throws IOException {
        return source.isDirectory() ? copyFolder(source, target) : copyFile(source, target);
    }

    private static File copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[4096];
            while (true) {
                int length = in.read(buffer);
                if (length <= 0) break;
                out.write(buffer, 0, length);
            }
            return target;
        }
    }


    private static File copyFolder(File source, File target) throws IOException {
        if(!target.exists()) target.mkdir();
        File files[] = source.listFiles();
        for (File file : files) copy(file, new File(target, file.getName()));
        return target;
    }


    public void delete() {
        if (file.exists()) delete(file);
    }

    private void delete(File file) {
        if (file.isDirectory() && file.list().length > 0) delete(file.listFiles());
        file.delete();
    }

    private void delete(File[] files) {
        for (File file : files) delete(file);
    }
}