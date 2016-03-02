package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

public class JpsTaraModuleExtensionImpl extends JpsElementBase<JpsTaraModuleExtensionImpl> implements JpsTaraModuleExtension {

	public static final JpsElementChildRole<JpsTaraModuleExtension> ROLE = JpsElementChildRoleBase.create("Tara");
	private TaraModuleExtensionProperties myProperties;

	public JpsTaraModuleExtensionImpl(TaraModuleExtensionProperties properties) {
		myProperties = properties;
	}

	private JpsTaraModuleExtensionImpl(JpsTaraModuleExtensionImpl original) {
		myProperties = XmlSerializerUtil.createCopy(original.myProperties);
	}

	public TaraModuleExtensionProperties getProperties() {
		return myProperties;
	}

	@Override
	public JpsModule getModule() {
		return (JpsModule) getParent();
	}

	@Override
	public String dsl() {
		return myProperties.dsl;
	}

	@Override
	public String generatedDsl() {
		return myProperties.generatedDslName;
	}


	@Override
	public boolean isDynamicLoad() {
		return myProperties.dynamicLoad;
	}

	@Override
	public boolean customLayers() {
		return myProperties.customLayers;
	}

	@Override
	public int level() {
		return myProperties.level;
	}

	@Override
	public boolean testModule() {
		return myProperties.testModule;
	}

	@Override
	public boolean ontology() {
		return myProperties.ontology;
	}

	public int domainRefactorId() {
		return myProperties.domainRefactorId;
	}

	public int engineRefactorId() {
		return myProperties.engineRefactorId;
	}

	@NotNull
	@Override
	public JpsTaraModuleExtensionImpl createCopy() {
		return new JpsTaraModuleExtensionImpl(this);
	}

	@Override
	public void applyChanges(@NotNull JpsTaraModuleExtensionImpl modified) {
		XmlSerializerUtil.copyBean(modified.myProperties, myProperties);
	}

}
