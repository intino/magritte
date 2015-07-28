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
		if (!value.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return NATURAL;
		if (!value.getInstanceNameList().isEmpty()
			|| !value.getIdentifierReferenceList().isEmpty()) return REFERENCE;
		if (!value.getStringValueList().isEmpty()) return STRING;
		if (value.getEmptyField() != null) return REFERENCE;
		return null;
	}

}