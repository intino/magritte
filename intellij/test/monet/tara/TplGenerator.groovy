package monet.tara

String TPL_PATH = "rt/res/intellij/tpl";
String SRC_PATH = "intellij/src";
String RES_PATH = "intellij/res";
new File(TPL_PATH).deleteDir();
File[] srcFiles = new File(SRC_PATH).listFiles()
fixTypes()
createTPLs(TPL_PATH, srcFiles)
createTPLs(TPL_PATH, new File(RES_PATH).listFiles())
new File(TPL_PATH + "/src/monet/tara/intellij/metamodel/psi/-Types.java.tpl").createNewFile()

void createTPLs(String tplPath, File[] files) {
    File file = new File(tplPath)
    files.each {
        if (it.isDirectory()) {
            if (!it.name.contains("compiler")) {
                file = new File(tplPath + it.getPath().substring(8) + "/")
                file.mkdirs()
                createTPLs(tplPath, it.listFiles())
            }
        } else {
            def fileName = it.name.replaceAll("Tara", "-").replace("Concept", "_").replace("m2","m1")
            if (!it.name.startsWith(".") && !it.text.startsWith("/*") && isCorrectFileType(it.name)) {
                String text = it.text;
                if (!it.getName().endsWith("png")) {
                    text = text.replaceAll("\\\\", "\\\\\\\\")
                    text = text.replaceAll(":", "\\\\:")
                    text = text.replaceAll("@", "\\\\@")
                    text = text.replaceAll("#", "\\\\#")
                    text = text.replaceAll("tara", "::projectName::")
                    text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
                    text = text.replaceAll("TARA", "::projectUpperName::")
                    text = text.replaceAll("m2", "m1")
                    text = text.replaceAll("concept", "definition")
                    text = text.replaceAll("CONCEPT", "DEFINITION")
                    if (it.getName().endsWith("xml"))
                        text = addXmlMarks(text)
                    else text = addJavaMarks(text)
                    text = text.replaceAll("%", "\\\\%")
                    File newFile = new File(tplPath + it.parent.substring(8), fileName + ".tpl")
                    newFile.write(text)
                } else
                    copyFile(it, new File(tplPath + it.parent.substring(8), fileName))
            } else if (it.name.endsWith(".bnf") || it.name.endsWith(".flex")) {
                String mfile;
                if (it.name.endsWith(".bnf")) mfile = "m1Grammar.tpl";
                else mfile = it.name.contains("Highlighter") ? "m1HighlightLex.tpl" : "m1Lexer.tpl"
                text = new File("intellij/res/tpl/" + mfile).text.replaceAll("%", "\\\\%")
                File newFile = new File(tplPath + it.parent.substring(8), fileName + ".tpl")
                newFile.write(text)
            }
        }
    }
}

private String addJavaMarks(String text) {
    int index = text.indexOf("//gen")
    int endIndex = text.indexOf("//end")
    if (index > 0) {
        String field = text.substring(index, endIndex + 5)
        String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
        if (token != "")
            text = text.replace(field, "::" + token + "::")
        else text = text.replace(field, "")
    }
    text
}

private String addXmlMarks(String text) {
    int index = 0;
    while (index >= 0) {
        index = text.indexOf("<!--gen", index)
        int endIndex = text.indexOf("<!--end-->")
        if (index > 0) {
            String field = text.substring(index, endIndex + 10)
            String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
            if (token != "")
                text = text.replace(field, "::" + token + "::")
            else text = text.replace(field, "")
        }
    }

    text
}

void fixTypes() {
    File file = new File("intellij/gen/monet/tara/intellij/metamodel/psi/TaraTypes.java")
    file.write(file.text.replace("new TaraTokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;"))
}

boolean isCorrectFileType(String fileName) {
    String[] fileTypes = ["java", "xml", "form", "properties", "png", "html", "ft"]
    if (fileTypes.contains(fileName.substring(fileName.lastIndexOf(".") + 1))) return true
    false
}

public Boolean copyFile(File source, File destination) {
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
        false;
    }
    true;
}