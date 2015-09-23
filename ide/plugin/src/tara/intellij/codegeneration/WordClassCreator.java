package tara.intellij.codegeneration;

import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;

public class WordClassCreator {

	private final String className;
	private final PsiDirectory destiny;

	public WordClassCreator(PsiDirectory destiny, String className) {
		this.className = className;
		this.destiny = destiny;
	}

	public PsiClass createClass() {
		PsiFile file = destiny.findFile(className + ".java");
		if (file != null) return null;
		return JavaDirectoryService.getInstance().createClass(destiny, className, "WordClass", true);
	}

}
