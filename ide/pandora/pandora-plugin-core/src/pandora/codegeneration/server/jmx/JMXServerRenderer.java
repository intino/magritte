package pandora.codegeneration.server.jmx;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import pandora.helpers.Commons;
import pandora.jmx.JMXService;
import tara.magritte.Graph;
import pandora.Operation;
import pandora.helpers.Commons;
import pandora.jmx.JMXService;

import java.io.File;
import java.util.List;

import static cottons.utils.StringHelper.snakeCaseToCamelCase;

public class JMXServerRenderer {
	private final List<JMXService> jmxServices;
	private final File destiny;
	private final String packageName;

	public JMXServerRenderer(Graph graph, File destiny, String packageName) {
		jmxServices = graph.find(JMXService.class);
		this.destiny = destiny;
		this.packageName = packageName;
	}


	public void execute() {
		jmxServices.forEach(this::processService);
	}

	private void processService(JMXService service) {
		final List<Operation> operations = service.operationList();
		if (operations.isEmpty()) return;
		Frame frame = new Frame().addTypes("jmxserver");
		frame.addSlot("name", service.name());
		frame.addSlot("package", packageName);
		Commons.writeFrame(destiny, "JMX" +snakeCaseToCamelCase(service.name()), template().format(frame));
	}


	private Template template() {
		Template template = JMXServerTemplate.create();
		template.add("SnakeCaseToCamelCase", value -> snakeCaseToCamelCase(value.toString()));
		template.add("validname", value -> value.toString().replace("-", "").toLowerCase());
		template.add("quoted", value -> '"' + value.toString() + '"');
		return template;
	}
}
