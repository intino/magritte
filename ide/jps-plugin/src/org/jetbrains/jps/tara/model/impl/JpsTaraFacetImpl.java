package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.tara.model.JpsTaraFacet;

public class JpsTaraFacetImpl extends JpsElementBase<JpsTaraFacetImpl> implements JpsTaraFacet {

	static final JpsElementChildRole<JpsTaraFacet> ROLE = JpsElementChildRoleBase.create("Tara");
	private TaraModuleExtensionProperties properties;

	JpsTaraFacetImpl(TaraModuleExtensionProperties properties) {
		this.properties = properties;
	}

	private JpsTaraFacetImpl(JpsTaraFacetImpl original) {
		properties = XmlSerializerUtil.createCopy(original.properties);
	}

	TaraModuleExtensionProperties getProperties() {
		return properties;
	}

	@Override
	public String platformDsl() {
		return properties.platformDsl;
	}

	@Override
	public String applicationDsl() {
		return properties.applicationDsl;
	}

	@Override
	public String systemDsl() {
		return properties.systemDsl;
	}

	public String platformOutDsl() {
		return properties.platformOutDsl;
	}

	public String applicationOutDsl() {
		return properties.applicationOutDsl;
	}

	@Override
	public boolean isLazyLoad() {
		return properties.lazyLoad;
	}

	public boolean isPersistent() {
		return properties.persistent;
	}

	@Override
	public String type() {
		return properties.type;
	}

	public int applicationRefactorId() {
		return properties.applicationRefactorId;
	}

	public int platformRefactorId() {
		return properties.applicationRefactorId;
	}

	@NotNull
	@Override
	public JpsTaraFacetImpl createCopy() {
		return new JpsTaraFacetImpl(this);
	}

	@Override
	public void applyChanges(@NotNull JpsTaraFacetImpl modified) {
		XmlSerializerUtil.copyBean(modified.properties, properties);
	}

}
