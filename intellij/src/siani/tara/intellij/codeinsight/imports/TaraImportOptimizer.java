package siani.tara.intellij.codeinsight.imports;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.Import;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaraImportOptimizer implements ImportOptimizer {
	@Override
	public boolean supports(PsiFile file) {
		return file instanceof TaraBoxFile;
	}

	@NotNull
	@Override
	public Runnable processFile(final PsiFile file) {
		return new Runnable() {
			@Override
			public void run() {
				if (file instanceof TaraBoxFile) new ImportsOptimizer((TaraBoxFile) file).run();
			}
		};
	}

	private static class ImportsOptimizer {
		private final TaraBoxFile file;
		private final Collection<Import> myImportBlock;
		private final TaraElementFactory generator;

		private ImportsOptimizer(TaraBoxFile file) {
			this.file = file;
			myImportBlock = this.file.getImports();
			generator = TaraElementFactory.getInstance(this.file.getProject());
		}

		public void run() {
			if (myImportBlock == null) return;
			deleteUnnecessaryImportStatement();
			deleteDuplicates();
			deleteUnusedImportStatement();
		}

		private void deleteUnnecessaryImportStatement() {
			String packageText = file.getBoxReference().getHeaderReference().getText() + ".";
			for (Import anImport : myImportBlock)
				if (!anImport.getHeaderReference().getText().replace(packageText, "").contains("."))
					anImport.delete();
		}

		private void deleteUnusedImportStatement() {
			Collection<IdentifierReference> identifierReferences = PsiTreeUtil.collectElementsOfType(file, IdentifierReference.class);
			Set<String> neededReferences = new HashSet<>();
			for (IdentifierReference reference : identifierReferences) {
				PsiElement resolve = ReferenceManager.resolve(reference);
				if (resolve == null) continue;
				neededReferences.add(((TaraBoxFile) resolve.getContainingFile()).getBoxReference().getHeaderReference().getText());
			}
			for (Import anImport : myImportBlock)
				if (!neededReferences.contains(anImport.getHeaderReference().getText()) && !anImport.isMetamodelImport())
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
