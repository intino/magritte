package pandora.codegeneration.server.jms;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class JMSResourceTemplate extends Template {

	protected JMSResourceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new JMSResourceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
				rule().add((condition("type", "resource"))).add(literal("package ")).add(mark("package")).add(literal(".resources;\n\nimport teseo.exceptions.*;\nimport ")).add(mark("package")).add(literal(".*;\nimport com.google.gson.Gson;\nimport tara.magritte.Graph;\nimport teseo.jms.RequestConsumer;\n\nimport javax.jms.*;\n")).add(mark("schemaImport")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal("Resource implements RequestConsumer {\n\n\tprivate Graph graph;\n\n\tpublic  ")).add(mark("name", "firstUpperCase")).add(literal("Resource(Graph graph) {\n\t\tthis.graph = graph;\n\t}\n\n\tpublic void consume(Session session, Message request) {\n\t\t")).add(mark("call")).add(literal("\n\t}\n\n\tprivate ")).add(mark("package")).add(literal(".actions.")).add(mark("name", "firstUpperCase")).add(literal("Action actionFor(Message message) {\n\t\tfinal ")).add(mark("package")).add(literal(".actions.")).add(mark("name", "firstUpperCase")).add(literal("Action action = new ")).add(mark("package")).add(literal(".actions.")).add(mark("name", "firstUpperCase")).add(literal("Action();\n\t\taction.graph = this.graph;")).add(expression().add(literal("\n")).add(literal("\t\ttry {")).add(literal("\n")).add(literal("\t\t\t")).add(mark("parameter", "assign")).add(literal("\n")).add(literal("\t\t} catch (JMSException e) {")).add(literal("\n")).add(literal("\t\t\te.printStackTrace();")).add(literal("\n")).add(literal("\t\t}"))).add(literal("\n\t\treturn action;\n\t}\n\t")).add(expression().add(literal("\n")).add(literal("\tprivate Message responseMessage(Session session, String responseId, ")).add(mark("returnType")).add(literal(" response) {")).add(literal("\n")).add(literal("\t\ttry {")).add(literal("\n")).add(literal("\t\t\t")).add(mark("returnMessageType")).add(literal("Message message = session.create")).add(mark("returnMessageType")).add(literal("Message();")).add(literal("\n")).add(literal("\t\t\tmessage.setJMSCorrelationID(responseId);")).add(literal("\n")).add(literal("\t\t\tmessage.set")).add(mark("returnMessageType")).add(literal("(new Gson().toJson(response));")).add(literal("\n")).add(literal("\t\t\treturn message;")).add(literal("\n")).add(literal("\t\t} catch (JMSException e) {")).add(literal("\n")).add(literal("\t\t\te.printStackTrace();")).add(literal("\n")).add(literal("\t\t\treturn null;")).add(literal("\n")).add(literal("\t\t}")).add(literal("\n")).add(literal("\t}"))).add(literal("\n}")),
			rule().add(not(condition("type", "void")), (condition("trigger", "call"))).add(literal("response(session, replyTo(request), responseMessage(session, idOf(request), actionFor(request).execute()));")),
			rule().add((condition("trigger", "call"))).add(literal("actionFor(request).execute();")),
			rule().add((condition("type", "parameter & objectData")), (condition("trigger", "assign"))).add(literal("action.")).add(mark("name", "SnakeCaseToCamelCase", "firstLowerCase")).add(literal(" = new Gson().fromJson(((TextMessage) message).getText(), ")).add(mark("type")).add(literal(".class);\n")),
			rule().add((condition("type", "parameter")), (condition("trigger", "assign"))).add(literal("action.")).add(mark("name", "SnakeCaseToCamelCase", "firstLowerCase")).add(literal(" = message.get")).add(mark("type")).add(literal("Property(\"")).add(mark("name")).add(literal("\");")),
			rule().add((condition("type", "schemaImport"))).add(literal("import ")).add(mark("package")).add(literal(".schemas.*;"))
		);
		return this;
	}
}