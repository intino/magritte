<?xml version="1.0" encoding="UTF-8"?>
<form xmlns="http://www.intellij.com/uidesigner/form/" version="1" bind-to-class="DuplicateDefinitionInspection.OptionsPanel">
  <grid id="ac1a1" binding="myWholePanel" layout-manager="GridLayoutManager" row-count="3" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
    <margin top="0" left="0" bottom="0" right="0"/>
    <constraints>
      <xy x="41" y="45" width="328" height="215"/>
    </constraints>
    <properties/>
    <border type="none"/>
    <children>
      <grid id="8f33e" layout-manager="GridLayoutManager" row-count="3" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
        <margin top="0" left="0" bottom="0" right="0"/>
        <constraints>
          <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="none"/>
        <children>
          <component id="7b8ec" class="javax.swing.JRadioButton" binding="myProjectScope">
            <constraints>
              <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text resource-bundle="messages/InspectionsBundle" key="duplicate.property.project.scope.option"/>
            </properties>
          </component>
          <component id="425f9" class="javax.swing.JRadioButton" binding="myFileScope">
            <constraints>
              <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text resource-bundle="messages/InspectionsBundle" key="duplicate.property.file.scope.option"/>
            </properties>
          </component>
          <component id="f42e9" class="javax.swing.JRadioButton" binding="myModuleScope">
            <constraints>
              <grid row="1" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text resource-bundle="messages/InspectionsBundle" key="duplicate.property.module.scope.option"/>
            </properties>
          </component>
        </children>
      </grid>
      <grid id="d6280" layout-manager="GridLayoutManager" row-count="1" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
        <margin top="20" left="0" bottom="0" right="0"/>
        <constraints>
          <grid row="1" column="0" row-span="1" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="none"/>
        <children>
          <component id="7c01a" class="javax.swing.JCheckBox" binding="myDuplicateKeys">
            <constraints>
              <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text resource-bundle="messages/::projectProperName::Bundle" key="options.::projectName::.definition.duplicate"/>
            </properties>
          </component>
        </children>
      </grid>
      <vspacer id="1c453">
        <constraints>
          <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="6" hsize-policy="1" anchor="0" fill="2" indent="0" use-parent-layout="false"/>
        </constraints>
      </vspacer>
    </children>
  </grid>
</form>
