package tara.magritte;

class Logger {

    static java.util.logging.Logger Logger;

    public static void severe(String msg) {
        Logger.severe(msg);
    }
}
