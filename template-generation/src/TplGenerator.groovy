public static void main(String[] args) {
    String TPL_PATH = "rt/res/intellij/tpl";
    String SRC_PATH = "intellij/src";
    String RES_PATH = "intellij/res";
    File templateBound = new File("rt/res/intellij/templates.properties")
    templateBound.delete()
    templateBound.createNewFile();
    new File(TPL_PATH).deleteDir();
    File[] srcFiles = new File(SRC_PATH).listFiles()
    fixTypes()
    createTPLs(TPL_PATH, srcFiles, templateBound)
    createTPLs(TPL_PATH, new File(RES_PATH).listFiles(), templateBound)
    new File(TPL_PATH + "/src/monet/tara/intellij/metamodel/psi/-Types.java.tpl").createNewFile()
    templateBound.append("Types.java" + " = " + "/intellij/tpl/src/monet/tara/intellij/metamodel/psi/-Types.java")
    addLangHeritage("rt/src/monet/tara/lang", TPL_PATH + "/src/monet/tara/lang")
}


private static void createTPLs(String tplPath, File[] files, File templateBound) {
    File file = new File(tplPath)
    files.each {
        if (it.isDirectory()) {
            if (!it.name.contains("compiler")) {
                file = new File(tplPath + it.getPath().substring(8) + "/")
                file.mkdirs()
                createTPLs(tplPath, it.listFiles(), templateBound)
            }
        } else {
            def fileName = it.name.replaceAll("Tara", "-").replace("Concept", "_").replace("m2", "m1")
            if (!it.name.startsWith(".") && !it.text.startsWith("/*") && isCorrectFileType(it.name)) {
                String text = it.text;
                if (!it.getName().endsWith("png")) {
                    text = scapeMetaCharacters(text)
                    text = addMarks(text)
                    if (it.getName().endsWith("xml"))
                        text = addXmlMarks(text)
                    else text = addJavaMarks(text)
                    text = text.replaceAll("%", "\\\\%")
                    File newFile = new File(tplPath + it.parent.substring(8), fileName + ".tpl")
                    newFile.write(text)
                    templateBound.append(it.getName() + " = " + getRelativeTplPath(newFile) + "\n");
                } else
                    copyFile(it, new File(tplPath + it.parent.substring(8), fileName))
            } else if (it.name.endsWith(".bnf") || it.name.endsWith(".flex")) {
                String mfile;
                if (it.name.endsWith(".bnf")) mfile = "m1Grammar.tpl";
                else mfile = it.name.contains("Highlighter") ? "m1HighlightLex.tpl" : "m1Lexer.tpl"
                String text = (new File("intellij/res/tpl/" + mfile)).text.replaceAll("%", "\\\\%")
                File newFile = new File(tplPath + it.parent.substring(8), fileName + ".tpl")
                newFile.write(text)
                templateBound.append(it.getName() + " = " + getRelativeTplPath(newFile) + "\n");
            }
        }
    }
}

private static String getRelativeTplPath(File newFile) {
    String IDE_TPL = "intellij/tpl/"
    String path = newFile.getAbsolutePath()
    def substring = path.substring(path.indexOf(IDE_TPL))
    substring.substring(0, substring.lastIndexOf("."))
}

private static String addMarks(String text) {
    text = text.replaceAll("tara", "::projectName::")
    text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
    text = text.replaceAll("TARA", "::projectUpperName::")
    text = text.replaceAll("m2", "m1")
    text = text.replaceAll("concept", "definition")
    text = text.replaceAll("CONCEPT", "DEFINITION")
    text
}

private static String scapeMetaCharacters(String text) {
    text = text.replaceAll("\\\\", "\\\\\\\\")
    text = text.replaceAll(":", "\\\\:")
    text = text.replaceAll("@", "\\\\@")
    text = text.replaceAll("#", "\\\\#")
    text
}

private static String addJavaMarks(String text) {
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

private static String addXmlMarks(String text) {
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

static void fixTypes() {
    File file = new File("intellij/gen/monet/tara/intellij/metamodel/psi/TaraTypes.java")
    file.write(file.text.replace("new TaraTokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;"))
}

static boolean isCorrectFileType(String fileName) {
    String[] fileTypes = ["java", "xml", "form", "properties", "png", "html", "ft", "json"]
    if (fileTypes.contains(fileName.substring(fileName.lastIndexOf(".") + 1))) return true
    false
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
        false;
    }
    true;
}

public static void addLangHeritage(String astDir, String destinyDir) {
    new File(destinyDir).mkdirs()
    copyFile(new File(astDir, "AST.java"), new File(destinyDir, "AST.java.tpl"));
    copyFile(new File(astDir, "ASTNode.java"), new File(destinyDir, "ASTNode.java.tpl"));
}