package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public interface Parameter extends tara.language.model.Parameter, Navigatable, TaraPsiElement {


	boolean isExplicit();

	Value getValue();

	TaraMeasureValue getMetric();

	String getValueType();

	default NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	default String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}
}
