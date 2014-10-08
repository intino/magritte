// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

public class TaraAttributeImpl extends AttributeMixin implements TaraAttribute {

	public TaraAttributeImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitAttribute(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraAnnotationsAndFacets getAnnotationsAndFacets() {
		return findChildByClass(TaraAnnotationsAndFacets.class);
	}

	@Override
	@Nullable
	public TaraAttributeType getAttributeType() {
		return findChildByClass(TaraAttributeType.class);
	}

	@Override
	@Nullable
	public TaraBooleanValue getBooleanValue() {
		return findChildByClass(TaraBooleanValue.class);
	}

	@Override
	@Nullable
	public TaraCoordinateValue getCoordinateValue() {
		return findChildByClass(TaraCoordinateValue.class);
	}

	@Override
	@Nullable
	public TaraDateValue getDateValue() {
		return findChildByClass(TaraDateValue.class);
	}

	@Override
	@Nullable
	public TaraDoc getDoc() {
		return findChildByClass(TaraDoc.class);
	}

	@Override
	@Nullable
	public TaraDoubleValue getDoubleValue() {
		return findChildByClass(TaraDoubleValue.class);
	}

	@Override
	@Nullable
	public TaraEmptyField getEmptyField() {
		return findChildByClass(TaraEmptyField.class);
	}

	@Override
	@Nullable
	public TaraIdentifierReference getIdentifierReference() {
		return findChildByClass(TaraIdentifierReference.class);
	}

	@Override
	@Nullable
	public TaraIntegerValue getIntegerValue() {
		return findChildByClass(TaraIntegerValue.class);
	}

	@Override
	@Nullable
	public TaraMeasure getMeasure() {
		return findChildByClass(TaraMeasure.class);
	}

	@Override
	@Nullable
	public TaraNaturalValue getNaturalValue() {
		return findChildByClass(TaraNaturalValue.class);
	}

	@Override
	@Nullable
	public TaraPortValue getPortValue() {
		return findChildByClass(TaraPortValue.class);
	}

	@Override
	@Nullable
	public TaraStringValue getStringValue() {
		return findChildByClass(TaraStringValue.class);
	}

	@Override
	@Nullable
	public TaraWord getWord() {
		return findChildByClass(TaraWord.class);
	}

}
