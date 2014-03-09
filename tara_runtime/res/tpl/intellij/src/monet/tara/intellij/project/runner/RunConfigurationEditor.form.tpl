<?xml version="1.0" encoding="UTF-8"?>
<form xmlns="http://www.intellij.com/uidesigner/form/" version="1" bind-to-class="monet.::projectName::.intellij.project.runner.RunConfigurationEditor">
  <grid id="27dc6" binding="myMainPanel" layout-manager="GridLayoutManager" row-count="6" column-count="2" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
    <margin top="0" left="0" bottom="0" right="0"/>
    <constraints>
      <xy x="20" y="20" width="500" height="329"/>
    </constraints>
    <properties/>
    <border type="none"/>
    <children>
      <vspacer id="41261">
        <constraints>
          <grid row="5" column="1" row-span="1" col-span="1" vsize-policy="6" hsize-policy="1" anchor="0" fill="2" indent="0" use-parent-layout="false"/>
        </constraints>
      </vspacer>
      <component id="3b65f" class="com.intellij.ui.RawCommandLineEditor" binding="deployParams">
        <constraints>
          <grid row="1" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="7" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
      </component>
      <grid id="4852" binding="workDirPanel" layout-manager="BorderLayout" hgap="0" vgap="0">
        <constraints>
          <grid row="3" column="1" row-span="1" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="none"/>
        <children/>
      </grid>
      <component id="5f62b" class="com.intellij.ui.components.JBLabel" binding="myScriptParametersLabel">
        <constraints>
          <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Deploy URL:"/>
        </properties>
      </component>
      <component id="c5c05" class="javax.swing.JLabel">
        <constraints>
          <grid row="1" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Deploy params:"/>
        </properties>
      </component>
      <component id="86e25" class="javax.swing.JLabel">
        <constraints>
          <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <labelFor value="d1e7d"/>
          <text value="Module:"/>
        </properties>
      </component>
      <component id="d1e7d" class="javax.swing.JComboBox" binding="myModulesBox">
        <constraints>
          <grid row="0" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="2" anchor="8" fill="1" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
      </component>
      <component id="ae9d1" class="javax.swing.JLabel">
        <constraints>
          <grid row="3" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Working directory:"/>
        </properties>
      </component>
      <component id="c934c" class="javax.swing.JCheckBox" binding="myDebugCB" default-binding="true">
        <constraints>
          <grid row="4" column="0" row-span="1" col-span="2" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <selected value="false"/>
          <text value="Debug option"/>
        </properties>
      </component>
      <component id="98cc" class="javax.swing.JTextField" binding="deployUrlField">
        <constraints>
          <grid row="2" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
            <preferred-size width="150" height="-1"/>
          </grid>
        </constraints>
        <properties/>
      </component>
    </children>
  </grid>
</form>
