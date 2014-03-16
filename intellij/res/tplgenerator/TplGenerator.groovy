package tplgenerator

String TPL_PATH = "tara_runtime/res/tpl";
String SRC_PATH = "intellij/src";
String RES_PATH = "intellij/res";
new File(TPL_PATH + "/intellij/").deleteDir();

File[] srcFiles = new File(SRC_PATH).listFiles()
fixTypes()
createTpls(TPL_PATH, srcFiles)
createTpls(TPL_PATH, new File(RES_PATH).listFiles())

private void createTpls(String tplPath, File[] files) {
    File file = new File(tplPath)
    files.each {
        if (it.isDirectory() && !it.name.contains("compiler")) {
            file = new File(tplPath + "/" + it.getPath() + "/")
            file.mkdirs()
            createTpls(tplPath, it.listFiles())
        } else {
            if ((it.getName().endsWith("properties") || it.getName().endsWith("java") || it.getName().endsWith("xml") || it.getName().endsWith("form")) && !(it.text.startsWith("/*"))) {
                String text = it.text.replaceAll("\\\\", "\\\\\\\\")
                text = text.replaceAll(":", "\\\\:")
                text = text.replaceAll("@", "\\\\@")
                text = text.replaceAll("#", "\\\\#")
                text = text.replaceAll("tara", "::projectName::")
                text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
                text = text.replaceAll("TARA", "::projectUpperName::")
                text = text.replaceAll("m2", "m1")
                text = text.replaceAll("concept", "definition")
                File newFile = new File(tplPath + "/" + it.parent, it.getName().replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")

                newFile.write(text)
            } else if (it.name.endsWith(".bnf") || it.name.endsWith(".flex")) {
                String mfile;
                if (it.name.endsWith(".bnf"))  mfile = "m1Grammar.tpl";
                else mfile = it.name.contains("Highlighter")? "m1HighlightLex.tpl" : "m1Lexer.tpl"
                text = new File("intellij/res/tplgenerator/" + mfile).text
                File newFile = new File(tplPath + "/" + it.parent, it.getName().replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            }
        }
    }
}




void fixTypes() {
    File file = new File("intellij/gen/monet/tara/intellij/metamodel/psi/TaraTypes.java")
    file.write(file.text.replace("new TaraTokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;"))
}