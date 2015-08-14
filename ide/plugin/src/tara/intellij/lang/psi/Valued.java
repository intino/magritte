package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.Nullable;

import static tara.language.model.Primitives.*;

public interface Valued extends Navigatable, TaraPsiElement {

	@Nullable
	TaraValue getValue();

	default String getInferredType() {
		TaraValue value = getValue();
		if (value == null) return null;
		String x = asPrimitive(value);
		if (x != null) return x;
		if (asReference(value)) return REFERENCE;
		return null;
	}

	@Nullable
	default String asPrimitive(TaraValue value) {
		if (!value.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return NATURAL;
		if (!value.getStringValueList().isEmpty()) return STRING;
		return null;
	}

	default boolean asReference(TaraValue value) {
		return !value.getInstanceNameList().isEmpty() || !value.getIdentifierReferenceList().isEmpty() || value.getEmptyField() != null;
	}

}
