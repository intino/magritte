package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.Primitive;

import static tara.lang.model.Primitive.*;

public interface Valued extends Navigatable, TaraPsiElement {

	@Nullable
	TaraValue getValue();

	default Primitive getInferredType() {
		TaraValue value = getValue();
		if (value == null) return null;
		Primitive x = asPrimitive(value);
		if (x != null) return x;
		if (!value.getInstanceNameList().isEmpty() || !value.getIdentifierReferenceList().isEmpty()) return REFERENCE;
		if (value.getEmptyField() != null) return REFERENCE;
		return null;
	}

	@Nullable
	default Primitive asPrimitive(TaraValue value) {
		if (!value.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return INTEGER;
		if (!value.getStringValueList().isEmpty()) return STRING;
		if (!value.getExpressionList().isEmpty()) return FUNCTION;
		return null;
	}

}
