package pandora.codegeneration.exception;

import org.siani.itrules.Template;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;
import pandora.Exception;
import pandora.Format;
import pandora.Resource;
import pandora.codegeneration.action.MethodActionRenderer;
import pandora.helpers.Commons;
import pandora.rest.RESTService;
import tara.magritte.Graph;

import java.io.File;
import java.util.List;

import static cottons.utils.StringHelper.snakeCaseToCamelCase;
import static pandora.helpers.Commons.writeFrame;

public class ExceptionRenderer {

    private static final String EXCEPTIONS = "exceptions";
    private final List<Exception> exceptions;
    private File gen;
    private String packageName;

    public ExceptionRenderer(Graph graph, File gen, String packageName) {
        exceptions = graph.find(Exception.class);
        this.gen = gen;
        this.packageName = packageName;
    }

    public void execute() {
        exceptions.forEach(this::processException);
    }

    private void processException(Exception exception) {
        if(exception.name())
        Frame frame = new Frame().addTypes("exception");
        frame.addSlot("name", exception.name());
        frame.addSlot("package", packageName);
        frame.addSlot("doTask", doTask(resource));
        frame.addSlot("throws", throwCodes(resource));
        frame.addSlot("returnType", Commons.returnType(resource.response()));
        frame.addSlot("parameter", (AbstractFrame[]) parameters(resource.resourceParameterList()));
        if (!resource.graph().find(Format.class).isEmpty())
            frame.addSlot("formatImport", new Frame().addTypes("formatImport").addSlot("package", packageName));
        writeFrame(new File(gen, EXCEPTIONS), snakeCaseToCamelCase(resource.name()) + "Exception", template().format(frame));
        return frame;
    }

    private Template template() {
        return ExceptionTemplate.create();
    }

}
