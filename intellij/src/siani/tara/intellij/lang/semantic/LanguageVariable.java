package siani.tara.intellij.lang.semantic;


import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import siani.tara.intellij.lang.psi.Flags;
import siani.tara.intellij.lang.psi.TaraValue;
import siani.tara.intellij.lang.psi.Variable;

public class LanguageVariable extends LanguageElement implements siani.tara.semantic.model.Variable {
	private final Variable variable;

	public LanguageVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public String name() {
		return variable.getName();
	}

	@Override
	public String type() {
		return variable.getType();
	}

	@Override
	public String[] flags() {
		Flags flags = variable.getFlags();
		return flags != null ? flags.asStringArray() : new String[0];
	}

	@Override
	public void flags(String... flags) {
		variable.addFlags(flags);
	}

	@Override
	public boolean isReference() {
		return variable.isReference();
	}

	@Override
	public boolean isOverriden() {
		return variable.isOverriden();
	}

	@Override
	public Object[] defaultValue() {
		TaraValue value = variable.getValue();
		return value != null ? value.getValues() : new Object[0];
	}

	@Override
	public PsiElement element() {
		return variable;
	}

	@Override
	@NonNls
	@Contract(
		pure = true
	)
	public String toString() {
		return variable.toString();
	}
}
