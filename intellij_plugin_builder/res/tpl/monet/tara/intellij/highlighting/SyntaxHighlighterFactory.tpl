package monet.::projectName::.intellij.highlighting;

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import monet.::projectName::.intellij.metamodel.::projectProperName::SyntaxHighlighter;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::SyntaxHighlighterFactory extends SyntaxHighlighterFactory {
	\@NotNull
	\@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
		return new ::projectProperName::SyntaxHighlighter();
	}
}