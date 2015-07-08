package siani.tara.magritte.sources;

import siani.tara.magritte.Source;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamSource implements Source {

    protected abstract InputStream inputStream();

    @Override
    public byte[] data() {
        try (InputStream input = inputStream()) {
            return read(input).toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }

    protected ByteArrayOutputStream read(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(4096);
        copy(input, output);
        output.flush();
        return output;
    }

    protected void copy(InputStream input, OutputStream output) throws IOException {
        byte[] data = new byte[16384];
        while (true) {
            int length = input.read(data, 0, data.length);
            if (length < 0) break;
            output.write(data, 0, length);
        }
    }

}
