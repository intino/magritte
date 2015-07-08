package monet.editable;

import magritte.Set;
import magritte.wraps.Morph;

public class Editable extends Morph {

	public String label() {
		return _definition()._get("Editable+label").asString();
	}

	public Set<Permission> permissionSet() {
		return _components(Permission.class);
	}

	public Permission permission(int index) {
		return permissionSet().get(index);
	}

	public static class Permission extends Morph {
		public String[] roles() {
			return _getMultiple("roles").asString();
		}
	}


}
