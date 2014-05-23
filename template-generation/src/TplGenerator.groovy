public class TplGenerator {

    static String TPL_PATH = "rt/res/intellij/tpl";
    static String SRC_PATH = "intellij/src";
    static String RES_PATH = "intellij/res";
    static String RES_TEST_PATH = "rt/res_test/intellij/tpl";
    static File templateBound;

    public static void main(String[] args) {
        templateBound = new File("rt/res/intellij/templates.properties")
        templateBound.delete()
        templateBound.createNewFile();
        new File(TPL_PATH).deleteDir();
        File[] srcFiles = new File(SRC_PATH).listFiles()
        fixTypes()
        createTPLs(TPL_PATH, srcFiles, templateBound)
        createTPLs(TPL_PATH, new File(RES_PATH).listFiles(), templateBound)
        new File(TPL_PATH + "/src/monet/tara/intellij/lang/psi/-Types.java.tpl").createNewFile()
        templateBound.append("Types.java" + " = " + "/intellij/tpl/src/monet/tara/intellij/lang/psi/-Types.java")
        addLangHeritage("rt/src/monet/tara/lang", TPL_PATH + "/src/monet/tara/lang")
        File tpl_testPath = new File(RES_TEST_PATH)
        FileUtils.removeDir(tpl_testPath)
        FileUtils.copyDir(new File(TPL_PATH), tpl_testPath)
        FileUtils.copyFile(templateBound, new File("rt/res_test/intellij/templates.properties"))
    }

    private static void createTPLs(String tplPath, File[] files, File templateBound) {
        File file = new File(tplPath)
        files.each {
            if (it.isDirectory()) {
                if (!it.name.contains("compiler") && !it.name.contains("formatter")) {
                    file = new File(tplPath + it.getPath().substring(8) + "/")
                    file.mkdirs()
                    createTPLs(tplPath, it.listFiles(), templateBound)
                }
            } else {
                String fileName = it.name.replaceAll("Tara", "-").replace("Concept", "_").replace("m2", "m1")
                if (!it.name.startsWith(".") && !it.text.startsWith("/*") && isCorrectFileType(it.name)) {
                    String text = it.text;
                    if (!it.getName().endsWith("png")) {
                        String genText = TplMarker.getGenText(text)
                        text = TplMarker.scapeMetaCharacters(text)
                        text = TplMarker.processExtensions(text)
                        text = TplMarker.processModifications(text)
                        text = TplMarker.addMarks(text)
                        def path = it.path.substring(it.path.lastIndexOf(SRC_PATH) + SRC_PATH.length() + 1);
                        text = TplMarker.addExtensions(path, text)
                        if (it.getName().endsWith("xml")) text = TplMarker.addXmlMarks(text)
                        else text = TplMarker.addJavaGenMarks(text, genText)
                        text = text.replaceAll("%", "\\\\%")
                        File newFile = new File(tplPath + it.parent.substring(8), fileName + ".tpl")
                        newFile.write(text)
                        templateBound.append(it.getName() + " = " + FileUtils.getRelativeTplPath(newFile) + "\n");
                    } else if (!it.name.startsWith("Concept"))
                        FileUtils.copyFile(it, new File(tplPath + it.parent.substring(8), fileName))
                } else if (isGrammarFile(it)) {
                    processGrammarTemplates(tplPath, it, fileName, templateBound)
                }
            }
        }
    }

    private static boolean isGrammarFile(File it) {
        it.name.endsWith(".bnf") || it.name.endsWith(".flex")
    }

    private static void processGrammarTemplates(String tplPath, File it, String newFileName, File templateBound) {
        String mfile
        if (it.name.endsWith(".bnf")) mfile = "m1Grammar.tpl"
        else mfile = it.name.contains("Highlighter") ? "m1HighlightLex.tpl" : "m1Lexer.tpl"
        String text = (new File(TplGenerator.class.getResource("/template-generation/tpl/").getPath() + mfile)).text.replaceAll("%", "\\\\%")
        File newFile = new File(tplPath + it.parent.substring(8), newFileName + ".tpl")
        newFile.write(text)
        templateBound.append(it.getName() + " = " + FileUtils.getRelativeTplPath(newFile) + "\n");
    }

    static void fixTypes() {
        File file = new File("intellij/gen/monet/tara/intellij/lang/psi/TaraTypes.java")
        file.write(file.text.replace("new TaraTokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;"))
    }

    static boolean isCorrectFileType(String fileName) {
        String[] fileTypes = ["java", "xml", "form", "properties", "png", "json"]
        if (fileTypes.contains(fileName.substring(fileName.lastIndexOf(".") + 1))) return true
        false
    }


    public static void addLangHeritage(String astDir, String destinyDir) {
        new File(destinyDir).mkdirs()
        FileUtils.copyFile(new File(astDir, "AST.java"), new File(destinyDir, "AST.java.tpl"));
        FileUtils.copyFile(new File(astDir, "ASTNode.java"), new File(destinyDir, "ASTNode.java.tpl"));
        FileUtils.copyFile(new File(astDir, "ASTWrapper.java"), new File(destinyDir, "ASTWrapper.java.tpl"));
    }

}