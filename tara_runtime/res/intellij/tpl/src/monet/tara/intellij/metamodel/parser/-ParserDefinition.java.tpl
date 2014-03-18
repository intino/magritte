package monet.::projectName::.intellij.metamodel.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import monet.::projectName::.intellij.metamodel.lexer.::projectProperName::LexerAdapter;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::ParserDefinition implements ParserDefinition {
	public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
	public static final TokenSet COMMENTS = TokenSet.create(::projectProperName::Types.DOC);

	public static final IFileElementType FILE = new IFileElementType(Language.findInstance(::projectProperName::Language.class));

	\@NotNull
	\@Override
	public com.intellij.lexer.Lexer createLexer(Project project) {
		return new ::projectProperName::LexerAdapter();
	}

	\@NotNull
	public TokenSet getWhitespaceTokens() {
		return WHITE_SPACES;
	}

	\@NotNull
	public TokenSet getCommentTokens() {
		return COMMENTS;
	}

	\@NotNull
	public TokenSet getStringLiteralElements() {
		return TokenSet.EMPTY;
	}

	\@NotNull
	public com.intellij.lang.PsiParser createParser(final Project project) {
		return new ::projectProperName::Parser();
	}

	\@Override
	public IFileElementType getFileNodeType() {
		return FILE;
	}

	public PsiFile createFile(FileViewProvider viewProvider) {
		return new ::projectProperName::FileImpl(viewProvider);
	}

	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
		return SpaceRequirements.MAY;
	}

	\@NotNull
	public com.intellij.psi.PsiElement createElement(ASTNode node) {
		return ::projectProperName::Types.Factory.createElement(node);
	}
}