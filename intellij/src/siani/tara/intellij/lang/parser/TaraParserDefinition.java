package siani.tara.intellij.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.lexer.TaraLexerAdapter;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;

public class TaraParserDefinition implements ParserDefinition {
	public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
	public static final TokenSet COMMENTS = TokenSet.create(TaraTypes.DOC);

	public static final IFileElementType FILE = new IFileElementType(Language.findInstance(TaraLanguage.class));

	@NotNull
	@Override
	public com.intellij.lexer.Lexer createLexer(Project project) {
		return new TaraLexerAdapter();
	}

	@NotNull
	public TokenSet getWhitespaceTokens() {
		return WHITE_SPACES;
	}

	@NotNull
	public TokenSet getCommentTokens() {
		return COMMENTS;
	}

	@NotNull
	public TokenSet getStringLiteralElements() {
		return TokenSet.EMPTY;
	}

	@NotNull
	public com.intellij.lang.PsiParser createParser(final Project project) {
		return new TaraParser();
	}

	@Override
	public IFileElementType getFileNodeType() {
		return FILE;
	}

	public PsiFile createFile(FileViewProvider viewProvider) {
		return new TaraFileImpl(viewProvider);
	}

	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
		return SpaceRequirements.MAY;
	}

	@NotNull
	public com.intellij.psi.PsiElement createElement(ASTNode node) {
		return TaraTypes.Factory.createElement(node);
	}
}