<?xml version="1.0" encoding="UTF-8"?>
<form xmlns="http\://www.intellij.com/uidesigner/form/" version="1" bind-to-class="monet.::projectName::.intellij.refactoring.move.::projectProperName::MoveDefinitionPanel.java">
  <grid id="27dc6" binding="myPanel" layout-manager="GridLayoutManager" row-count="4" column-count="1" same-size-horizontally="true" same-size-vertically="false" hgap="-1" vgap="10">
    <margin top="0" left="0" bottom="0" right="0"/>
    <constraints>
      <xy x="20" y="20" width="543" height="467"/>
    </constraints>
    <properties>
      <preferredSize width="320" height="160"/>
    </properties>
    <border type="none"/>
    <children>
      <grid id="38299" layout-manager="GridLayoutManager" row-count="3" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
        <margin top="5" left="5" bottom="5" right="5"/>
        <constraints>
          <grid row="0" column="0" row-span="4" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="etched"/>
        <children>
          <vspacer id="f71eb">
            <constraints>
              <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="6" hsize-policy="1" anchor="0" fill="2" indent="0" use-parent-layout="false"/>
            </constraints>
          </vspacer>
          <component id="e9a03" class="javax.swing.JLabel" binding="myElementsToMove">
            <constraints>
              <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text value="Move"/>
            </properties>
          </component>
          <component id="2a1d8" class="com.intellij.openapi.ui.TextFieldWithBrowseButton" binding="myBrowseTargetFileButton">
            <constraints>
              <grid row="1" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties/>
          </component>
        </children>
      </grid>
    </children>
  </grid>
</form>