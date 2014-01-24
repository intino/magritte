package monet.tara.transpiler.intellij.plugingeneration;

import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.intellij.grammar.generator.ParserGenerator;
import org.intellij.grammar.psi.BnfFile;

import java.io.IOException;

public class BnfToJavaGenerator {
	RunContentDescriptor console;

	public static void bnfToJava(Project project, VirtualFile bnfVirtualFile, String outputPath, RunContentDescriptor console) throws IOException {
		PsiFile bnfFile = PsiManager.getInstance(project).findFile(bnfVirtualFile);
		new ParserGenerator((BnfFile) bnfFile, outputPath, outputPath).generate();
	}
}
