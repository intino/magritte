package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Value extends Navigatable, Iconable, TaraPsiElement {

	@NotNull
	List<Object> getValues();

	@Nullable
	TaraMeasureValue getMeasureValue();
}
