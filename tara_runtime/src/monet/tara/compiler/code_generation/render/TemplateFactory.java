package monet.tara.compiler.code_generation.render;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

	private static final Map<String, String> TEMPLATES = new HashMap<>();
	private static final String IDE = "intellij/";
	private static final String PROJECT_PATH = "monet/tara/" + IDE;

	public static final String CONCEPT_KEY = "_";
	public static final String PROJECT_KEY = "-";

	static {
		TEMPLATES.put("CreateFileAction.java", IDE + PROJECT_PATH + "actions/CreateFileAction.java");
		TEMPLATES.put("CreatePackageAction.java", IDE + PROJECT_PATH + "actions/CreatePackageAction.java");

		TEMPLATES.put("InSignatureFitFilter.java", IDE + PROJECT_PATH + "codeInsight/completion/InSignatureFitFilter.java");
		TEMPLATES.put("AnnotationsCompletionContributor.java", IDE + PROJECT_PATH + "codeInsight/completion/-AnnotationsCompletionContributor.java");
		TEMPLATES.put("PrimitivesCompletionContributor.java", IDE + PROJECT_PATH + "codeInsight/completion/-PrimitivesCompletionContributor.java");
		TEMPLATES.put("SignatureCompletionContributor.java", IDE + PROJECT_PATH + "codeInsight/completion/-SignatureCompletionContributor.java");
		TEMPLATES.put("InSignatureFitFilter.java", IDE + PROJECT_PATH + "codeInsight/completion/InSignatureFitFilter.java");

		TEMPLATES.put("DuplicateInspection.java", IDE + PROJECT_PATH + "codeinspection/Duplicate_Inspection.java");
		TEMPLATES.put("DuplicateInspection.form", IDE + PROJECT_PATH + "codeinspection/Duplicate_Inspection.form");
		TEMPLATES.put("UnusedAbstractInspection.java", IDE + PROJECT_PATH + "codeinspection/UnusedAbstract_Inspection.java");
		TEMPLATES.put("RemoveAttributeFix.java", IDE + PROJECT_PATH + "codeinspection/fix/RemoveAttributeFix.java");
		TEMPLATES.put("RemoveFix.java", IDE + PROJECT_PATH + "codeinspection/fix/Remove_fix.java");

		TEMPLATES.put("CompilerConfigurable.form", IDE + PROJECT_PATH + "compiler/-CompilerConfigurable.form");
		TEMPLATES.put("CompilerConfigurable.java", IDE + PROJECT_PATH + "compiler/-CompilerConfigurable.java");
		TEMPLATES.put("CompilerConfiguration.java", IDE + PROJECT_PATH + "compiler/-CompilerConfiguration.java");
		TEMPLATES.put("CompilerWorkspaceConfiguration.java", IDE + PROJECT_PATH + "compiler/-CompilerWorkspaceConfiguration.java");

		TEMPLATES.put("LoggingEventSubmitter.java", IDE + PROJECT_PATH + "diagnostic/errorreporting/LoggingEventSubmitter.java");
		TEMPLATES.put("PluginErrorReportSubmitter.java", IDE + PROJECT_PATH + "diagnostic/errorreporting/PluginErrorReportSubmitter.java");
		TEMPLATES.put("PluginErrorReportSubmitterBundle.java", IDE + PROJECT_PATH + "diagnostic/errorreporting/PluginErrorReportSubmitterBundle.java");

		TEMPLATES.put("DocumentationProvider.java", IDE + PROJECT_PATH + "documentation/-DocumentationProvider.java");

		TEMPLATES.put("Block.java", IDE + PROJECT_PATH + "formatter/-block.java");
		TEMPLATES.put("FormattingModelBuilder.java", IDE + PROJECT_PATH + "formatter/-FormattingModelBuilder.java");

		TEMPLATES.put("BraceMatcher.java", IDE + PROJECT_PATH + "highlighting/-BraceMatcher.java");
		TEMPLATES.put("ColorSettingPage.java", IDE + PROJECT_PATH + "highlighting/-ColorSettingPage.java");
		TEMPLATES.put("HighlighterLexAdapter.java", IDE + PROJECT_PATH + "highlighting/-HighlighterLexAdapter.java");
		TEMPLATES.put("SyntaxHighlighter.java", IDE + PROJECT_PATH + "highlighting/-SyntaxHighlighter.java");
		TEMPLATES.put("SyntaxHighlighterFactory.java", IDE + PROJECT_PATH + "highlighting/-SyntaxHighlighterFactory.java");

		TEMPLATES.put("FileType.java", IDE + PROJECT_PATH + "metamodel/file/-FileType.java");
		TEMPLATES.put("FileTypeFactory.java", IDE + PROJECT_PATH + "metamodel/file/-FileTypeFactory.java");
		TEMPLATES.put("LexerAdapter.java", IDE + PROJECT_PATH + "metamodel/lexer/-LexerAdapter.java");
		TEMPLATES.put("BlockManager.java", IDE + PROJECT_PATH + "metamodel/lexer/BlockManager.java");
		TEMPLATES.put("GeneratedParserUtilBase.java", IDE + PROJECT_PATH + "metamodel/parser/GeneratedParserUtilBase.java");
		TEMPLATES.put("ParserDefinition.java", IDE + PROJECT_PATH + "metamodel/parser/-ParserDefinition.java");
		TEMPLATES.put("ParserUtil.java", IDE + PROJECT_PATH + "metamodel/parser/-ParserUtil.java");
		TEMPLATES.put("Icons.java", IDE + PROJECT_PATH + "metamodel/-Icons.java");
		TEMPLATES.put("Language.java", IDE + PROJECT_PATH + "metamodel/-Language.java");
		TEMPLATES.put("Attribute.java", IDE + PROJECT_PATH + "metamodel/psi/Attribute.java");
		TEMPLATES.put("File.java", IDE + PROJECT_PATH + "metamodel/psi/-File.java");
		TEMPLATES.put("_.java", IDE + PROJECT_PATH + "metamodel/psi/_.java");
		TEMPLATES.put("Signature.java", IDE + PROJECT_PATH + "metamodel/psi/Signature.java");
		TEMPLATES.put("Body.java", IDE + PROJECT_PATH + "metamodel/psi/Body.java");
		TEMPLATES.put("ElementFactory.java", IDE + PROJECT_PATH + "metamodel/psi/-ElementFactory.java");
		TEMPLATES.put("ElementType.java", IDE + PROJECT_PATH + "metamodel/psi/-ElementType.java");
		TEMPLATES.put("TokenType.java", IDE + PROJECT_PATH + "metamodel/psi/-TokenType.java");
		TEMPLATES.put("BodyMixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/BodyMixin.java");
		TEMPLATES.put("AttributeMixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/AttributeMixin.java");
		TEMPLATES.put("SignatureMixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/SignatureMixin.java");
		TEMPLATES.put("IdentifierMixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/IdentifierMixin.java");
		TEMPLATES.put("ReferenceStatementMixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/-ReferenceStatementMixin.java");
		TEMPLATES.put("_Mixin.java", IDE + PROJECT_PATH + "metamodel/psi/impl/_Mixin.java");
		TEMPLATES.put("IdentifierManipulator.java", IDE + PROJECT_PATH + "metamodel/psi/impl/IdentifierManipulator.java");
		TEMPLATES.put("ElementFactoryImpl.java", IDE + PROJECT_PATH + "metamodel/psi/impl/-ElementFactoryImpl.java");
		TEMPLATES.put("FileImpl.java", IDE + PROJECT_PATH + "metamodel/psi/impl/-FileImpl.java");
		TEMPLATES.put("PsiImplUtil.java", IDE + PROJECT_PATH + "metamodel/psi/impl/-PsiImplUtil.java");
		TEMPLATES.put("Util.java", IDE + PROJECT_PATH + "metamodel/psi/impl/-Util.java");

		TEMPLATES.put("ModuleSettings.java", IDE + PROJECT_PATH + "project/module/ModuleSettings.java");
		TEMPLATES.put("ModuleBuilder.java", IDE + PROJECT_PATH + "project/module/ModuleBuilder.java");
		TEMPLATES.put("ModuleType.java", IDE + PROJECT_PATH + "project/module/ModuleType.java");
		TEMPLATES.put("DefaultRunner.java", IDE + PROJECT_PATH + "project/runner/DefaultRunner.java");
		TEMPLATES.put("Runner.java", IDE + PROJECT_PATH + "project/runner/Runner.java");
		TEMPLATES.put("RunConfiguration.java", IDE + PROJECT_PATH + "project/runner/RunConfiguration.java");
		TEMPLATES.put("RunConfigurationType.java", IDE + PROJECT_PATH + "project/runner/RunConfigurationType.java");
		TEMPLATES.put("RunnerUtil.java", IDE + PROJECT_PATH + "project/runner/RunnerUtil.java");
		TEMPLATES.put("RunConfigurationEditor.java", IDE + PROJECT_PATH + "project/runner/RunConfigurationEditor.java");
		TEMPLATES.put("RunConfigurationEditor.form", IDE + PROJECT_PATH + "project/runner/RunConfigurationEditor.form");

		TEMPLATES.put("NamesValidator.java", IDE + PROJECT_PATH + "refactoring/rename/NamesValidator.java");
		TEMPLATES.put("NameSuggestionProvider.java", IDE + PROJECT_PATH + "refactoring/NameSuggestionProvider.java");
		TEMPLATES.put("RenameHandler.java", IDE + PROJECT_PATH + "refactoring/rename/RenameHandler.java");

		TEMPLATES.put("FileStructureViewElement.java", IDE + PROJECT_PATH + "structureView/FileStructureViewElement.java");
		TEMPLATES.put("StructureViewElement.java", IDE + PROJECT_PATH + "structureView/StructureViewElement.java");
		TEMPLATES.put("StructureViewFactory.java", IDE + PROJECT_PATH + "structureView/StructureViewFactory.java");
		TEMPLATES.put("StructureViewModel.java", IDE + PROJECT_PATH + "structureView/StructureViewModel.java");

		TEMPLATES.put("Annotator.java", IDE + PROJECT_PATH + "-Annotator.java");
		TEMPLATES.put("Bundle.java", IDE + PROJECT_PATH + "-Bundle.java");
		TEMPLATES.put("FindUsagesProvider.java", IDE + PROJECT_PATH + "-FindUsagesProvider.java");
		TEMPLATES.put("FoldingBuilder.java", IDE + PROJECT_PATH + "-FoldingBuilder.java");
		TEMPLATES.put("RefactoringSupportProvider.java", IDE + PROJECT_PATH + "-RefactoringSupportProvider.java");
		TEMPLATES.put("Reference.java", IDE + PROJECT_PATH + "-Reference.java");
		TEMPLATES.put("ReferenceContributor.java", IDE + PROJECT_PATH + "-ReferenceContributor.java");

		TEMPLATES.put("grammar", IDE + PROJECT_PATH + "metamodel/lexer/-.flex");
		TEMPLATES.put("lexer", IDE + PROJECT_PATH + "metamodel/parser/-.bnf");

		TEMPLATES.put("plugin.xml", IDE + "META-INF/plugin.xml");

//		TEMPLATES.put("Definition", "metamodel/Definition");
//		TEMPLATES.put("Metamodel", "metamodel/Metamodel");
	}

	private static final String RES = "res/";

	public static Map<String, String> getTemplates() {
		return TEMPLATES;
	}

	public static String getTemplate(String className) {
		String template;
		if ((template = TEMPLATES.get(className)) != null)
			return template;
		else
			throw new RuntimeException("Template not defined");
	}
}