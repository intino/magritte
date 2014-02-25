<idea-plugin version="1">
  <id>siani.dev</id>
  <name>::projectProperName:: Plugin</name>
  <version>1.0</version>
  <vendor email="oroncal\@siani.es" url="http://www.monet.org">YourCompany</vendor>

  <description><![CDATA[
      Enter short description for your plugin here.<br>
      <small>most HTML tags may be used</small>
      ]]></description>

  <change-notes><![CDATA[
      Add change notes here.<br>
      <small>most HTML tags may be used</small>
      ]]>
  </change-notes>

  <idea-version since-build="107.105"/>

  <actions>
    <action id="NewMetamodelUnit" class="monet.::projectName::.intellij.actions.Create::projectProperName::FileAction">
      <add-to-group group-id="NewGroup" anchor="before" relative-to-action="NewGroup1"/>
    </action>
  </actions>

  <application-components>
  </application-components>

  <project-components>
    <component>
      <implementation-class>monet.::projectName::.intellij.compiler.::projectProperName::CompilerLoader</implementation-class>
    </component>
  </project-components>

  <extensions defaultExtensionNs="com.intellij">
    <lang.namesValidator language="Python"
                         implementationClass="monet.::projectName::.intellij.refactoring.rename.::projectProperName::NamesValidator"/>
    <projectService serviceInterface="monet.::projectName::.intellij.psi.::projectProperName::ElementFactory"
                    serviceImplementation="monet.::projectName::.intellij.psi.impl.::projectProperName::ElementFactoryImpl"/>
    <internalFileTemplate name="Concept"/>
    <moduleType id="::projectProperName::_MODULE" implementationClass="monet.::projectName::.intellij.project.module.::projectProperName::ModuleType"/>

    <projectConfigurable instance="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfigurable" id="::projectProperName:: compiler"
                         displayName="::projectProperName:: Compiler" parentId="project.propCompiler"/>

    <compileServer.plugin classpath="::projectName::-jps-plugin.jar"/>
    <projectService serviceInterface="monet.::projectName::.intellij.compiler.::projectProperName::CompilerWorkspaceConfiguration"
                    serviceImplementation="monet.::projectName::.intellij.compiler.::projectProperName::CompilerWorkspaceConfiguration"/>
    <projectService serviceInterface="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfiguration"
                    serviceImplementation="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfiguration"/>
    <configurationType implementation="monet.::projectName::.intellij.project.runner.::projectProperName::RunConfigurationType"/>
    <lang.commenter language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::Commenter"/>

    <fileTemplateGroup implementation="monet.::projectName::.intellij.project.::projectProperName::TemplatesFactory"/>
    <fileTypeFactory implementation="monet.::projectName::.intellij.metamodel.file.::projectProperName::FileTypeFactory"/>
    <lang.parserDefinition language="::projectProperName::" implementationClass="monet.::projectName::.intellij.metamodel.::projectProperName::ParserDefinition"/>
    <lang.syntaxHighlighterFactory key="::projectProperName::"
                                   implementationClass="monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighterFactory"/>
    <colorSettingsPage implementation="monet.::projectName::.intellij.highlighting.::projectProperName::ColorSettingPage"/>
    <!--<annotator language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::Annotator"/>-->
    <!--<localInspection language="::projectProperName::" shortName="UnusedAbstractDefinition" bundle="messages.::projectProperName::Bundle"-->
    <!--key="unused.abstract.definition.inspection.display.name"-->
    <!--groupKey="::projectName::.files.inspection.group.display.name" enabledByDefault="true" level="WARNING"-->
    <!--implementationClass="monet.::projectName::.intellij.metamodel.codeinspection.UnusedAbstractDefinitionInspection"/>-->
    <!--<globalInspection shortName="DuplicateDefinitionInspection" bundle="messages.::projectProperName::Bundle"-->
                      <!--key="duplicate.definition.display.name"-->
                      <!--groupKey="group.names.::projectName::.files" enabledByDefault="false" level="WARNING"-->
                      <!--implementationClass="monet.::projectName::.intellij.codeinspection.DuplicateDefinitionInspection"/>-->
    <completion.contributor language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::CompletionContributor"/>
    <psi.referenceContributor language="::projectProperName::" implementation="monet.::projectName::.intellij.::projectProperName::ReferenceContributor"/>
    <nameSuggestionProvider implementation="monet.::projectName::.intellij.refactoring.::projectProperName::NameSuggestionProvider"/>
    <lang.elementManipulator forClass="monet.::projectName::.intellij.psi.::projectProperName::Identifier"
                             implementationClass="monet.::projectName::.intellij.psi.impl.::projectProperName::DefinitionIdentifierManipulator"/>
    <lang.refactoringSupport language="::projectProperName::"
                             implementationClass="monet.::projectName::.intellij.::projectProperName::RefactoringSupportProvider"/>
    <!--<renameHandler implementation="::projectProperName::RenameHandler"/>-->
    <lang.findUsagesProvider language="::projectProperName::" implementationClass="monet.::projectName::.intellij.metamodel.::projectProperName::FindUsagesProvider"/>
    <lang.foldingBuilder language="::projectProperName::" implementationClass="monet.::projectName::.intellij.::projectProperName::FoldingBuilder"/>
    <!--<lang.psiStructureViewFactory language="::projectProperName::" implementationClass="::projectProperName::StructureViewFactory"/>-->
    <!--<lang.formatter language="::projectProperName::" implementationClass="::projectProperName::FormattingModelBuilder"/>-->
  </extensions>
</idea-plugin>