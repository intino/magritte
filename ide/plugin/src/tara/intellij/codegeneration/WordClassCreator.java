package tara.intellij.codegeneration;

import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

public class WordClassCreator {

	private final String className;
	private final PsiDirectory destiny;

	public WordClassCreator(PsiDirectory destiny, String className) {
		this.className = className;
		this.destiny = destiny;
	}

	public void createClass() {
		if (destiny.findFile(className + ".java") != null) return;
		JavaDirectoryService.getInstance().createClass(destiny, className, "WordClass", true);
	}

}
