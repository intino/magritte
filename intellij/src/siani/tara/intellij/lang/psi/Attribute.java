package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;

public interface Attribute extends Navigatable, Iconable, PsiNamedElement {

	String getType();

	@Nullable
	TaraBooleanValue getBooleanValue();

	@Nullable
	TaraDoubleValue getDoubleValue();

	@Nullable
	TaraIntegerValue getIntegerValue();

	@Nullable
	TaraNaturalValue getNaturalValue();

	@Nullable
	TaraStringValue getStringValue();
}
