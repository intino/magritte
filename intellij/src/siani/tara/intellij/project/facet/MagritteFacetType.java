package siani.tara.intellij.project.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;

import javax.swing.*;


public class MagritteFacetType extends FacetType<MagritteFacet, MagritteFacetConfiguration> {
	public static final String STRING_ID = "google-app-engine";

	public MagritteFacetType() {
		super(MagritteFacet.ID, STRING_ID, "Google App Engine");
	}

	public MagritteFacetConfiguration createDefaultConfiguration() {
		return new MagritteFacetConfiguration();
	}

	public MagritteFacet createFacet(@NotNull Module module,
	                                 String name,
	                                 @NotNull MagritteFacetConfiguration configuration,
	                                 @Nullable Facet underlyingFacet) {
		return new MagritteFacet(this, module, name, configuration);
	}

	public boolean isSuitableModuleType(ModuleType moduleType) {
		return moduleType instanceof JavaModuleType;
	}

	@NotNull
	@Override
	public String getDefaultFacetName() {
		return "Magritte Engine";
	}

	@Override
	public Icon getIcon() {
		return TaraIcons.getIcon(TaraIcons.MAGRITTE);
	}
}