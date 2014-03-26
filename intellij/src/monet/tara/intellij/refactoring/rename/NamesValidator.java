package monet.tara.intellij.refactoring.rename;

import com.intellij.openapi.project.Project;

public class NamesValidator implements com.intellij.lang.refactoring.NamesValidator { //TODO
	public boolean isKeyword(final String name, final Project project) {
		return false;
	}

	public boolean isIdentifier(final String name, final Project project) {
		return true;
	}
}