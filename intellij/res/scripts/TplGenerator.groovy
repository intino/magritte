package scripts

String TPL_PATH = "tara_runtime/res/tpl";
String SRC_PATH = "intellij/src";
String RES_PATH = "intellij/res";

File[] srcFiles = new File(SRC_PATH).listFiles()

createTpls(TPL_PATH, srcFiles)
createTpls(TPL_PATH, new File(RES_PATH).listFiles())

private void createTpls(String tplPath, File[] files) {
    File file = new File(tplPath)
    files.each {
        if (it.isDirectory()) {
            file = new File(tplPath + "/" + it.getPath() + "/")
            file.mkdirs()
            createTpls(tplPath, it.listFiles())
        } else {
            if ((it.getName().endsWith("properties") || it.getName().endsWith("java") || it.getName().endsWith("xml") || it.getName().endsWith("form")) && !(it.text.startsWith("/*"))) {
                String text = it.text.replaceAll("::", "\\\\::")
                text = text.replaceAll("@", "\\\\@")
                text = text.replaceAll("tara", "::projectName::")
                text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
                text = text.replaceAll("concept", "definition")
                File newFile = new File(tplPath + "/" + it.parent, it.getName().replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            } else if (it.name.endsWith(".bnf") || it.name.endsWith(".flex")) {
                String mfile = (it.name.endsWith(".bnf")) ? "m1Grammar.tpl" : "m1Lexer.tpl"
                text = new File("intellij/res/scripts/" + mfile).text
                File newFile = new File(tplPath + "/" + it.parent, it.getName().replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            }
        }
    }
}


public static File[] listFiles(String path, FileFilter fileFilter) {
    ArrayList<File> javaFiles = new ArrayList<>();
    listFilesRecursive(new File(path), javaFiles, fileFilter);
    return javaFiles.toArray(new File[javaFiles.size()]);

}

private static void listFilesRecursive(File path, ArrayList<File> javaFiles, FileFilter filter) {
    for (File file : path.listFiles(filter))
        if (file.isDirectory()) listFilesRecursive(file, javaFiles, filter);
        else javaFiles.add(file);
}