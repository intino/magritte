package tara.magritte;

class Logger {

    static java.util.logging.Logger Logger;

    public static void severe(String msg) {
        if(Logger == null) Logger = java.util.logging.Logger.getLogger("False-mirror");
        Logger.severe(msg);
    }
}
