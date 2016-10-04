package pandora.codegeneration;

import cottons.utils.Files;
import pandora.PandoraApplication;
import pandora.codegeneration.accessor.rest.RESTAccessorRenderer;
import pandora.codegeneration.format.FormatRenderer;
import pandora.codegeneration.server.jms.JMSResourceRenderer;
import pandora.codegeneration.server.jms.JMSServiceRenderer;
import pandora.codegeneration.server.jms.channel.ChannelRenderer;
import pandora.codegeneration.server.jms.channel.SubscriptionModelRenderer;
import pandora.codegeneration.server.jmx.JMXOperationsServiceRenderer;
import pandora.codegeneration.server.jmx.JMXServerRenderer;
import pandora.codegeneration.server.rest.RESTResourceRenderer;
import pandora.codegeneration.server.rest.RESTServiceRenderer;
import pandora.codegeneration.server.scheduling.ScheduledTaskRenderer;
import pandora.codegeneration.server.scheduling.SchedulerRenderer;
import tara.magritte.Graph;

import java.io.File;

public class FullRenderer {

    private final Graph graph;
    private final File gen;
    private final File src;
    private final String packageName;

    public FullRenderer(Graph graph, File src, File gen, String packageName) {
        this.graph = graph;
        this.gen = gen;
        this.src = src;
        this.packageName = packageName;
    }

    public void execute() {
        Files.removeDir(gen);
        formats();
        rest();
        scheduling();
        jmx();
        jms();
        channels();
    }

    private void formats() {
        new FormatRenderer(graph, gen, packageName).execute();
    }

    private void rest() {
        new RESTResourceRenderer(graph, gen, src, packageName).execute();
        new RESTServiceRenderer(graph, gen, packageName).execute();
        ((PandoraApplication) graph.application()).rESTServiceList().forEach(r -> new RESTAccessorRenderer(r, gen, packageName).execute());
    }

    private void jmx() {
        new JMXOperationsServiceRenderer(graph, src, gen, packageName).execute();
        new JMXServerRenderer(graph, gen, packageName).execute();
    }

    private void jms() {
        new JMSResourceRenderer(graph, src, gen, packageName).execute();
        new JMSServiceRenderer(graph, gen, packageName).execute();
    }

    private void scheduling() {
        new ScheduledTaskRenderer(graph, src, gen, packageName).execute();
        new SchedulerRenderer(graph, gen, packageName).execute();
    }

    private void channels() {
        new SubscriptionModelRenderer(graph, src, gen, packageName).execute();
        new ChannelRenderer(graph, gen, packageName).execute();
    }

}