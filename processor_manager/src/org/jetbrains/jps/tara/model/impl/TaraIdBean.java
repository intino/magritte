package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraIdBean {

	@Tag("groupId")
	public String groupId;

	@Tag("artifactId")
	public String artifactId;

	@Tag("version")
	public String version;

	public TaraIdBean() {
	}

	public TaraIdBean(String groupId, String artifactId, String version) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TaraIdBean bean = (TaraIdBean) o;

		if (artifactId != null ? !artifactId.equals(bean.artifactId) : bean.artifactId != null) return false;
		if (groupId != null ? !groupId.equals(bean.groupId) : bean.groupId != null) return false;
		if (version != null ? !version.equals(bean.version) : bean.version != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = groupId != null ? groupId.hashCode() : 0;
		result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		return result;
	}
}
