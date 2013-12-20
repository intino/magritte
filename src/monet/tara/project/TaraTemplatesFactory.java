package monet.tara.project;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import monet.tara.metamodel.TaraIcons;
import monet.tara.metamodel.file.TaraFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TaraTemplatesFactory implements FileTemplateGroupDescriptorFactory {
	@NonNls
	public static final String[] TEMPLATES = { TaraTemplates.METAMODEL_UNIT };
	@NonNls
	static final String NAME_TEMPLATE_PROPERTY = "NAME";
	static final String LOW_CASE_NAME_TEMPLATE_PROPERTY = "lowCaseName";

	private static class TaraTemplatesFactoryHolder {
		private static final TaraTemplatesFactory myInstance = new TaraTemplatesFactory();
	}

	public static TaraTemplatesFactory getInstance() {
		return TaraTemplatesFactoryHolder.myInstance;
	}

	public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
		final FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("Tara", TaraIcons.ICON);
		final FileTypeManager fileTypeManager = FileTypeManager.getInstance();
		for (String template : TEMPLATES)
			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
		return group;
	}


	public static PsiFile createFromTemplate(@NotNull final PsiDirectory directory,
	                                         @NotNull final String name,
	                                         @NotNull String fileName,
	                                         @NotNull String templateName,
	                                         boolean allowReformatting,
	                                         @NonNls String... parameters) throws IncorrectOperationException {
		final FileTemplate template = FileTemplateManager.getInstance().getInternalTemplate(templateName);
		Project project = directory.getProject();
		template.setExtension(TaraFileType.INSTANCE.getDefaultExtension());
		String text = template.getText();
		assert template.isTemplateOfType(TaraFileType.INSTANCE);
		final PsiFileFactory factory = PsiFileFactory.getInstance(project);
		PsiFile file = factory.createFileFromText(fileName, TaraFileType.INSTANCE, text);
		file = (PsiFile) directory.add(file);
		if (file != null && allowReformatting && template.isReformatCode())
			new ReformatCodeProcessor(project, file, null, false).run();
		return file;
	}

}
