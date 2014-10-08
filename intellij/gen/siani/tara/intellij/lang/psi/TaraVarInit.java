// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraVarInit extends VarInit {

	@NotNull
	List<TaraBooleanValue> getBooleanValueList();

	@NotNull
	List<TaraCoordinateValue> getCoordinateValueList();

	@NotNull
	List<TaraDateValue> getDateValueList();

	@NotNull
	List<TaraDoubleValue> getDoubleValueList();

	@Nullable
	TaraEmptyField getEmptyField();

	@NotNull
	List<TaraIdentifierReference> getIdentifierReferenceList();

	@NotNull
	List<TaraIntegerValue> getIntegerValueList();

	@Nullable
	TaraMeasure getMeasure();

	@NotNull
	List<TaraNaturalValue> getNaturalValueList();

	@NotNull
	List<TaraPortValue> getPortValueList();

	@NotNull
	List<TaraStringValue> getStringValueList();

}
