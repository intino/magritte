// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface TaraAttribute extends Attribute {

	@Nullable
	TaraAnnotationsAndFacets getAnnotationsAndFacets();

	@Nullable
	TaraAttributeType getAttributeType();

	@Nullable
	TaraBooleanValue getBooleanValue();

	@Nullable
	TaraCoordinateValue getCoordinateValue();

	@Nullable
	TaraDateValue getDateValue();

	@Nullable
	TaraDoc getDoc();

	@Nullable
	TaraDoubleValue getDoubleValue();

	@Nullable
	TaraEmptyField getEmptyField();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

	@Nullable
	TaraIntegerValue getIntegerValue();

	@Nullable
	TaraMeasure getMeasure();

	@Nullable
	TaraNaturalValue getNaturalValue();

	@Nullable
	TaraPortValue getPortValue();

	@Nullable
	TaraStringValue getStringValue();

	@Nullable
	TaraWord getWord();

}
