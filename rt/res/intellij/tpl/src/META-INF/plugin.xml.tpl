<idea-plugin version="1">
  <id>siani.dev.::projectName::</id>
  <name>::projectProperName::</name>
  ::version::
  <vendor email="octavioroncal\@siani.es" url="http\://www.monet.org">Siani</vendor>

  ::description::

  <change-notes><![CDATA[ First Realease. Windows integration. ]]>
  </change-notes>

  <idea-version since-build="135.375"/>

  <!-- please see http\://confluence.jetbrains.net/display/IDEADEV/Plugin+Compatibility+with+IntelliJ+Platform+Products
       on how to target different products -->
  <!-- uncomment to enable plugin in all products
  <depends>com.intellij.modules.lang</depends> -->

  <actions>
    <action id="NewDefinition" class="monet.::projectName::.intellij.actions.Create::projectProperName::FileAction">
      <add-to-group group-id="NewGroup" anchor="first"/>
    </action>
    
    
  </actions>

  <application-components>
  </application-components>

  <extensions defaultExtensionNs="com.intellij">
    <fileDocumentManagerListener implementation="monet.::projectName::.intellij.codegeneration.::projectProperName::FileDocumentManagerListener"/>
    <errorHandler implementation="monet.::projectName::.intellij.diagnostic.errorreporting.PluginErrorReportSubmitter"/>
    <lang.namesValidator language="::projectProperName::"
                         implementationClass="monet.::projectName::.intellij.refactoring.rename.NamesValidator"/>
    <projectService serviceInterface="monet.::projectName::.intellij.lang.psi.::projectProperName::ElementFactory"
                    serviceImplementation="monet.::projectName::.intellij.lang.psi.impl.::projectProperName::ElementFactoryImpl"/>
    <moduleType id="::projectUpperName::_MODULE" implementationClass="monet.::projectName::.intellij.project.module.ModuleType"/>
    <treeStructureProvider implementation="monet.::projectName::.intellij.project.view.MergerTreeStructureProvider"/>
    <fileTemplateGroup implementation="monet.::projectName::.intellij.actions.::projectProperName::TemplatesFactory"/>
    <codeInsight.lineMarkerProvider language="::projectProperName::"
                                    implementationClass="monet.::projectName::.intellij.codeinsight.::projectProperName::LineMarkerProvider"/>
    <gotoRelatedProvider implementation="monet.::projectName::.intellij.codeinsight.::projectProperName::RelatedFilesProvider"/>
    

    <configurationType implementation="monet.::projectName::.intellij.project.runner.RunConfigurationType"/>
    <lang.commenter language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::Commenter"/>
    <lang.documentationProvider language="::projectProperName::"
                                implementationClass="monet.::projectName::.intellij.documentation.::projectProperName::DocumentationProvider"/>
    <fileTypeFactory implementation="monet.::projectName::.intellij.lang.file.::projectProperName::FileTypeFactory"/>
    <lang.parserDefinition language="::projectProperName::"
                           implementationClass="monet.::projectName::.intellij.lang.parser.::projectProperName::ParserDefinition"/>
    <lang.syntaxHighlighterFactory key="::projectProperName::"
                                   implementationClass="monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighterFactory"/>
    <colorSettingsPage implementation="monet.::projectName::.intellij.highlighting.::projectProperName::ColorSettingPage"/>
    <lang.braceMatcher language="::projectProperName::" implementationClass="monet.::projectName::.intellij.highlighting.::projectProperName::BraceMatcher"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.ReferenceAnnotator"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.AnnotationsAnnotator"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.AttributeAnnotator"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.DefinitionAnnotator"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.PackageAnnotator"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.annotator.ParameterAnnotator"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeinsight.completion.::projectProperName::SignatureCompletionContributor"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeinsight.completion.::projectProperName::PrimitivesCompletionContributor"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeinsight.completion.::projectProperName::AnnotationsCompletionContributor"/>
    <lang.psiStructureViewFactory language="::projectProperName::"
                                  implementationClass="monet.::projectName::.intellij.structureview.StructureViewFactory"/>
    <!--<psi.referenceContributor implementation="monet.::projectName::.intellij.lang.psi.resolve.::projectProperName::ReferenceContributor"/>-->
    <nameSuggestionProvider implementation="monet.::projectName::.intellij.refactoring.NameSuggestionProvider"/>
    <lang.elementManipulator forClass="monet.::projectName::.intellij.lang.psi.::projectProperName::Identifier"
                             implementationClass="monet.::projectName::.intellij.lang.psi.impl.IdentifierManipulator"/>
    <codeInsight.parameterInfo language="::projectProperName::" implementationClass="monet.::projectName::.intellij.codeinsight.parameterinfo.::projectProperName::ParameterInfoHandler"/>

    <codeInsight.parameterInfo language="::projectProperName::"
                               implementationClass="monet.::projectName::.intellij.codeinsight.parameterinfo.::projectProperName::ParameterInfoHandler"/>

    <lang.refactoringSupport language="::projectProperName::"
                             implementationClass="monet.::projectName::.intellij.::projectProperName::RefactoringSupportProvider"/>
    <renameHandler implementation="monet.::projectName::.intellij.refactoring.rename.RenameHandler"/>
    <lang.findUsagesProvider language="::projectProperName::"
                             implementationClass="monet.::projectName::.intellij.lang.psi.resolve.::projectProperName::FindUsagesProvider"/>
    <lang.foldingBuilder language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::FoldingBuilder"/>
    <projectService serviceInterface="monet.::projectName::.intellij.codeinsight.JavaHelper"
                    serviceImplementation="monet.::projectName::.intellij.codeinsight.JavaHelper$Impl"/>
    <!--<referenceImporter implementation="monet.::projectName::.intellij.annotator.imports.::projectProperName::ReferenceImporter"/>-->
    <!--<lang.formatter language="::projectProperName::" implementationClass="monet.::projectName::.intellij.formatter.::projectProperName::FormattingModelBuilder"/>-->
    <moveFileHandler implementation="monet.::projectName::.intellij.refactoring.move.::projectProperName::FileMoveHandler"/>
    <refactoring.moveHandler implementation="monet.::projectName::.intellij.refactoring.move.::projectProperName::MoveDefinition"/>
    <lang.importOptimizer language="::projectProperName::"
                          implementationClass="monet.::projectName::.intellij.codeinsight.imports.::projectProperName::ImportOptimizer"/>
    <findUsagesHandlerFactory implementation="monet.::projectName::.intellij.findUsages.::projectProperName::FindUsagesHandlerFactory" id="::projectProperName::"
                              order="last, before default"/>
  </extensions>
</idea-plugin>