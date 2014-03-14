<?xml version="1.0" encoding="UTF-8"?>
<form xmlns="http://www.intellij.com/uidesigner/form/" version="1" bind-to-class="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfigurable">
  <grid id="27dc6" binding="myMainPanel" layout-manager="GridLayoutManager" row-count="6" column-count="8" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
    <margin top="0" left="0" bottom="0" right="0"/>
    <constraints>
      <xy x="20" y="20" width="590" height="339"/>
    </constraints>
    <properties/>
    <border type="none"/>
    <children>
      <grid id="838f2" layout-manager="GridLayoutManager" row-count="1" column-count="3" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
        <margin top="0" left="0" bottom="0" right="0"/>
        <constraints>
          <grid row="0" column="0" row-span="1" col-span="8" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="none"/>
        <children>
          <component id="209ef" class="javax.swing.JLabel">
            <constraints>
              <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
            </constraints>
            <properties>
              <text resource-bundle="messages/CompilerBundle" key="javac.option.max.heap.size"/>
            </properties>
          </component>
          <component id="22ced" class="javax.swing.JTextField" binding="myHeapSize">
            <constraints>
              <grid row="0" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
                <preferred-size width="150" height="-1"/>
              </grid>
            </constraints>
            <properties/>
          </component>
          <hspacer id="5e141">
            <constraints>
              <grid row="0" column="2" row-span="1" col-span="1" vsize-policy="1" hsize-policy="6" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
            </constraints>
          </hspacer>
        </children>
      </grid>
      <component id="c0f42" class="javax.swing.JLabel">
        <constraints>
          <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Version"/>
        </properties>
      </component>
      <component id="7048b" class="javax.swing.JLabel">
        <constraints>
          <grid row="3" column="0" row-span="1" col-span="3" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Comentarios de la version"/>
        </properties>
      </component>
      <component id="8260f" class="javax.swing.JTextArea" binding="commentaries">
        <constraints>
          <grid row="4" column="0" row-span="1" col-span="8" vsize-policy="6" hsize-policy="6" anchor="0" fill="3" indent="0" use-parent-layout="false">
            <preferred-size width="150" height="50"/>
          </grid>
        </constraints>
        <properties>
          <text value=""/>
        </properties>
      </component>
      <component id="3111c" class="javax.swing.JCheckBox" binding="pluginGenerationCheckBox" default-binding="true">
        <constraints>
          <grid row="1" column="0" row-span="1" col-span="6" vsize-policy="0" hsize-policy="3" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <selected value="true"/>
          <text value="Plugin generation"/>
        </properties>
      </component>
      <component id="9c00d" class="javax.swing.JTextField" binding="version">
        <constraints>
          <grid row="2" column="1" row-span="1" col-span="2" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
            <preferred-size width="150" height="-1"/>
          </grid>
        </constraints>
        <properties/>
      </component>
      <component id="64f0c" class="javax.swing.JTextField" binding="textField2" default-binding="true">
        <constraints>
          <grid row="2" column="6" row-span="1" col-span="2" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
            <preferred-size width="150" height="-1"/>
          </grid>
        </constraints>
        <properties/>
      </component>
      <component id="c04e7" class="javax.swing.JLabel">
        <constraints>
          <grid row="5" column="0" row-span="1" col-span="2" vsize-policy="0" hsize-policy="0" anchor="9" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Excluded Files"/>
        </properties>
      </component>
      <component id="7db02" class="javax.swing.JList" binding="excludedStubs">
        <constraints>
          <grid row="5" column="2" row-span="1" col-span="6" vsize-policy="6" hsize-policy="2" anchor="0" fill="3" indent="0" use-parent-layout="false">
            <preferred-size width="150" height="50"/>
          </grid>
        </constraints>
        <properties/>
      </component>
      <component id="91c1b" class="javax.swing.JLabel">
        <constraints>
          <grid row="2" column="4" row-span="1" col-span="2" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Vendor"/>
        </properties>
      </component>
    </children>
  </grid>
</form>
