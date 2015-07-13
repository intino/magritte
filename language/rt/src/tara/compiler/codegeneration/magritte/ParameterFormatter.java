package tara.compiler.codegeneration.magritte;

public class ParameterFormatter {
    public static String format(String parametersWithType) {
        String result = "";
        for (String parameter : parametersWithType.split(",")) {
            String[] split = parameter.trim().split(" ");
            result += ", " + split[split.length - 1];
        }
        return result.isEmpty() ? result : result.substring(2);
    }
}
