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

import static tara.intellij.lang.psi.TaraTypes.IMPORTS;
import static tara.intellij.lang.psi.TaraTypes.NODE;

public class TaraFormattingModelBuilder implements FormattingModelBuilderEx, CustomFormattingModelBuilder {
	private static final boolean DUMP_FORMATTING_AST = false;

	private static void printAST(ASTNode node, int indent) {
		while (node != null) {
			for (int i = 0; i < indent; i++) {
				System.out.print(" ");
			}
			System.out.println(node.toString() + " " + node.getTextRange().toString());
			printAST(node.getFirstChildNode(), indent + 1);
			node = node.getTreeNext();
		}
	}

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
		if (DUMP_FORMATTING_AST) {
			System.out.println("AST tree for " + element.getContainingFile().getName() + ":");
			printAST(fileNode, 0);
		}
		final TaraBlockContext context = new TaraBlockContext(settings, createSpacingBuilder(settings), mode);
		final TaraBlock block = new TaraBlock(null, fileNode, null, Indent.getNoneIndent(), null, context);
		if (DUMP_FORMATTING_AST)
			FormattingModelDumper.dumpFormattingModel(block, 1, System.out);
		return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
	}

	protected SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
		final IFileElementType file = LanguageParserDefinitions.INSTANCE.forLanguage(TaraLanguage.INSTANCE).getFileNodeType();
		final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(TaraLanguage.INSTANCE);
		return new SpacingBuilder(commonSettings).betweenInside(NODE, NODE, file).blankLines(0)
			.between(NODE, NODE).blankLines(1)
			.after(IMPORTS).blankLines(0)
			.between(NODE, NODE).spacing(0, 1, 1, false, 1);
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