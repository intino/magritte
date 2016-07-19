package tara.intellij.project.facet;

public class ModuleInfo {

	private final TaraFacetConfiguration.ModuleType type;
	private String platformOutDsl;
	private String applicationOutDsl;

	public ModuleInfo(TaraFacetConfiguration.ModuleType type, String platformOutDsl, String applicationOutDsl) {
		this.type = type;
		this.platformOutDsl = platformOutDsl;
		this.applicationOutDsl = applicationOutDsl;
	}

	public TaraFacetConfiguration.ModuleType type() {
		return type;
	}

	public String applicationDsl() {
		return platformOutDsl;
	}

	public String getApplicationOutDsl() {
		return applicationOutDsl;
	}
}
