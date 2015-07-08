package siani.tara.magritte.stores;

import siani.tara.magritte.Store;
import siani.tara.magritte.Source;
import siani.tara.magritte.Reference;
import siani.tara.magritte.schema.GateReference;
import siani.tara.magritte.sources.StreamSource;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileStore implements Store {

    private final File root;

    public FileStore(File directory) {
        this.root = directory;
        this.root.mkdirs();
    }

    public FileStore(String path) {
        this(new File(path));
    }

    @Override
    public boolean exists(Reference reference) {
        return fileOf((GateReference) reference).exists();
    }

    @Override
    public Source sourceOf(Reference reference) {
        return new FileSource(reference);
    }

    @Override
    public void save(Reference reference, byte[] data) {
        try {
            createFile((GateReference) reference, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(GateReference uid, byte[] data) throws IOException {
        File file = fileOf(uid);
        if (!file.exists()) file.createNewFile();
        write(file, data);
    }

    private File fileOf(GateReference uid) {
        return new File(directoryOf(uid), uid.value() + extensionOf(uid));
    }

    private File directoryOf(GateReference uid) {
        File file = new File(root, uid.spot());
        file.mkdirs();
        return file;
    }

    private String extensionOf(GateReference uid) {
        return uid.value().contains(".") ? "" : ".mnode";
    }

    private void write(File file, byte[] data) throws IOException {
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(data);
            output.flush();
        }
    }

    public class FileSource extends StreamSource {

        private Reference reference;

        public FileSource(Reference reference) {
            this.reference = reference;
        }

        @Override
        protected InputStream inputStream() {
            try {
                return new FileInputStream(file());
            } catch (FileNotFoundException e) {
                return emptyInputStream();
            }
        }

        @Override
        public Reference uid() {
            return reference;
        }

        private File file() {
            return fileOf((GateReference) reference);
        }

        private InputStream emptyInputStream() {
            return new ByteArrayInputStream(new byte[0]);
        }
    }
}
