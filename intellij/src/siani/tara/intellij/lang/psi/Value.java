package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Value extends Navigatable, Iconable, TaraPsiElement {

	@NotNull
	Object[] getValues();


	@Nullable
	TaraMeasureValue getMeasureValue();
}
