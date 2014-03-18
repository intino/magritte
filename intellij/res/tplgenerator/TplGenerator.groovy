package tplgenerator

String TPL_PATH = "tara_runtime/res/intellij/tpl";
String SRC_PATH = "intellij/src";
String RES_PATH = "intellij/res";
new File(TPL_PATH).deleteDir();
File[] srcFiles = new File(SRC_PATH).listFiles()
fixTypes()
createTPLs(TPL_PATH, srcFiles)
createTPLs(TPL_PATH, new File(RES_PATH).listFiles())

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
                    int index = text.indexOf("//gen")
                    int endIndex = text.indexOf("//end")
                    if (index > 0) {
                        String field = text.substring(index, endIndex + 5)
                        String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
                        text = text.replace(field, "::" + token + "::")
                    }
                }
                File newFile = new File(tplPath + it.parent.substring(8), it.name.replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            } else if (it.name.endsWith(".bnf") || it.name.endsWith(".flex")) {
                String mfile;
                if (it.name.endsWith(".bnf")) mfile = "m1Grammar.tpl";
                else mfile = it.name.contains("Highlighter") ? "m1HighlightLex.tpl" : "m1Lexer.tpl"
                text = new File("intellij/res/tplgenerator/" + mfile).text
                File newFile = new File(tplPath + it.parent.substring(8), it.name.replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            }
        }
    }
}

void fixTypes() {
    File file = new File("intellij/gen/monet/tara/intellij/metamodel/psi/TaraTypes.java")
    file.write(file.text.replace("new TaraTokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;"))
}

boolean isCorrectFileType(String fileName) {
    String[] fileTypes = ["java", "xml", "form", "properties", "png"]
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1)
    if (fileTypes.contains(extension)) return true
    return false
}