// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraValue extends Value {

	@NotNull
	List<TaraBooleanValue> getBooleanValueList();

	@NotNull
	List<TaraDoubleValue> getDoubleValueList();

	@Nullable
	TaraEmptyField getEmptyField();

	@NotNull
	List<TaraExpression> getExpressionList();

	@NotNull
	List<TaraIdentifierReference> getIdentifierReferenceList();

	@NotNull
	List<TaraIntegerValue> getIntegerValueList();

	@NotNull
	List<TaraLinkValue> getLinkValueList();

	@Nullable
	TaraMeasureValue getMeasureValue();

	@NotNull
	List<TaraNaturalValue> getNaturalValueList();

	@NotNull
	List<TaraStringValue> getStringValueList();

}
