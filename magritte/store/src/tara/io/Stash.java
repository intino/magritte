package tara.io;

public class Stash {
    public Entry[] entries;

    public Stash() {
    }

    public Stash(Entry... entry) {
        this.entries = entry;
    }
}
