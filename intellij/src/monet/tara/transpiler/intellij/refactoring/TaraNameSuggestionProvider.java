package monet.tara.transpiler.intellij.refactoring;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.refactoring.rename.NameSuggestionProvider;
import monet.tara.transpiler.intellij.psi.TaraIdentifier;
import monet.tara.transpiler.intellij.psi.TaraNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * Created by oroncal on 08/01/14.
 */
public class TaraNameSuggestionProvider implements NameSuggestionProvider {
	@Nullable
	@Override
	public SuggestedNameInfo getSuggestedNames(PsiElement element, @Nullable PsiElement nameSuggestionContext, Set<String> result) {
		if (!(element instanceof TaraNamedElement)) return null;
		final String name = ((TaraNamedElement) element).getName();
		if (name == null) return null;
		if (element instanceof TaraIdentifier)
			result.add(toCamelCase(name, true));
		else
			result.add(name.toLowerCase());
		return SuggestedNameInfo.NULL_INFO;
	}

	@NotNull
	protected String toCamelCase(@NotNull final String name, boolean uppercaseFirstLetter) {
		final List<String> strings = StringUtil.split(name, "_");
		if (strings.size() > 0) {
			final StringBuilder buf = new StringBuilder();
			String str = strings.get(0).toLowerCase();
			if (uppercaseFirstLetter) str = StringUtil.capitalize(str);
			buf.append(str);
			for (int i = 1; i < strings.size(); i++) {
				buf.append(StringUtil.capitalize(strings.get(i).toLowerCase()));
			}
			return buf.toString();
		}
		return name;
	}
}
