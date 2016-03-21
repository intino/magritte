package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.tara.model.JpsTaraFacet;

public class JpsTaraFacetImpl extends JpsElementBase<JpsTaraFacetImpl> implements JpsTaraFacet {

	public static final JpsElementChildRole<JpsTaraFacet> ROLE = JpsElementChildRoleBase.create("Tara");
	private TaraModuleExtensionProperties myProperties;

	public JpsTaraFacetImpl(TaraModuleExtensionProperties properties) {
		myProperties = properties;
	}

	private JpsTaraFacetImpl(JpsTaraFacetImpl original) {
		myProperties = XmlSerializerUtil.createCopy(original.myProperties);
	}

	public TaraModuleExtensionProperties getProperties() {
		return myProperties;
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
	public int level() {
		return myProperties.level;
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
	public JpsTaraFacetImpl createCopy() {
		return new JpsTaraFacetImpl(this);
	}

	@Override
	public void applyChanges(@NotNull JpsTaraFacetImpl modified) {
		XmlSerializerUtil.copyBean(modified.myProperties, myProperties);
	}

}
