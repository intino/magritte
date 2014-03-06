String TPL_PATH = "/Users/oroncal/workspace/tara/tara_runtime/res/tpl/monet/tara/intellij";
String SRC_PATH = "/Users/oroncal/workspace/tara/intellij";

File[] files = new File(SRC_PATH).listFiles(new FileFilter() {
    @Override
    boolean accept(File pathname) {
        return !pathname.getName().toLowerCase().contains("test");
    }
})

File file = new File(TPL_PATH)
files.each {
    if (it.isDirectory()) {
        file = new File(TPL_PATH + "/" + it.getPath() + "/")
        file.mkdirs()
    } else {
        String text = it.text
        text = text.replaceAll("tara", "::projectName::")
        text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
        text = text.replaceAll("concept", "definition")
        file.write(text);
    }
}
