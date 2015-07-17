package tara.intellij.project.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;

import javax.swing.*;

public class TaraFacetType extends FacetType<TaraFacet, TaraFacetConfiguration> {

	public static final String STRING_ID = "Tara";

	public TaraFacetType() {
		super(TaraFacet.ID, STRING_ID, "Tara");
	}

	public TaraFacetConfiguration createDefaultConfiguration() {
		return new TaraFacetConfiguration();
	}

	public TaraFacet createFacet(@NotNull Module module,
	                             String name,
	                             @NotNull TaraFacetConfiguration configuration,
	                             @Nullable Facet underlyingFacet) {
		return new TaraFacet(this, module, name, configuration);
	}

	public boolean isSuitableModuleType(ModuleType moduleType) {
		return moduleType instanceof JavaModuleType;
	}

	@NotNull
	@Override
	public String getDefaultFacetName() {
		return "Tara";
	}

	@Override
	public Icon getIcon() {
		return TaraIcons.ICON_13;
	}
}
