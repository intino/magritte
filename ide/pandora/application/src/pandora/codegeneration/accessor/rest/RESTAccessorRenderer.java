package pandora.codegeneration.accessor.rest;

import org.siani.itrules.Template;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;
import pandora.Exception;
import pandora.codegeneration.schema.SchemaRenderer;
import pandora.helpers.Commons;
import pandora.type.TypeData;
import pandora.Resource;
import pandora.Response;
import pandora.Schema;
import pandora.codegeneration.schema.SchemaRenderer;
import pandora.date.DateData;
import pandora.datetime.DateTimeData;
import pandora.file.FileData;
import pandora.helpers.Commons;
import pandora.object.ObjectData;
import pandora.rest.RESTService;
import pandora.type.TypeData;

import java.io.File;
import java.util.List;

import static cottons.utils.StringHelper.snakeCaseToCamelCase;
import static pandora.helpers.Commons.writeFrame;

public class RESTAccessorRenderer {
	private final RESTService service;
	private File destination;
	private String packageName;

	public RESTAccessorRenderer(RESTService application) {
		this.service = application;
	}

	public void execute(File destination, String packageName) {
		this.destination = destination;
		this.packageName = packageName;
		new SchemaRenderer(service.graph(), destination, packageName).execute();
		processService(service);
	}

	private void processService(RESTService restService) {
		Frame frame = new Frame().addTypes("accessor");
		frame.addSlot("name", restService.name());
		frame.addSlot("package", packageName);
		setupAuthentication(restService, frame);
		if (!restService.graph().find(Schema.class).isEmpty())
			frame.addSlot("schemaImport", new Frame().addTypes("schemaImport").addSlot("package", packageName));
		frame.addSlot("resource", (AbstractFrame[]) restService.node().findNode(Resource.class).stream().
				map(resource -> processResource(resource, restService.authenticated() != null, restService.authenticatedWithCertificate() != null)).toArray(Frame[]::new));
		Commons.writeFrame(destination, snakeCaseToCamelCase(restService.name()) + "Accessor", getTemplate().format(frame));
	}

	private void setupAuthentication(RESTService restService, Frame frame) {
		if (restService.authenticated() != null) frame.addSlot("auth", "");
		if (restService.authenticatedWithCertificate() != null) frame.addSlot("certificate", "");
		else if (restService.authenticatedWithPassword() != null) frame.addSlot("user", "");
	}

	private Frame processResource(Resource resource, boolean authenticated, boolean cert) {
		return new Frame().addTypes("resource", resource.type().toString())
				.addSlot("returnType", Commons.returnType(resource.response()))
				.addSlot("name", resource.name())
				.addSlot("parameter", (AbstractFrame[]) parameters(resource.resourceParameterList()))
				.addSlot("invokeSentence", invokeSentence(resource, authenticated, cert))
				.addSlot("exceptionResponses", exceptionResponses(resource));
	}

	private Frame[] parameters(List<Resource.Parameter> parameters) {
		return parameters.stream().map(this::parameter).toArray(Frame[]::new);
	}

	private Frame parameter(Resource.Parameter parameter) {
		return new Frame().addTypes("parameter", parameter.in().toString(), (parameter.required() ? "required" : "optional"), parameter.asType().getClass().getSimpleName())
				.addSlot("name", parameter.name())
				.addSlot("parameterType", parameter.asType().type());
	}

	private Frame invokeSentence(Resource resource, boolean authenticated, boolean cert) {
		Response response = resource.response();
		Frame result;
		if (response.asType() == null) result = voidInvokeSentence();
		else if (response.isObject()) result = objectInvokeSentence(response.asObject());
		else if (response.isFile()) result = fileInvokeSentence(response.asFile());
		else if (response.isDate()) result = dateInvokeSentence(response.asDate());
		else if (response.isDateTime()) result = dateTimeInvokeSentence(response.asDateTime());
		else result = primitiveInvokeSentence(response.asType());
		return result.addSlot("doInvoke", doInvoke(resource, authenticated, cert));
	}

	private Frame doInvoke(Resource resource, boolean authenticated, boolean cert) {
		final Frame frame = new Frame().addTypes("doInvoke")
				.addSlot("relativePath", Commons.path(resource))
				.addSlot("type", resource.type().toString())
				.addSlot("pathParameters", Commons.pathParameters(resource));
		if (authenticated) frame.addTypes("auth");
		if (cert) frame.addTypes("cert");
		if (Commons.queryParameters(resource) > 0) frame.addSlot("parameters", "parameters");
		else if (Commons.fileParameters(resource) > 0) frame.addSlot("parameters", "resource");
		return frame;

	}

	private Frame exceptionResponses(Resource resource) {
		List<Exception> exceptions = resource.exceptionList();
		if (exceptions.isEmpty()) return new Frame().addTypes("exceptionResponses", "none");
		return new Frame().addTypes("exceptionResponses")
				.addSlot("exceptionResponse", (AbstractFrame[]) exceptionResponses(exceptions));
	}

	private Frame[] exceptionResponses(List<Exception> responses) {
		return responses.stream().map(this::exceptionResponse).toArray(Frame[]::new);
	}

	private Frame exceptionResponse(Exception response) {
		return new Frame().addTypes("exceptionResponse")
				.addSlot("code", response.code().value())
				.addSlot("exceptionName", response.code().toString());
	}

	private Frame voidInvokeSentence() {
		return new Frame().addTypes("invokeSentence", "void");
	}

	private Frame objectInvokeSentence(ObjectData objectData) {
		return new Frame().addTypes("invokeSentence", "object")
				.addSlot("returnType", objectData.type());
	}

	private Frame fileInvokeSentence(FileData fileData) {
		return new Frame().addTypes("invokeSentence", "file");
		//TODO
	}

	private Frame dateInvokeSentence(DateData dateData) {
		return new Frame().addTypes("invokeSentence", "file");
		//TODO
	}

	private Frame dateTimeInvokeSentence(DateTimeData dateTimeData) {
		return new Frame().addTypes("invokeSentence", "file");
		//TODO
	}

	private Frame primitiveInvokeSentence(TypeData typeData) {
		return new Frame().addTypes("invokeSentence", "primitive", typeData.type())
				.addSlot("returnType", typeData.type());
	}

	private Template getTemplate() {
		Template template = RESTAccessorTemplate.create();
		template.add("SnakeCaseToCamelCase", value -> snakeCaseToCamelCase(value.toString()));
		template.add("ReturnTypeFormatter", (value) -> value.equals("Void") ? "void" : value);
		template.add("validname", value -> value.toString().replace("-", "").toLowerCase());
		return template;
	}

}
