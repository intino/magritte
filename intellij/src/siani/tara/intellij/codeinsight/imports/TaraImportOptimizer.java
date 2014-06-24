package siani.tara.intellij.codeinsight.imports;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.Import;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.TaraFile;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TaraImportOptimizer implements ImportOptimizer {
	@Override
	public boolean supports(PsiFile file) {
		return file instanceof TaraFile;
	}

	@NotNull
	@Override
	public Runnable processFile(final PsiFile file) {
		return new Runnable() {
			@Override
			public void run() {
				if (file instanceof TaraFile) new ImportsOptimizer((TaraFile) file).run();
			}
		};
	}

	private static class ImportsOptimizer {
		private final TaraFile file;
		private final Import[] myImportBlock;
		private final TaraElementFactory generator;

		private ImportsOptimizer(TaraFile file) {
			this.file = file;
			myImportBlock = this.file.getImports();
			generator = TaraElementFactory.getInstance(this.file.getProject());
		}

		public void run() {
			if (myImportBlock == null) return;
			deleteUnnecessaryImportStatement();
			deleteDuplicates();
		}

		private void deleteUnnecessaryImportStatement() {
			String packageText = file.getBoxReference().getHeaderReference().getText() + ".";
			for (Import anImport : myImportBlock)
				if (!anImport.getHeaderReference().getText().replace(packageText, "").contains("."))
					anImport.delete();
		}

		private void deleteDuplicates() {
			Set<Import> set = new HashSet<>();
			for (Import anImport : myImportBlock)
				if (isInSet(set, anImport))
					anImport.delete();
		}

		private boolean isInSet(Set<Import> set, Import anImport) {
			for (Import anImport1 : set) if (anImport1.getText().equals(anImport.getText())) return true;
			set.add(anImport);
			return false;
		}

	}
}
