package monet.tafat.intellij.highlighting;

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import monet.tafat.intellij.metamodel.TafatSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;

public class TafatSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
	@NotNull
	@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
		return new TafatSyntaxHighlighter();
	}
}