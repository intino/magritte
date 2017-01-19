package io.intino.tara.magritte.types;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ResX {

    private URL url;

    public ResX(URL url) {
        this.url = url;
    }

    public String getQuery() {
        return url.getQuery();
    }

    public String getPath() {
        return url.getPath();
    }

    public String getUserInfo() {
        return url.getUserInfo();
    }

    public String getAuthority() {
        return url.getAuthority();
    }

    public int getPort() {
        return url.getPort();
    }

    public int getDefaultPort() {
        return url.getDefaultPort();
    }

    public String getProtocol() {
        return url.getProtocol();
    }

    public String getHost() {
        return url.getHost();
    }

    public String getFile() {
        return url.getFile();
    }

    public String getRef() {
        return url.getRef();
    }

    public boolean sameFile(URL other) {
        return url.sameFile(other);
    }

    public String toExternalForm() {
        return url.toExternalForm();
    }

    public URI toURI() throws URISyntaxException {
        return url.toURI();
    }

    public URLConnection openConnection() throws IOException {
        return url.openConnection();
    }

    public URLConnection openConnection(Proxy proxy) throws IOException {
        return url.openConnection(proxy);
    }

    public InputStream openStream() throws IOException {
        return url.openStream();
    }

    public Object getContent() throws IOException {
        return url.getContent();
    }

    public Object getContent(Class[] classes) throws IOException {
        return url.getContent(classes);
    }

    public URL getURL() {
        return url;
    }
}
