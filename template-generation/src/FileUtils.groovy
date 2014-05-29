import java.nio.file.FileSystemException

class FileUtils {

    public static Boolean copyDir(String sSource, String sDestination) throws FileSystemException {
        File oSource = new File(sSource);
        File oDestination = new File(sDestination);
        return copyDir(oSource, oDestination);
    }

    public static Boolean copyDir(File oSource, File oDestination) throws FileSystemException {
        try {
            if (oSource.exists()) {
                if (oSource.isDirectory()) {
                    if (!oDestination.exists())
                        oDestination.mkdir();
                    String[] children = oSource.list();
                    for (String aChildren : children)
                        copyDir(new File(oSource, aChildren), new File(oDestination, aChildren));
                } else {
                    InputStream ins = new FileInputStream(oSource);
                    OutputStream out = new FileOutputStream(oDestination);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = ins.read(buf)) > 0)
                        out.write(buf, 0, len);
                    ins.close();
                    out.close();
                }
                return true;
            }
        } catch (IOException oException) {
            throw new FileSystemException(oException.getMessage(), oSource.getName(), oException.getMessage());
        }
        return false;
    }


    public static Boolean copyFile(File source, File destination) {
        new File(destination.getParentFile().getAbsolutePath()).mkdirs();
        try {
            FileInputStream inFile = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inFile.read(buf)) > 0)
                out.write(buf, 0, len);
            inFile.close();
            out.close();
        } catch (IOException ignored) {
            println "error copying " + ignored.getMessage()
            false;
        }
        true;
    }

    public static Boolean removeDir(String sDirname) {
        File oFile = new File(sDirname);
        return removeDir(oFile);
    }

    public static Boolean removeDir(File oFile) {
        if (oFile.exists()) {
            File[] aFiles = oFile.listFiles();
            assert aFiles != null;
            for (File aFile : aFiles)
                if (aFile.isDirectory()) removeDir(aFile.getAbsolutePath());
                else aFile.delete();
        } else
            return true;
        return oFile.delete();
    }

    public static String getRelativeTplPath(File newFile) {
        String IDE_TPL = "intellij/tpl/"
        String path = newFile.getAbsolutePath()
        def substring = path.substring(path.indexOf(IDE_TPL))
        substring.substring(0, substring.lastIndexOf("."))
    }

}
