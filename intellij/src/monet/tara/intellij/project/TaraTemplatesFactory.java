package monet.tara.intellij.project;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.file.TaraFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TaraTemplatesFactory implements FileTemplateGroupDescriptorFactory {
	@NonNls
	public static final String[] TEMPLATES = {TaraTemplates.CONCEPT};

	public static TaraTemplatesFactory getInstance() {
		return TaraTemplatesFactoryHolder.myInstance;
	}

	public static PsiFile createFromTemplate(@NotNull final PsiDirectory directory,
	                                         @NotNull String fileName,
	                                         @NotNull String templateName,
	                                         boolean allowReformatting) throws IncorrectOperationException {
		final FileTemplate template = FileTemplateManager.getInstance().getInternalTemplate(templateName);
		Project project = directory.getProject();
		template.setExtension(TaraFileType.INSTANCE.getDefaultExtension());
		assert template.isTemplateOfType(TaraFileType.INSTANCE);
		PsiFile file = PsiFileFactory.getInstance(project).createFileFromText(fileName, TaraFileType.INSTANCE, template.getText());
		file = (PsiFile) directory.add(file);
		if (file != null && allowReformatting && template.isReformatCode())
			new ReformatCodeProcessor(project, file, null, false).run();
		return file;
	}

	public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
		final FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("Tara", TaraIcons.ICON_13);
		final FileTypeManager fileTypeManager = FileTypeManager.getInstance();
		for (String template : TEMPLATES)
			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
		return group;
	}

	private static class TaraTemplatesFactoryHolder {
		private static final TaraTemplatesFactory myInstance = new TaraTemplatesFactory();
	}

}
