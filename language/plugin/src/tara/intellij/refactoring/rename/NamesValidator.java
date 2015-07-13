package tara.intellij.refactoring.rename;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NamesValidator implements com.intellij.lang.refactoring.NamesValidator {

	private static List<String> javaKeywords = new ArrayList<>();

	static {
		javaKeywords.add("abstract");
		javaKeywords.add("continue");
		javaKeywords.add("for");
		javaKeywords.add("new");
		javaKeywords.add("switch");
		javaKeywords.add("assert");
		javaKeywords.add("default");
		javaKeywords.add("package");
		javaKeywords.add("synchronized");
		javaKeywords.add("boolean");
		javaKeywords.add("do");
		javaKeywords.add("if");
		javaKeywords.add("private");
		javaKeywords.add("this");
		javaKeywords.add("break");
		javaKeywords.add("double");
		javaKeywords.add("implements");
		javaKeywords.add("protected");
		javaKeywords.add("throw");
		javaKeywords.add("byte");
		javaKeywords.add("else");
		javaKeywords.add("import");
		javaKeywords.add("public");
		javaKeywords.add("throws");
		javaKeywords.add("case");
		javaKeywords.add("enum");
		javaKeywords.add("instanceof");
		javaKeywords.add("return");
		javaKeywords.add("transient");
		javaKeywords.add("catch");
		javaKeywords.add("extends");
		javaKeywords.add("int");
		javaKeywords.add("short");
		javaKeywords.add("try");
		javaKeywords.add("char");
		javaKeywords.add("final");
		javaKeywords.add("interface");
		javaKeywords.add("static");
		javaKeywords.add("void");
		javaKeywords.add("class");
		javaKeywords.add("finally");
		javaKeywords.add("long");
		javaKeywords.add("strictfp");
		javaKeywords.add("volatile");
		javaKeywords.add("const");
		javaKeywords.add("float");
		javaKeywords.add("native");
		javaKeywords.add("super");
		javaKeywords.add("while");
	}

	public boolean isKeyword(@NotNull final String name, final Project project) {
		return javaKeywords.contains(name);
	}

	public boolean isIdentifier(@NotNull final String name, final Project project) {
		return !javaKeywords.contains(name);
	}
}