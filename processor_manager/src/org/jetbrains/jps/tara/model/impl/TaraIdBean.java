package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraIdBean {

	@Tag("groupId")
	public String groupId;

	@Tag("artifactId")
	public String artifactId;

	@Tag("version")
	public String version;

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
		return !checkArtifact(bean) && !checkGroup(bean) && !checkVersion(bean);
	}

	private boolean checkVersion(TaraIdBean bean) {
		return version != null ? !version.equals(bean.version) : bean.version != null;
	}

	private boolean checkGroup(TaraIdBean bean) {
		return groupId != null ? !groupId.equals(bean.groupId) : bean.groupId != null;
	}

	private boolean checkArtifact(TaraIdBean bean) {
		return artifactId != null ? !artifactId.equals(bean.artifactId) : bean.artifactId != null;
	}

	@Override
	public int hashCode() {
		int result = groupId != null ? groupId.hashCode() : 0;
		result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		return result;
	}
}
