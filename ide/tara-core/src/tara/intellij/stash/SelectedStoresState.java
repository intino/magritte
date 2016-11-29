package tara.intellij.stash;

import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Tag;

import java.util.ArrayList;
import java.util.List;

public class SelectedStoresState {

	private List<String> stores = new ArrayList<>();

	@Tag("stores")
	@AbstractCollection(surroundWithTag = false, elementTag = "stores", elementValueAttribute = "id")
	public List<String> getStores() {
		return stores;
	}

	public void setStores(List<String> stores) {
		this.stores = stores;
	}
}
