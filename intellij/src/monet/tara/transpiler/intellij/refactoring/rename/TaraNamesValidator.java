package monet.tara.transpiler.intellij.refactoring.rename;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;

/**
 * Created by oroncal on 08/01/14.
 */
public class TaraNamesValidator implements NamesValidator {
	public boolean isKeyword(final String name, final Project project) {
		return false;
	}

	public boolean isIdentifier(final String name, final Project project) {
		return true;
	}
}