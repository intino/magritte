package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public interface Import extends Navigatable, Iconable {

	@NotNull
	TaraHeaderReference getHeaderReference();

}
