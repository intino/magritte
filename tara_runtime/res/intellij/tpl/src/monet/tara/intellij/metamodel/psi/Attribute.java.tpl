package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;

public interface Attribute extends Navigatable, Iconable, PsiNamedElement {

	public String getType();

	\@Nullable
	::projectProperName::BooleanList getBooleanList();

	\@Nullable
	::projectProperName::BooleanValue getBooleanValue();

	\@Nullable
	::projectProperName::DoubleList getDoubleList();

	\@Nullable
	::projectProperName::DoubleValue getDoubleValue();

	\@Nullable
	::projectProperName::IntegerList getIntegerList();

	\@Nullable
	::projectProperName::IntegerValue getIntegerValue();

	\@Nullable
	::projectProperName::NaturalList getNaturalList();

	\@Nullable
	::projectProperName::NaturalValue getNaturalValue();

	\@Nullable
	::projectProperName::StringList getStringList();

	\@Nullable
	::projectProperName::StringValue getStringValue();

	\@Nullable
	::projectProperName::VariableNames getVariableNames();

}
