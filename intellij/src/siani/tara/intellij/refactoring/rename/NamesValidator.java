package siani.tara.intellij.refactoring.rename;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraTypes;

public class NamesValidator implements com.intellij.lang.refactoring.NamesValidator { //TODO
	public boolean isKeyword(@NotNull final String name, final Project project) {
		return name.equals(TaraTypes.CONCEPT.toString()) || name.equals(TaraTypes.IS.toString()) || name.equals(TaraTypes.EXTENDS.toString());
	}

	public boolean isIdentifier(@NotNull final String name, final Project project) {
		return true;
	}
}