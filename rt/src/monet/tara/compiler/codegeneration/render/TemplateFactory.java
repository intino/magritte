package monet.tara.compiler.codegeneration.render;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

	public static final String IDE_TPL = "intellij/tpl/";
	public static final String IDE = "intellij/";
	public static final String CONCEPT_KEY = "_";
	private static final Map<String, String> TEMPLATES = new HashMap<>();
	private static final String SRC = "src/";
	private static final String RES = "res/";
	private static final String PROJECT_PATH = "monet/tara/" + IDE;

	static {
		TEMPLATES.put("CreateFileAction.java", IDE_TPL + SRC + PROJECT_PATH + "actions/CreateFileAction.java");

		TEMPLATES.put("InSignatureFitFilter.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/InSignatureFitFilter.java");
		TEMPLATES.put("AnnotationsCompletionContributor.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/-AnnotationsCompletionContributor.java");
		TEMPLATES.put("TaraFilter.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/-Filters.java");
		TEMPLATES.put("PrimitivesCompletionContributor.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/-PrimitivesCompletionContributor.java");
		TEMPLATES.put("SignatureCompletionContributor.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/-SignatureCompletionContributor.java");
		TEMPLATES.put("InSignatureFitFilter.java", IDE_TPL + SRC + PROJECT_PATH + "codeinsight/completion/InSignatureFitFilter.java");

		TEMPLATES.put("DuplicateInspection.java", IDE_TPL + SRC + PROJECT_PATH + "codeinspection/Duplicate_Inspection.java");
		TEMPLATES.put("DuplicateInspection.form", IDE_TPL + SRC + PROJECT_PATH + "codeinspection/Duplicate_Inspection.form");
		TEMPLATES.put("UnusedAbstractInspection.java", IDE_TPL + SRC + PROJECT_PATH + "codeinspection/UnusedAbstract_Inspection.java");
		TEMPLATES.put("RemoveAttributeFix.java", IDE_TPL + SRC + PROJECT_PATH + "codeinspection/fix/RemoveAttributeFix.java");
		TEMPLATES.put("RemoveFix.java", IDE_TPL + SRC + PROJECT_PATH + "codeinspection/fix/Remove_Fix.java");

//		TEMPLATES.put("CompilerConfigurable.form", IDE_TPL + SRC+ PROJECT_PATH + "compiler/-CompilerConfigurable.form");
//		TEMPLATES.put("CompilerConfigurable.java", IDE_TPL + SRC+ PROJECT_PATH + "compiler/-CompilerConfigurable.java");
//		TEMPLATES.put("CompilerConfiguration.java", IDE_TPL + SRC+ PROJECT_PATH + "compiler/-CompilerConfiguration.java");
//		TEMPLATES.put("CompilerWorkspaceConfiguration.java", IDE_TPL + SRC+ PROJECT_PATH + "compiler/-CompilerWorkspaceConfiguration.java");

		TEMPLATES.put("LoggingEventSubmitter.java", IDE_TPL + SRC + PROJECT_PATH + "diagnostic/errorreporting/LoggingEventSubmitter.java");
		TEMPLATES.put("PluginErrorReportSubmitter.java", IDE_TPL + SRC + PROJECT_PATH + "diagnostic/errorreporting/PluginErrorReportSubmitter.java");
		TEMPLATES.put("PluginErrorReportSubmitterBundle.java", IDE_TPL + SRC + PROJECT_PATH + "diagnostic/errorreporting/PluginErrorReportSubmitterBundle.java");

		TEMPLATES.put("DocumentationProvider.java", IDE_TPL + SRC + PROJECT_PATH + "documentation/-DocumentationProvider.java");
		TEMPLATES.put("DocumentationFormatter.java", IDE_TPL + SRC + PROJECT_PATH + "documentation/-DocumentationFormatter.java");

		//TEMPLATES.put("Block.java", IDE_TPL + SRC+ PROJECT_PATH + "formatter/-Block.java");
		//TEMPLATES.put("FormattingModelBuilder.java", IDE_TPL + SRC+ PROJECT_PATH + "formatter/-FormattingModelBuilder.java");

		TEMPLATES.put("BraceMatcher.java", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-BraceMatcher.java");
		TEMPLATES.put("ColorSettingPage.java", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-ColorSettingPage.java");
		TEMPLATES.put("HighlighterLexAdapter.java", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-HighlighterLexAdapter.java");
		TEMPLATES.put("SyntaxHighlighter.java", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-SyntaxHighlighter.java");
		TEMPLATES.put("SyntaxHighlighterFactory.java", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-SyntaxHighlighterFactory.java");
		TEMPLATES.put("HighlighterLex.flex", IDE_TPL + SRC + PROJECT_PATH + "highlighting/-HighlighterLex.flex");

		TEMPLATES.put("FileType.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/file/-FileType.java");
		TEMPLATES.put("FileTypeFactory.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/file/-FileTypeFactory.java");
		TEMPLATES.put("LexerAdapter.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/lexer/-LexerAdapter.java");
		TEMPLATES.put("BlockManager.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/lexer/BlockManager.java");
		TEMPLATES.put("GeneratedParserUtilBase.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/parser/GeneratedParserUtilBase.java");
		TEMPLATES.put("ParserDefinition.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/parser/-ParserDefinition.java");
		TEMPLATES.put("ParserUtil.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/parser/-ParserUtil.java");
		TEMPLATES.put("Annotation.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/parser/Annotation.java");
		TEMPLATES.put("Icons.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/-Icons.java");
		TEMPLATES.put("Language.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/-Language.java");
		TEMPLATES.put("Attribute.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Attribute.java");
		TEMPLATES.put("File.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/-File.java");
		TEMPLATES.put("_.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/_.java");
		TEMPLATES.put("Body.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Body.java");
		TEMPLATES.put("Annotations.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Annotations.java");
		TEMPLATES.put("Attribute.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Attribute.java");
		TEMPLATES.put("Doc.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Doc.java");
		TEMPLATES.put("_Injection.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/_Injection.java");
		TEMPLATES.put("Identifier.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Identifier.java");
		TEMPLATES.put("ReferenceIdentifier.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/ReferenceIdentifier.java");
		TEMPLATES.put("ReferenceStatement.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/ReferenceStatement.java");
		TEMPLATES.put("Signature.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Signature.java");
		TEMPLATES.put("Word.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/Word.java");
		TEMPLATES.put("ElementFactory.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/-ElementFactory.java");
		TEMPLATES.put("ElementType.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/-ElementType.java");
		TEMPLATES.put("TokenType.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/-TokenType.java");
		TEMPLATES.put("AnnotationsMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/AnnotationsMixin.java");
		TEMPLATES.put("AttributeMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/AttributeMixin.java");
		TEMPLATES.put("BodyMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/BodyMixin.java");
		TEMPLATES.put("DocMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/DocMixin.java");
		TEMPLATES.put("_Mixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/_Mixin.java");
		TEMPLATES.put("_InjectionMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/_InjectionMixin.java");
		TEMPLATES.put("AttributeMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/AttributeMixin.java");
		TEMPLATES.put("IdentifierMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/IdentifierMixin.java");
		TEMPLATES.put("ReferenceIdentifierMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/ReferenceIdentifierMixin.java");
		TEMPLATES.put("ReferenceStatementMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/ReferenceStatementMixin.java");
		TEMPLATES.put("SignatureMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/SignatureMixin.java");
		TEMPLATES.put("WordMixin.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/WordMixin.java");
		TEMPLATES.put("IdentifierManipulator.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/IdentifierManipulator.java");
		TEMPLATES.put("ElementFactoryImpl.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/-ElementFactoryImpl.java");
		TEMPLATES.put("FileImpl.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/-FileImpl.java");
		TEMPLATES.put("PsiImplUtil.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/-PsiImplUtil.java");
		TEMPLATES.put("Types.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/-Types.java");
		TEMPLATES.put("Util.java", IDE_TPL + SRC + PROJECT_PATH + "metamodel/psi/impl/-Util.java");

		TEMPLATES.put("ModuleBuilder.java", IDE_TPL + SRC + PROJECT_PATH + "project/module/ModuleBuilder.java");
		TEMPLATES.put("ModuleType.java", IDE_TPL + SRC + PROJECT_PATH + "project/module/ModuleType.java");
		TEMPLATES.put("DefaultRunner.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/DefaultRunner.java");
		TEMPLATES.put("Runner.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/Runner.java");
		TEMPLATES.put("RunConfiguration.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/RunConfiguration.java");
		TEMPLATES.put("RunConfigurationType.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/RunConfigurationType.java");
		TEMPLATES.put("RunnerUtil.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/RunnerUtil.java");
		TEMPLATES.put("RunConfigurationEditor.java", IDE_TPL + SRC + PROJECT_PATH + "project/runner/RunConfigurationEditor.java");
		TEMPLATES.put("RunConfigurationEditor.form", IDE_TPL + SRC + PROJECT_PATH + "project/runner/RunConfigurationEditor.form");
		TEMPLATES.put("MergerTreeStructureProvider.java", IDE_TPL + SRC + PROJECT_PATH + "project/view/MergerTreeStructureProvider.java");
		TEMPLATES.put("_Node.java", IDE_TPL + SRC + PROJECT_PATH + "project/view/_Node.java");
		TEMPLATES.put("_TreeView.java", IDE_TPL + SRC + PROJECT_PATH + "project/view/_TreeView.java");

		TEMPLATES.put("NamesValidator.java", IDE_TPL + SRC + PROJECT_PATH + "refactoring/rename/NamesValidator.java");
		TEMPLATES.put("NameSuggestionProvider.java", IDE_TPL + SRC + PROJECT_PATH + "refactoring/NameSuggestionProvider.java");
		TEMPLATES.put("RenameHandler.java", IDE_TPL + SRC + PROJECT_PATH + "refactoring/rename/RenameHandler.java");

		TEMPLATES.put("FileStructureViewElement.java", IDE_TPL + SRC + PROJECT_PATH + "structureview/FileStructureViewElement.java");
		TEMPLATES.put("StructureViewElement.java", IDE_TPL + SRC + PROJECT_PATH + "structureview/StructureViewElement.java");
		TEMPLATES.put("StructureViewFactory.java", IDE_TPL + SRC + PROJECT_PATH + "structureview/StructureViewFactory.java");
		TEMPLATES.put("StructureViewModel.java", IDE_TPL + SRC + PROJECT_PATH + "structureview/StructureViewModel.java");

		TEMPLATES.put("Annotator.java", IDE_TPL + SRC + PROJECT_PATH + "-Annotator.java");
		TEMPLATES.put("Bundle.java", IDE_TPL + SRC + PROJECT_PATH + "-Bundle.java");
		TEMPLATES.put("Commenter.java", IDE_TPL + SRC + PROJECT_PATH + "-Commenter.java");
		TEMPLATES.put("FindUsagesProvider.java", IDE_TPL + SRC + PROJECT_PATH + "-FindUsagesProvider.java");
		TEMPLATES.put("FoldingBuilder.java", IDE_TPL + SRC + PROJECT_PATH + "-FoldingBuilder.java");
		TEMPLATES.put("LineMarkerProvider.java", IDE_TPL + SRC + PROJECT_PATH + "-LineMarkerProvider.java");
		TEMPLATES.put("RefactoringSupportProvider.java", IDE_TPL + SRC + PROJECT_PATH + "-RefactoringSupportProvider.java");
		TEMPLATES.put("Reference.java", IDE_TPL + SRC + PROJECT_PATH + "-Reference.java");
		TEMPLATES.put("ReferenceContributor.java", IDE_TPL + SRC + PROJECT_PATH + "-ReferenceContributor.java");

		TEMPLATES.put("lexer", IDE_TPL + SRC + PROJECT_PATH + "metamodel/lexer/-.flex");
		TEMPLATES.put("grammar", IDE_TPL + SRC + PROJECT_PATH + "metamodel/parser/-.bnf");

		TEMPLATES.put("plugin.xml", IDE_TPL + SRC + "META-INF/plugin.xml");

		TEMPLATES.put("errorReporter.properties", IDE_TPL + RES + "messages/errorReporter.properties");
		TEMPLATES.put("PluginErrorReportSubmitterBundle.properties", IDE_TPL + RES + "messages/PluginErrorReportSubmitterBundle.properties");
		TEMPLATES.put("Bundle.properties", IDE_TPL + RES + "messages/-Bundle.properties");
	}


	private TemplateFactory() {
	}

	public static Map<String, String> getTemplates() {
		return TEMPLATES;
	}

	public static String getTemplate(String className) {
		String template = TEMPLATES.get(className);
		if (template != null) return template;
		else throw new RuntimeException("Template not defined " + className);
	}
}