package happysense;

import java.net.URL;
import teseo.restful.core.Resource;
import teseo.restful.core.RestfulAccessor;
import teseo.restful.exceptions.RestfulFailure;
import com.google.gson.Gson;
import teseo.exceptions.*;
import happysense.schemas.*;

public class RestAccessor {

	private URL url;
	private RestfulAccessor accessor = new RestfulAccessor();

	public RestAccessor(URL url) {
		this.url = url;
	}

	public CategorizationCatalogResponseSchema categorizations() throws ErrorUnknown {
		try {

			return new Gson().fromJson(accessor.get(this.url, "dashboard/categorizations").content(), CategorizationCatalogResponseSchema.class);
		} catch (RestfulFailure e) {
			throw new ErrorUnknown(e.label());
		} 
	}

	public SummaryResponseSchema summary() throws ErrorUnknown {
		try {

			return new Gson().fromJson(accessor.get(this.url, "dashboard/summary").content(), SummaryResponseSchema.class);
		} catch (RestfulFailure e) {
			throw new ErrorUnknown(e.label());
		} 
	}

	public OpinionSnapshotResponseSchema snapshot(String scale, RangeSchema range, FilterListSchema filters) throws ErrorUnknown {
		try {
			java.util.Map<String, String> parameters = new java.util.HashMap<String, String>() {{
				put("scale", scale);


			}};



			return new Gson().fromJson(accessor.get(this.url, "dashboard/snapshot", parameters).content(), OpinionSnapshotResponseSchema.class);
		} catch (RestfulFailure e) {
			throw new ErrorUnknown(e.label());
		} 
	}
}