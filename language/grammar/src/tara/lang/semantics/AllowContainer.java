package tara.lang.semantics;

import java.util.List;

public interface AllowContainer {


	AllowContainer allow(Allow... allow);

	List<Allow> allows();
}
