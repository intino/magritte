package teseo.natives.real.data;



/**#/Users/oroncal/workspace/teseo/application/src/teseo/Teseo.tara#25#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.real.RealData self;

	@Override
	public String value() {
		return "Double";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.real.RealData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.real.RealData.class;
	}
}