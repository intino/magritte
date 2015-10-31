package tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.lang.model.rules.variable.WordRule;
import tara.lang.semantics.Constraint;

import java.util.stream.Collectors;

public class TaraWordReferenceSolver extends TaraReferenceSolver {

	private final Constraint.Parameter parameterAllow;

	public TaraWordReferenceSolver(PsiElement element, TextRange range, Constraint.Parameter parameterAllow) {
		super(element, range);
		this.parameterAllow = parameterAllow;
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		return myElement;
	}

	@Override
	protected PsiElement doMultiResolve() {
		return myElement;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return ((WordRule) parameterAllow.rule()).words().stream().
			map(node -> LookupElementBuilder.create(node).withIcon(TaraIcons.ICON_13).withTypeText("Word")).
			collect(Collectors.toList()).
			toArray();
	}


}
