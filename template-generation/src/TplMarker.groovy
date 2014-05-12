class TplMarker {


    public static final String extensionPath = "template-generation/extensions/"

    public static String addMarks(String text) {
        text = text.replaceAll("tara", "::projectName::")
        text = text.replaceAll("Tara", "::projectProperName::").replaceAll("Concept", "Definition")
        text = text.replaceAll("TARA", "::projectUpperName::")
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

    public static String addJavaMarks(String text) {
        int index = text.indexOf("//gen")
        int endIndex = text.indexOf("//end")
        if (index > 0) {
            String field = text.substring(index, endIndex + 5)
            String token = field.substring(field.indexOf("%") + 1, field.lastIndexOf("%"))
            if (token != "") {
                token = token.replace("%", "|")
                text = text.replace(field, "::" + token + "::")
            } else text = text.replace(field, "")
        }
        text
    }

    public static String addXmlMarks(String text) {
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
}
