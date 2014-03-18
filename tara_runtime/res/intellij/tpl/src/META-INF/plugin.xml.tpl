<idea-plugin version="1">
  <id>siani.dev.::projectName::</id>
  <name>::projectProperName:: Plugin</name>
  <version>1.0</version>
  <vendor email="octavioroncal\@siani.es" url="http\://www.monet.org">Siani</vendor>

  <description><![CDATA[
      Enter short description for your plugin here.<br>
      <small>most HTML tags may be used</small>
      ]]></description>

  <change-notes><![CDATA[
      Add change notes here.<br>
      <small>most HTML tags may be used</small>
      ]]>
  </change-notes>

  <!-- please see http\://confluence.jetbrains.net/display/IDEADEV/Build+Number+Ranges for description -->
  <idea-version since-build="133.331"/>

  <!-- please see http\://confluence.jetbrains.net/display/IDEADEV/Plugin+Compatibility+with+IntelliJ+Platform+Products
       on how to target different products -->
  <!-- uncomment to enable plugin in all products
  <depends>com.intellij.modules.lang</depends>
  -->

  <actions>
    <!--<action id="BashErrorReporter.TriggerException"-->
            <!--class="monet.::projectName::.intellij.actions.TriggerExceptionAction"-->
            <!--text="Trigger Exception"-->
            <!--description="Triggers an exception">-->
      <!--<add-to-group group-id="ToolsMenu" anchor="last"/>-->
    <!--</action>-->
    <action id="NewDefinition" class="monet.::projectName::.intellij.actions.CreateFileAction">
      <add-to-group group-id="NewGroup" anchor="first"/>
    </action>
    <action id="NewPackage" class="monet.::projectName::.intellij.actions.CreatePackageAction" text="Package"
            description="Create a new package" icon="AllIcons.Nodes.Package">
    </action>
  </actions>

  <application-components>
  </application-components>

  <extensions defaultExtensionNs="com.intellij">
    <errorHandler implementation="monet.::projectName::.intellij.diagnostic.errorreporting.PluginErrorReportSubmitter"/>
    <lang.namesValidator language="::projectProperName::"
                         implementationClass="monet.::projectName::.intellij.refactoring.rename.NamesValidator"/>
    <projectService serviceInterface="monet.::projectName::.intellij.metamodel.psi.::projectProperName::ElementFactory"
                    serviceImplementation="monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::ElementFactoryImpl"/>
    <moduleType id="::projectUpperName::_MODULE" implementationClass="monet.::projectName::.intellij.project.module.ModuleType"/>
    <treeStructureProvider implementation="monet.::projectName::.intellij.project.view.MergerTreeStructureProvider"/>

    <!--compiler-->
    <compileServer.plugin classpath="::projectName::-jps-plugin.jar"/>
    <projectConfigurable instance="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfigurable" id="::projectProperName:: compiler"
                         displayName="::projectProperName:: Compiler" parentId="project.propCompiler"/>

    <projectService serviceInterface="monet.::projectName::.intellij.compiler.::projectProperName::CompilerWorkspaceConfiguration"
                    serviceImplementation="monet.::projectName::.intellij.compiler.::projectProperName::CompilerWorkspaceConfiguration"/>
    <projectService serviceInterface="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfiguration"
                    serviceImplementation="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfiguration"/>
    <!--end_compiler-->

    <configurationType implementation="monet.::projectName::.intellij.project.runner.RunConfigurationType"/>
    <lang.commenter language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::Commenter"/>
    <lang.documentationProvider language="::projectProperName::"
                                implementationClass="monet.::projectName::.intellij.documentation.::projectProperName::DocumentationProvider"/>
    <fileTypeFactory implementation="monet.::projectName::.intellij.metamodel.file.::projectProperName::FileTypeFactory"/>
    <lang.parserDefinition language="::projectProperName::"
                           implementationClass="monet.::projectName::.intellij.metamodel.parser.::projectProperName::ParserDefinition"/>
    <lang.syntaxHighlighterFactory key="::projectProperName::"
                                   implementationClass="monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighterFactory"/>
    <colorSettingsPage implementation="monet.::projectName::.intellij.highlighting.::projectProperName::ColorSettingPage"/>
    <lang.braceMatcher language="::projectProperName::" implementationClass="monet.::projectName::.intellij.highlighting.::projectProperName::BraceMatcher"/>
    <annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::Annotator"/>
    <globalInspection language="::projectProperName::" shortName="UnusedDefinition" bundle="messages.::projectProperName::Bundle"
                      key="unused.definition.inspection.display.name"
                      groupKey="::projectName::.files.inspection.group.display.name" enabledByDefault="true" level="WARNING"
                      implementationClass="monet.::projectName::.intellij.codeinspection.UnusedAbstractDefinitionInspection"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeInsight.completion.::projectProperName::SignatureCompletionContributor"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeInsight.completion.::projectProperName::PrimitivesCompletionContributor"/>
    <completion.contributor language="::projectProperName::"
                            implementationClass="monet.::projectName::.intellij.codeInsight.completion.::projectProperName::AnnotationsCompletionContributor"/>
    <lang.psiStructureViewFactory language="::projectProperName::"
                                  implementationClass="monet.::projectName::.intellij.structureview.StructureViewFactory"/>
    <psi.referenceContributor language="JAVA" implementation="monet.::projectName::.intellij.::projectProperName::ReferenceContributor"/>
    <nameSuggestionProvider implementation="monet.::projectName::.intellij.refactoring.NameSuggestionProvider"/>
    <lang.elementManipulator forClass="monet.::projectName::.intellij.metamodel.psi.::projectProperName::Identifier"
                             implementationClass="monet.::projectName::.intellij.metamodel.psi.impl.IdentifierManipulator"/>
    <lang.refactoringSupport language="::projectProperName::"
                             implementationClass="monet.::projectName::.intellij.::projectProperName::RefactoringSupportProvider"/>
    <renameHandler implementation="monet.::projectName::.intellij.refactoring.rename.RenameHandler"/>
    <lang.findUsagesProvider language="::projectProperName::"
                             implementationClass="monet.::projectName::.intellij.::projectProperName::FindUsagesProvider"/>
    <lang.foldingBuilder language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::FoldingBuilder"/>
    <!--<lang.formatter language="::projectProperName::" implementationClass="monet.::projectName::.intellij.formatter.::projectProperName::FormattingModelBuilder"/>-->
    <!--<refactoring.moveHandler implementation="com.intellij.uiDesigner.projectView.FormMoveProvider"/>-->

  </extensions>
</idea-plugin>