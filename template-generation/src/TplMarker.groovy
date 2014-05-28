class TplMarker {


    public static final String extensionPath = "template-generation/extensions/"

    public static String addMarks(String text) {
        int index = 0
        while ((index = text.indexOf("tara", index)) > 0) {
            if (text.indexOf("monet.tara.lang", index - 6) + 6 != index)
                text = text.substring(0, index) + "::projectName::" + text.substring(index + "tara".length(), text.length())
            index++
        }
        index = 0
        while ((index = text.indexOf("TARA", index)) > 0) {
            if (text.indexOf("Icons.TARA", index - 6) + 6 != index)
                text = text.substring(0, index) + "::projectUpperName::" + text.substring(index + "TARA".length(), text.length())
            index++
        }
        text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
        text = text.replaceAll("m2", "m1")
        text = text.replaceAll("concept", "definition")
        text = text.replaceAll("CONCEPT", "DEFINITION")
        text
    }

    public static String scapeMetaCharacters(String text) {
        text = text.replaceAll("\\\\", "\\\\\\\\")
        text = text.replaceAll(":", "\\\\:")
        text = text.replaceAll("@", "\\\\@")
        text = text.replaceAll("#", "\\\\#")
        text
    }

    public static String addExtensions(String fileName, String text) {
        def offset = fileName.lastIndexOf(File.separator)
        String route = fileName.substring(0, offset).replaceAll(File.separator, ".");
        String objectName = fileName.substring(offset, fileName.length()).replaceAll("\\..*", ".tpl")
        URL resource = TplMarker.class.getResource(extensionPath + route + objectName)
        if (resource == null) return text;
        String extension = new File(resource.getPath()).text;
        int index = text.indexOf("//%extension%")
        int endIndex = text.indexOf("//end_extension")
        if (index > 0) {
            String field = text.substring(index, endIndex + 15)
            text = text.replace(field, extension);
        }
        text
    }


    public static String addXmlExtensions(String fileName, String text) {
        def tag = "<!--%extensions%-->"
        def offset = fileName.lastIndexOf(File.separator)
        String route = fileName.substring(0, offset).replaceAll(File.separator, ".");
        String objectName = fileName.substring(offset, fileName.length()).replaceAll("\\..*", ".tpl")
        URL resource = TplMarker.class.getResource(extensionPath + route + objectName)
        if (resource == null) return text;
        String extension = new File(resource.getPath()).text;
        int index = text.indexOf(tag)
        if (index > 0) {
            String field = text.substring(index, index + tag.length())
            text = text.replace(field, extension);
        }
        text
    }

    public static String addJavaGenMarks(String text, String replace) {
        text = addMarksWithSubstitution(text, replace)
        text
    }

    public static String processExtensions(String text) {
        final String tag = "@Extensible(tag ="
        int from = 0
        while ((from = text.indexOf(tag, from)) > 0) {
            int to
            (text, to) = processExtensionTemplate(text, from, tag)
            text = removeAnnotation("import monet.tara.lang.Extensible;", text, from, to)
            from++
        }
        text
    }

    private static List processExtensionTemplate(String text, int from, String tag) {
        int to = text.indexOf(")", from + tag.length())
        String template = text.substring(text.indexOf("\"", from) + 1, to - 1)
        String replace = getTemplateText(template)
        if (replace != null) {
            int lastIndexOfBlock = getLastIndexOfCurlyBlock(text, to)
            text = text.substring(0, lastIndexOfBlock) + replace + text.substring(lastIndexOfBlock, text.length())
        }
        [text, to]
    }

    public static String processModifications(String text) {
        final String tag = "@Modifiable(tag ="
        int from = 0
        while ((from = text.indexOf(tag, from)) > 0)
            if (from > 0) {
                int to = text.indexOf(")", from + tag.length())
                String template = text.substring(text.indexOf("\"", from) + 1, to - 1)
                String replace = getTemplateText(template)
                if (replace != null) {
                    int firstCR = text.indexOf("\n", to)
                    int lastIndexOfBlock = getLastIndexOfCurlyBlock(text, to)
                    text = text.substring(0, firstCR) + replace + text.substring(lastIndexOfBlock + 3, text.length())
                }
                text = removeAnnotation("import monet.tara.lang.Modifiable;", text, from, to)
                from++
            }
        text
    }

    static String removeAnnotation(String importSentence, String text, int from, int to) {
        String result = text.substring(0, from - 2) + text.substring(to + 1, text.length());
        def of = result.indexOf(importSentence)
        return result.substring(0, of) + result.substring(of + importSentence.length(), result.length())
    }

    static int getLastIndexOfCurlyBlock(String text, int from) {
        int firstCR = text.indexOf("\n", from)
        String tabs = ""
        int i = firstCR + 1
        while (text.charAt(i++) == "\t")
            tabs += "\t"
        String search = "\n" + tabs + "}"
        int of = text.indexOf(search, firstCR)
        of
    }

    private static String getTemplateText(String template) {
        def tpl = ExtensionFactory.getTemplate(template)
        if (tpl != null) {
            File file = new File(TplMarker.class.getResource(tpl).getPath());
            return file.text
        }
        null
    }

    private static String addMarksWithSubstitution(String text, String replace) {
        int index = -1
        while ((index = text.indexOf("//gen", index + 1)) > 0) {
            int endIndex = text.indexOf("//end")
            if (index > 0) {
                String field = text.substring(index, endIndex + 5)
                String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
                if (token != "")
                    text = text.replace(field, "::" + token + "::")
                else text = text.replace(field, "")
            }
        }

        text
    }

    public static String getGenText(String text) {
        int index = text.indexOf("//gen")
        int endIndex = text.indexOf("//end")
        if (index > 0) {
            String field = text.substring(index, endIndex + 5)
            String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
            if (token != "") {
                String[] split = token.split("%")
                if (split.length == 2) return split[0] + "|" + split[1]
                else return split[0]
            }
            return "";
        }
    }

    public static String addXmlMarks(String text) {
        int index = -1
        while ((index = text.indexOf("<!--gen", index + 1)) > 0) {
            int endIndex = text.indexOf("<!--end-->")
            String field = text.substring(index, endIndex + 10)
            String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
            if (token != "")
                text = text.replace(field, "::" + token + "::")
            else text = text.replace(field, "")
        }
        text
    }

}
