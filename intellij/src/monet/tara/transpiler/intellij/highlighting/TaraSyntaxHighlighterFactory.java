package monet.tara.transpiler.intellij.highlighting;

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import monet.tara.transpiler.intellij.metamodel.TaraSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;

public class TaraSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
	@NotNull
	@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
		return new TaraSyntaxHighlighter();
	}
}