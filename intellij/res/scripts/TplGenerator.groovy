package scripts

String TPL_PATH = "tara_runtime/res/tpl";
String SRC_PATH = "intellij/src";

File[] files = new File(SRC_PATH).listFiles()

createTpls(TPL_PATH, files)

private void createTpls(String TPL_PATH, File[] files) {
    File file = new File(TPL_PATH)
    files.each {
        if (it.isDirectory()) {
            file = new File(TPL_PATH + "/" + it.getPath() + "/")
            file.mkdirs()
            createTpls(TPL_PATH, it.listFiles())
        } else {
            if ((it.getName().endsWith("java") || it.getName().endsWith("xml") || it.getName().endsWith("form")) && !(it.text.startsWith("/*"))) {
                String text = it.text.replaceAll("::", "\\\\::")
                text = text.replaceAll("@", "\\\\@")
                text = text.replaceAll("tara", "::projectName::")
                text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
                text = text.replaceAll("concept", "definition")
                File newFile = new File(TPL_PATH + "/" + it.parent, it.getName().replaceAll("Tara", "-").replace("Concept", "_") + ".tpl")
                newFile.write(text)
            }
//            else {
//                File newFile = new File(TPL_PATH + "/" + it.parent, it.getName())
//                newFile.write(it.text);
//            }
        }
    }

}