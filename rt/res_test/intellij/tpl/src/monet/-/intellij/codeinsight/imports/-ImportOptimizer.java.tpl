package monet.::projectName::.intellij.codeinsight.imports;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.lang.psi.Import;
import monet.::projectName::.intellij.lang.psi.::projectProperName::ElementFactory;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ::projectProperName::ImportOptimizer implements ImportOptimizer {
	\@Override
	public boolean supports(PsiFile file) {
		return file instanceof ::projectProperName::File;
	}

	\@NotNull
	\@Override
	public Runnable processFile(final PsiFile file) {
		return new Runnable() {
			\@Override
			public void run() {
				if (file instanceof ::projectProperName::File) new ImportsOptimizer((::projectProperName::File) file).run();
			}
		};
	}

	private static class ImportsOptimizer {
		private final ::projectProperName::File file;
		private final Import[] myImportBlock;
		private final ::projectProperName::ElementFactory generator;

		private ImportsOptimizer(::projectProperName::File file) {
			this.file = file;
			myImportBlock = this.file.getImports();
			generator = ::projectProperName::ElementFactory.getInstance(this.file.getProject());
		}

		public void run() {
			if (myImportBlock == null) return;
			deleteUnnecessaryImportStatement();
			deleteDuplicates();
		}

		private void deleteUnnecessaryImportStatement() {
			String packageText = file.getPackage().getHeaderReference().getText() + ".";
			for (Import anImport \: myImportBlock)
				if (!anImport.getHeaderReference().getText().replace(packageText, "").contains("."))
					anImport.delete();
		}

		private void deleteDuplicates() {
			Set<Import> set = new HashSet<>();
			for (Import anImport \: myImportBlock) if (!set.add(anImport)) anImport.delete();
		}
	}
}
