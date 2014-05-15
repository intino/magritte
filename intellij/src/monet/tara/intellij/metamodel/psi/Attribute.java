package monet.tara.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;

public interface Attribute extends Navigatable, Iconable, PsiNamedElement {

	String getType();

	@Nullable
	TaraBooleanList getBooleanList();

	@Nullable
	TaraBooleanValue getBooleanValue();

	@Nullable
	TaraDoubleList getDoubleList();

	@Nullable
	TaraDoubleValue getDoubleValue();

	@Nullable
	TaraIntegerList getIntegerList();

	@Nullable
	TaraIntegerValue getIntegerValue();

	@Nullable
	TaraNaturalList getNaturalList();

	@Nullable
	TaraNaturalValue getNaturalValue();

	@Nullable
	TaraStringList getStringList();

	@Nullable
	TaraStringValue getStringValue();
}
