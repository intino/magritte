package tara.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraLanguage;

public class TaraFormattingModelBuilder implements FormattingModelBuilderEx, CustomFormattingModelBuilder {

	@NotNull
	@Override
	public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
		return createModel(element, settings, FormattingMode.REFORMAT);
	}

	@Override
	public boolean isEngagedToFormat(PsiElement context) {
		PsiFile file = context.getContainingFile();
		return file != null && file.getLanguage() == TaraLanguage.INSTANCE;
	}

	@NotNull
	@Override
	public FormattingModel createModel(@NotNull PsiElement element, @NotNull CodeStyleSettings settings, @NotNull FormattingMode mode) {
		final ASTNode fileNode = element.getContainingFile().getNode();
		final TaraBlockContext context = new TaraBlockContext(settings, createSpacingBuilder(settings), mode);
		final TaraBlock block = new TaraBlock(fileNode, null, Indent.getNoneIndent(), null, context);
		return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
	}

	protected SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
		final IFileElementType root = LanguageParserDefinitions.INSTANCE.forLanguage(TaraLanguage.INSTANCE).getFileNodeType();
		final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(TaraLanguage.INSTANCE);
		return new SpacingBuilder(commonSettings);
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
		return null;
	}

	@Nullable
	@Override
	public CommonCodeStyleSettings.IndentOptions getIndentOptionsToUse(@NotNull PsiFile psiFile, @NotNull FormatTextRanges formatTextRanges, @NotNull CodeStyleSettings codeStyleSettings) {
		return null;
	}

}