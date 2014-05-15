package monet.tara.intellij.actions;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.lang.TaraIcons;
import monet.tara.intellij.lang.file.TaraFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class TaraTemplatesFactory implements FileTemplateGroupDescriptorFactory {
	@NonNls
	public static final String[] TEMPLATES = {TaraTemplates.TARA_CONCEPT};
	@NonNls
	static final String NAME_TEMPLATE_PROPERTY = "NAME";
	static final String LOW_CASE_NAME_TEMPLATE_PROPERTY = "lowCaseName";
	private final ArrayList<String> myCustomTemplates = new ArrayList<>();

	public static TaraTemplatesFactory getInstance() {
		return TaraTemplatesFactoryHolder.myInstance;
	}

	public static PsiFile createFromTemplate(@NotNull final PsiDirectory directory,
	                                         @NotNull final String name,
	                                         @NotNull String fileName,
	                                         @NotNull String templateName,
	                                         boolean allowReformatting,
	                                         @NonNls String... parameters) throws IncorrectOperationException {
		final FileTemplate template = FileTemplateManager.getInstance().getJ2eeTemplate(templateName);
		Project project = directory.getProject();
		Properties properties = new Properties(FileTemplateManager.getInstance().getDefaultProperties(project));
		JavaTemplateUtil.setPackageNameAttribute(properties, directory);
		properties.setProperty(NAME_TEMPLATE_PROPERTY, name);
		properties.setProperty(LOW_CASE_NAME_TEMPLATE_PROPERTY, name.substring(0, 1).toLowerCase() + name.substring(1));
		for (int i = 0; i < parameters.length; i += 2)
			properties.setProperty(parameters[i], parameters[i + 1]);
		String text;
		try {
			text = template.getText(properties);
		} catch (Exception e) {
			throw new RuntimeException("Unable to load template for " + FileTemplateManager.getInstance().internalTemplateToSubject(templateName), e);
		}
		final PsiFileFactory factory = PsiFileFactory.getInstance(project);
		PsiFile file = factory.createFileFromText(fileName, TaraFileType.INSTANCE, text);
		file = (PsiFile) directory.add(file);
		if (file != null && allowReformatting && template.isReformatCode())
			new ReformatCodeProcessor(project, file, null, false).run();
		return file;
	}

	public void registerCustomTemplates(String... templates) {
		Collections.addAll(myCustomTemplates, templates);
	}

	public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
		final FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor(TaraBundle.message("file.template.group.title.tara"), TaraIcons.ICON_100);
		final FileTypeManager fileTypeManager = FileTypeManager.getInstance();
		for (String template : TEMPLATES) {
			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
		}
		// register custom templates
//		for (String template : getInstance().getCustomTemplates()) {
//			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
//		}
		return group;
	}

	public String[] getCustomTemplates() {
		return ArrayUtil.toStringArray(myCustomTemplates);
	}

	private static class TaraTemplatesFactoryHolder {
		private static final TaraTemplatesFactory myInstance = new TaraTemplatesFactory();
	}
}