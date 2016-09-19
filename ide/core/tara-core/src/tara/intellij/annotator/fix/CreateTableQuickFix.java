package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraTableParameters;
import tara.intellij.lang.psi.TaraWithTable;
import tara.intellij.lang.psi.impl.TaraUtil;

public class CreateTableQuickFix implements IntentionAction {
	private static final String TABLE_EXTENSION = ".table";
	private final TaraWithTable table;

	public CreateTableQuickFix(TaraWithTable name) {
		this.table = name;
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Create Data using " + table.getIdentifierReference().getText() + " table";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		VirtualFile dataRoot = TaraUtil.getResourcesRoot(table);
		final PsiDirectory directory = PsiManager.getInstance(project).findDirectory(dataRoot);
		final PsiFileFactory factory = PsiFileFactory.getInstance(project);
		PsiFile tableFile = factory.createFileFromText(table.getIdentifierReference().getText() + TABLE_EXTENSION, PlainTextFileType.INSTANCE, findParameters());
		if (directory != null) directory.add(tableFile);
		if (tableFile.canNavigate()) tableFile.navigate(true);
	}

	private String findParameters() {
		final TaraTableParameters tableParameters = table.getTableParameters();
		return tableParameters != null ? tableParameters.getText().trim().substring(1, tableParameters.getText().length() - 1).replace(",", "").replaceAll("\\s+", ";") + "\n" : "";
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
