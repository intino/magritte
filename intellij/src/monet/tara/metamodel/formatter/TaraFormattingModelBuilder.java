package monet.tara.metamodel.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFormattingModelBuilder implements FormattingModelBuilder {
	@NotNull
	@Override
	public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
		return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
				new TaraBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment()), settings);
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
		return null;
	}
}