package tara.intellij.project.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.framework.detection.FacetBasedFrameworkDetector;
import com.intellij.framework.detection.FileContentPattern;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.patterns.ElementPattern;
import com.intellij.util.indexing.FileContent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.file.TaraFileType;

import javax.swing.*;


public class TaraFacetType extends FacetType<TaraFacet, TaraFacetConfiguration> {

	private static final String STRING_ID = "Tara";

	public TaraFacetType() {
		super(TaraFacet.ID, STRING_ID, "Tara");
	}

	public static FacetType<TaraFacet, TaraFacetConfiguration> getInstance() {
		return TaraFacetType.findInstance(TaraFacetType.class);
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
		return TaraIcons.ICON_16;
	}

	public static class TaraFrameworkDetector extends FacetBasedFrameworkDetector<TaraFacet, TaraFacetConfiguration> {
		public TaraFrameworkDetector() {
			super("tara");
		}

		@Override
		public FacetType<TaraFacet, TaraFacetConfiguration> getFacetType() {
			return getInstance();
		}

		@NotNull
		@Override
		public FileType getFileType() {
			return TaraFileType.instance();
		}

		@NotNull
		@Override
		public ElementPattern<FileContent> createSuitableFilePattern() {
			return FileContentPattern.fileContent();
		}
	}
}
