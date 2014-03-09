<?xml version="1.0" encoding="UTF-8"?>
<form xmlns="http://www.intellij.com/uidesigner/form/" version="1" bind-to-class="monet.::projectName::.intellij.compiler.::projectProperName::CompilerConfigurable">
  <grid id="27dc6" binding="myMainPanel" layout-manager="GridLayoutManager" row-count="3" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
    <margin top="0" left="0" bottom="0" right="0"/>
    <constraints>
      <xy x="20" y="20" width="500" height="167"/>
    </constraints>
    <properties/>
    <border type="none"/>
    <children>
      <grid id="838f2" layout-manager="GridLayoutManager" row-count="1" column-count="3" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
        <margin top="0" left="0" bottom="0" right="0"/>
        <constraints>
          <grid row="0" column="0" row-span="1" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
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
            <clientProperties>
              <caretAspectRatio class="java.lang.Float" value="0.04"/>
            </clientProperties>
          </component>
          <hspacer id="5e141">
            <constraints>
              <grid row="0" column="2" row-span="1" col-span="1" vsize-policy="1" hsize-policy="6" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
            </constraints>
          </hspacer>
        </children>
      </grid>
      <grid id="4e3dc" binding="myExcludesPanel" layout-manager="BorderLayout" hgap="0" vgap="0">
        <constraints>
          <grid row="2" column="0" row-span="1" col-span="1" vsize-policy="7" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties/>
        <border type="etched" title="Exclude from stub generation"/>
        <children/>
      </grid>
      <component id="52aeb" class="com.intellij.ui.components.JBCheckBox" binding="myInvokeDynamicSupportCB">
        <constraints>
          <grid row="1" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
        </constraints>
        <properties>
          <text value="Invoke &amp;dynamic support"/>
        </properties>
      </component>
    </children>
  </grid>
</form>
