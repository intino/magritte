package siani.tara.intellij.project.facet;

import com.intellij.facet.*;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFacet extends Facet<TaraFacetConfiguration> {
	public static final FacetTypeId<TaraFacet> ID = new FacetTypeId<>("tara");

	public TaraFacet(@NotNull FacetType facetType,
	                 @NotNull Module module,
	                 @NotNull String name,
	                 @NotNull TaraFacetConfiguration configuration) {
		super(facetType, module, name, configuration, null);
	}

	public static FacetType<TaraFacet, TaraFacetConfiguration> getFacetType() {
		return FacetTypeRegistry.getInstance().findFacetType(ID);
	}

	@Nullable
	public static TaraFacet getTaraFacetByModule(@Nullable Module module) {
		if (module == null) return null;
		return FacetManager.getInstance(module).getFacetByType(ID);
	}

}
