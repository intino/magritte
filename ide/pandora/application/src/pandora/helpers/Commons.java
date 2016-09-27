package pandora.helpers;

import pandora.Method;
import pandora.Resource;
import pandora.file.FileData;
import pandora.rest.RESTService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Commons {

    public static void writeFrame(File packageFolder, String name, String format) {
        try {
            packageFolder.mkdirs();
            File file = javaFile(packageFolder, name);
            Files.write(file.toPath(), format.getBytes());
//            Logger.getGlobal().info(file.getAbsolutePath() + " generated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File javaFile(File packageFolder, String name) {
        return new File(packageFolder, prepareName(name) + ".java");
    }

    private static String prepareName(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public static String[] pathParameters(Resource resource) {
        return resource.resourceParameterList().stream().filter(p -> p.in() == Resource.Parameter.In.path)
                .map(Resource.Parameter::name).toArray(String[]::new);
    }

    public static long queryParameters(Resource resource) {
        return resource.resourceParameterList().stream().filter(p -> p.in() == Resource.Parameter.In.query).count();
    }

    public static String format(String path) {
        return path.isEmpty() ? "" : path + "/";
    }

    public static String path(Resource resource) {
        String basePath = cleanPath(resource.ownerAs(RESTService.class).path());
        String resourcePath = cleanPath(resource.path());
        return format(basePath) + resourcePath;
    }

    private static String cleanPath(String path) {
        path = path.endsWith("/") ? path.substring(path.length() - 1) : path;
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public static String returnType(Method.Response response) {
        if (response == null || response.asType() == null) return "void";
        return response.asType().type();
    }

    public static int fileParameters(Resource resource) {
        return (int) resource.resourceParameterList().stream().filter(p -> p.is(FileData.class)).count();
    }

    public static String validPackage(Object value) {
        return value.toString().replace("-", "").toLowerCase();
    }
}
