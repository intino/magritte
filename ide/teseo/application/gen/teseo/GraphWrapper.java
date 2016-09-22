package teseo;

import tara.magritte.Graph;

import java.util.List;

public class GraphWrapper extends tara.magritte.GraphWrapper {

	protected Graph graph;
	private List<teseo.type.TypeData> typeDataList;
	private List<teseo.real.RealData> realDataList;
	private List<teseo.integer.IntegerData> integerDataList;
	private List<teseo.bool.BoolData> boolDataList;
	private List<teseo.text.TextData> textDataList;
	private List<teseo.date.DateData> dateDataList;
	private List<teseo.datetime.DateTimeData> dateTimeDataList;
	private List<teseo.file.FileData> fileDataList;
	private List<teseo.object.ObjectData> objectDataList;
	private List<teseo.list.ListData> listDataList;
	private List<teseo.Schema> schemaList;
	private List<teseo.Channel> channelList;
	private List<teseo.Service> serviceList;
	private List<teseo.rest.RESTService> rESTServiceList;
	private List<teseo.jmx.JMXService> jMXServiceList;
	private List<teseo.jms.JMSService> jMSServiceList;
	private List<teseo.Trigger> triggerList;
	private List<teseo.scheduled.ScheduledTrigger> scheduledTriggerList;
	private List<teseo.cron.CronTrigger> cronTriggerList;
	private List<teseo.boot.BootTrigger> bootTriggerList;
	private List<teseo.processwatcher.ProcessWatcherTrigger> processWatcherTriggerList;
	private List<teseo.directorywatcher.DirectoryWatcherTrigger> directoryWatcherTriggerList;
	private List<teseo.jmsqueuewatcher.JMSQueueWatcherTrigger> jMSQueueWatcherTriggerList;
	private List<teseo.jmstopicwatcher.JMSTopicWatcherTrigger> jMSTopicWatcherTriggerList;
	private List<teseo.jmsrequest.JMSRequestTrigger> jMSRequestTriggerList;

	public GraphWrapper(Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("Teseo");
	    update();
	}

	protected void update() {
		typeDataList = this.graph.rootList(teseo.type.TypeData.class);
		realDataList = this.graph.rootList(teseo.real.RealData.class);
		integerDataList = this.graph.rootList(teseo.integer.IntegerData.class);
		boolDataList = this.graph.rootList(teseo.bool.BoolData.class);
		textDataList = this.graph.rootList(teseo.text.TextData.class);
		dateDataList = this.graph.rootList(teseo.date.DateData.class);
		dateTimeDataList = this.graph.rootList(teseo.datetime.DateTimeData.class);
		fileDataList = this.graph.rootList(teseo.file.FileData.class);
		objectDataList = this.graph.rootList(teseo.object.ObjectData.class);
		listDataList = this.graph.rootList(teseo.list.ListData.class);
		schemaList = this.graph.rootList(teseo.Schema.class);
		channelList = this.graph.rootList(teseo.Channel.class);
		serviceList = this.graph.rootList(teseo.Service.class);
		rESTServiceList = this.graph.rootList(teseo.rest.RESTService.class);
		jMXServiceList = this.graph.rootList(teseo.jmx.JMXService.class);
		jMSServiceList = this.graph.rootList(teseo.jms.JMSService.class);
		triggerList = this.graph.rootList(teseo.Trigger.class);
		scheduledTriggerList = this.graph.rootList(teseo.scheduled.ScheduledTrigger.class);
		cronTriggerList = this.graph.rootList(teseo.cron.CronTrigger.class);
		bootTriggerList = this.graph.rootList(teseo.boot.BootTrigger.class);
		processWatcherTriggerList = this.graph.rootList(teseo.processwatcher.ProcessWatcherTrigger.class);
		directoryWatcherTriggerList = this.graph.rootList(teseo.directorywatcher.DirectoryWatcherTrigger.class);
		jMSQueueWatcherTriggerList = this.graph.rootList(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
		jMSTopicWatcherTriggerList = this.graph.rootList(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
		jMSRequestTriggerList = this.graph.rootList(teseo.jmsrequest.JMSRequestTrigger.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		if (node.is("TypeData")) this.typeDataList.add(node.as(teseo.type.TypeData.class));
		if (node.is("RealData")) this.realDataList.add(node.as(teseo.real.RealData.class));
		if (node.is("IntegerData")) this.integerDataList.add(node.as(teseo.integer.IntegerData.class));
		if (node.is("BoolData")) this.boolDataList.add(node.as(teseo.bool.BoolData.class));
		if (node.is("TextData")) this.textDataList.add(node.as(teseo.text.TextData.class));
		if (node.is("DateData")) this.dateDataList.add(node.as(teseo.date.DateData.class));
		if (node.is("DateTimeData")) this.dateTimeDataList.add(node.as(teseo.datetime.DateTimeData.class));
		if (node.is("FileData")) this.fileDataList.add(node.as(teseo.file.FileData.class));
		if (node.is("ObjectData")) this.objectDataList.add(node.as(teseo.object.ObjectData.class));
		if (node.is("ListData")) this.listDataList.add(node.as(teseo.list.ListData.class));
		if (node.is("Schema")) this.schemaList.add(node.as(teseo.Schema.class));
		if (node.is("Channel")) this.channelList.add(node.as(teseo.Channel.class));
		if (node.is("Service")) this.serviceList.add(node.as(teseo.Service.class));
		if (node.is("RESTService")) this.rESTServiceList.add(node.as(teseo.rest.RESTService.class));
		if (node.is("JMXService")) this.jMXServiceList.add(node.as(teseo.jmx.JMXService.class));
		if (node.is("JMSService")) this.jMSServiceList.add(node.as(teseo.jms.JMSService.class));
		if (node.is("Trigger")) this.triggerList.add(node.as(teseo.Trigger.class));
		if (node.is("ScheduledTrigger")) this.scheduledTriggerList.add(node.as(teseo.scheduled.ScheduledTrigger.class));
		if (node.is("CronTrigger")) this.cronTriggerList.add(node.as(teseo.cron.CronTrigger.class));
		if (node.is("BootTrigger")) this.bootTriggerList.add(node.as(teseo.boot.BootTrigger.class));
		if (node.is("ProcessWatcherTrigger")) this.processWatcherTriggerList.add(node.as(teseo.processwatcher.ProcessWatcherTrigger.class));
		if (node.is("DirectoryWatcherTrigger")) this.directoryWatcherTriggerList.add(node.as(teseo.directorywatcher.DirectoryWatcherTrigger.class));
		if (node.is("JMSQueueWatcherTrigger")) this.jMSQueueWatcherTriggerList.add(node.as(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class));
		if (node.is("JMSTopicWatcherTrigger")) this.jMSTopicWatcherTriggerList.add(node.as(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class));
		if (node.is("JMSRequestTrigger")) this.jMSRequestTriggerList.add(node.as(teseo.jmsrequest.JMSRequestTrigger.class));
	}

	@Override
	protected void removeNode(tara.magritte.Node node) {
		if (node.is("TypeData")) this.typeDataList.remove(node.as(teseo.type.TypeData.class));
		if (node.is("RealData")) this.realDataList.remove(node.as(teseo.real.RealData.class));
		if (node.is("IntegerData")) this.integerDataList.remove(node.as(teseo.integer.IntegerData.class));
		if (node.is("BoolData")) this.boolDataList.remove(node.as(teseo.bool.BoolData.class));
		if (node.is("TextData")) this.textDataList.remove(node.as(teseo.text.TextData.class));
		if (node.is("DateData")) this.dateDataList.remove(node.as(teseo.date.DateData.class));
		if (node.is("DateTimeData")) this.dateTimeDataList.remove(node.as(teseo.datetime.DateTimeData.class));
		if (node.is("FileData")) this.fileDataList.remove(node.as(teseo.file.FileData.class));
		if (node.is("ObjectData")) this.objectDataList.remove(node.as(teseo.object.ObjectData.class));
		if (node.is("ListData")) this.listDataList.remove(node.as(teseo.list.ListData.class));
		if (node.is("Schema")) this.schemaList.remove(node.as(teseo.Schema.class));
		if (node.is("Channel")) this.channelList.remove(node.as(teseo.Channel.class));
		if (node.is("Service")) this.serviceList.remove(node.as(teseo.Service.class));
		if (node.is("RESTService")) this.rESTServiceList.remove(node.as(teseo.rest.RESTService.class));
		if (node.is("JMXService")) this.jMXServiceList.remove(node.as(teseo.jmx.JMXService.class));
		if (node.is("JMSService")) this.jMSServiceList.remove(node.as(teseo.jms.JMSService.class));
		if (node.is("Trigger")) this.triggerList.remove(node.as(teseo.Trigger.class));
		if (node.is("ScheduledTrigger")) this.scheduledTriggerList.remove(node.as(teseo.scheduled.ScheduledTrigger.class));
		if (node.is("CronTrigger")) this.cronTriggerList.remove(node.as(teseo.cron.CronTrigger.class));
		if (node.is("BootTrigger")) this.bootTriggerList.remove(node.as(teseo.boot.BootTrigger.class));
		if (node.is("ProcessWatcherTrigger")) this.processWatcherTriggerList.remove(node.as(teseo.processwatcher.ProcessWatcherTrigger.class));
		if (node.is("DirectoryWatcherTrigger")) this.directoryWatcherTriggerList.remove(node.as(teseo.directorywatcher.DirectoryWatcherTrigger.class));
		if (node.is("JMSQueueWatcherTrigger")) this.jMSQueueWatcherTriggerList.remove(node.as(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class));
		if (node.is("JMSTopicWatcherTrigger")) this.jMSTopicWatcherTriggerList.remove(node.as(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class));
		if (node.is("JMSRequestTrigger")) this.jMSRequestTriggerList.remove(node.as(teseo.jmsrequest.JMSRequestTrigger.class));
	}

	public String message(String language, String key, Object... parameters) {
		return graph.i18n().message(language, key, parameters);
	}

	public java.net.URL resourceAsMessage(String language, String key) {
		return graph.loadResource(graph.i18n().message(language, key));
	}

	public java.util.Map<String,String> keysIn(String language) {
		return graph.i18n().wordsIn(language);
	}

	public tara.magritte.Concept concept(String concept) {
		return graph.concept(concept);
	}

	public tara.magritte.Concept concept(java.lang.Class<? extends tara.magritte.Layer> layerClass) {
		return graph.concept(layerClass);
	}

	public List<tara.magritte.Concept> conceptList() {
		return graph.conceptList();
	}

	public List<tara.magritte.Concept> conceptList(java.util.function.Predicate<tara.magritte.Concept> predicate) {
		return graph.conceptList(predicate);
	}

	public tara.magritte.Node createRoot(tara.magritte.Concept concept, String namespace) {
		return graph.createRoot(concept, namespace);
	}

	public <T extends tara.magritte.Layer> T createRoot(java.lang.Class<T> layerClass, String namespace) {
		return graph.createRoot(layerClass, namespace);
	}

	public tara.magritte.Node createRoot(String concept, String namespace) {
		return graph.createRoot(concept, namespace);
	}

	public <T extends tara.magritte.Layer> T createRoot(java.lang.Class<T> layerClass, String namespace, String id) {
		return graph.createRoot(layerClass, namespace, id);
	}

	public tara.magritte.Node createRoot(String concept, String namespace, String id) {
		return graph.createRoot(concept, namespace, id);
	}

	public tara.magritte.Node createRoot(tara.magritte.Concept concept, String namespace, String id) {
		return graph.createRoot(concept, namespace, id);
	}

	public List<teseo.type.TypeData> typeDataList() {
	    return typeDataList;
	}

	public List<teseo.real.RealData> realDataList() {
	    return realDataList;
	}

	public List<teseo.integer.IntegerData> integerDataList() {
	    return integerDataList;
	}

	public List<teseo.bool.BoolData> boolDataList() {
	    return boolDataList;
	}

	public List<teseo.text.TextData> textDataList() {
	    return textDataList;
	}

	public List<teseo.date.DateData> dateDataList() {
	    return dateDataList;
	}

	public List<teseo.datetime.DateTimeData> dateTimeDataList() {
	    return dateTimeDataList;
	}

	public List<teseo.file.FileData> fileDataList() {
	    return fileDataList;
	}

	public List<teseo.object.ObjectData> objectDataList() {
	    return objectDataList;
	}

	public List<teseo.list.ListData> listDataList() {
	    return listDataList;
	}

	public List<teseo.Schema> schemaList() {
	    return schemaList;
	}

	public List<teseo.Channel> channelList() {
	    return channelList;
	}

	public List<teseo.Service> serviceList() {
	    return serviceList;
	}

	public List<teseo.rest.RESTService> rESTServiceList() {
	    return rESTServiceList;
	}

	public List<teseo.jmx.JMXService> jMXServiceList() {
	    return jMXServiceList;
	}

	public List<teseo.jms.JMSService> jMSServiceList() {
	    return jMSServiceList;
	}

	public List<teseo.Trigger> triggerList() {
	    return triggerList;
	}

	public List<teseo.scheduled.ScheduledTrigger> scheduledTriggerList() {
	    return scheduledTriggerList;
	}

	public List<teseo.cron.CronTrigger> cronTriggerList() {
	    return cronTriggerList;
	}

	public List<teseo.boot.BootTrigger> bootTriggerList() {
	    return bootTriggerList;
	}

	public List<teseo.processwatcher.ProcessWatcherTrigger> processWatcherTriggerList() {
	    return processWatcherTriggerList;
	}

	public List<teseo.directorywatcher.DirectoryWatcherTrigger> directoryWatcherTriggerList() {
	    return directoryWatcherTriggerList;
	}

	public List<teseo.jmsqueuewatcher.JMSQueueWatcherTrigger> jMSQueueWatcherTriggerList() {
	    return jMSQueueWatcherTriggerList;
	}

	public List<teseo.jmstopicwatcher.JMSTopicWatcherTrigger> jMSTopicWatcherTriggerList() {
	    return jMSTopicWatcherTriggerList;
	}

	public List<teseo.jmsrequest.JMSRequestTrigger> jMSRequestTriggerList() {
	    return jMSRequestTriggerList;
	}

	public List<teseo.type.TypeData> typeDataList(java.util.function.Predicate<teseo.type.TypeData> predicate) {
	    return typeDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.type.TypeData typeData(int index) {
		return typeDataList.get(index);
	}

	public List<teseo.real.RealData> realDataList(java.util.function.Predicate<teseo.real.RealData> predicate) {
	    return realDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.real.RealData realData(int index) {
		return realDataList.get(index);
	}

	public List<teseo.integer.IntegerData> integerDataList(java.util.function.Predicate<teseo.integer.IntegerData> predicate) {
	    return integerDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.integer.IntegerData integerData(int index) {
		return integerDataList.get(index);
	}

	public List<teseo.bool.BoolData> boolDataList(java.util.function.Predicate<teseo.bool.BoolData> predicate) {
	    return boolDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.bool.BoolData boolData(int index) {
		return boolDataList.get(index);
	}

	public List<teseo.text.TextData> textDataList(java.util.function.Predicate<teseo.text.TextData> predicate) {
	    return textDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.text.TextData textData(int index) {
		return textDataList.get(index);
	}

	public List<teseo.date.DateData> dateDataList(java.util.function.Predicate<teseo.date.DateData> predicate) {
	    return dateDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.date.DateData dateData(int index) {
		return dateDataList.get(index);
	}

	public List<teseo.datetime.DateTimeData> dateTimeDataList(java.util.function.Predicate<teseo.datetime.DateTimeData> predicate) {
	    return dateTimeDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.datetime.DateTimeData dateTimeData(int index) {
		return dateTimeDataList.get(index);
	}

	public List<teseo.file.FileData> fileDataList(java.util.function.Predicate<teseo.file.FileData> predicate) {
	    return fileDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.file.FileData fileData(int index) {
		return fileDataList.get(index);
	}

	public List<teseo.object.ObjectData> objectDataList(java.util.function.Predicate<teseo.object.ObjectData> predicate) {
	    return objectDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.object.ObjectData objectData(int index) {
		return objectDataList.get(index);
	}

	public List<teseo.list.ListData> listDataList(java.util.function.Predicate<teseo.list.ListData> predicate) {
	    return listDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.list.ListData listData(int index) {
		return listDataList.get(index);
	}

	public List<teseo.Schema> schemaList(java.util.function.Predicate<teseo.Schema> predicate) {
	    return schemaList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.Schema schema(int index) {
		return schemaList.get(index);
	}

	public List<teseo.Channel> channelList(java.util.function.Predicate<teseo.Channel> predicate) {
	    return channelList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.Channel channel(int index) {
		return channelList.get(index);
	}

	public List<teseo.Service> serviceList(java.util.function.Predicate<teseo.Service> predicate) {
	    return serviceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.Service service(int index) {
		return serviceList.get(index);
	}

	public List<teseo.rest.RESTService> rESTServiceList(java.util.function.Predicate<teseo.rest.RESTService> predicate) {
	    return rESTServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.rest.RESTService rESTService(int index) {
		return rESTServiceList.get(index);
	}

	public List<teseo.jmx.JMXService> jMXServiceList(java.util.function.Predicate<teseo.jmx.JMXService> predicate) {
	    return jMXServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.jmx.JMXService jMXService(int index) {
		return jMXServiceList.get(index);
	}

	public List<teseo.jms.JMSService> jMSServiceList(java.util.function.Predicate<teseo.jms.JMSService> predicate) {
	    return jMSServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.jms.JMSService jMSService(int index) {
		return jMSServiceList.get(index);
	}

	public List<teseo.Trigger> triggerList(java.util.function.Predicate<teseo.Trigger> predicate) {
	    return triggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.Trigger trigger(int index) {
		return triggerList.get(index);
	}

	public List<teseo.scheduled.ScheduledTrigger> scheduledTriggerList(java.util.function.Predicate<teseo.scheduled.ScheduledTrigger> predicate) {
	    return scheduledTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.scheduled.ScheduledTrigger scheduledTrigger(int index) {
		return scheduledTriggerList.get(index);
	}

	public List<teseo.cron.CronTrigger> cronTriggerList(java.util.function.Predicate<teseo.cron.CronTrigger> predicate) {
	    return cronTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.cron.CronTrigger cronTrigger(int index) {
		return cronTriggerList.get(index);
	}

	public List<teseo.boot.BootTrigger> bootTriggerList(java.util.function.Predicate<teseo.boot.BootTrigger> predicate) {
	    return bootTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.boot.BootTrigger bootTrigger(int index) {
		return bootTriggerList.get(index);
	}

	public List<teseo.processwatcher.ProcessWatcherTrigger> processWatcherTriggerList(java.util.function.Predicate<teseo.processwatcher.ProcessWatcherTrigger> predicate) {
	    return processWatcherTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.processwatcher.ProcessWatcherTrigger processWatcherTrigger(int index) {
		return processWatcherTriggerList.get(index);
	}

	public List<teseo.directorywatcher.DirectoryWatcherTrigger> directoryWatcherTriggerList(java.util.function.Predicate<teseo.directorywatcher.DirectoryWatcherTrigger> predicate) {
	    return directoryWatcherTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.directorywatcher.DirectoryWatcherTrigger directoryWatcherTrigger(int index) {
		return directoryWatcherTriggerList.get(index);
	}

	public List<teseo.jmsqueuewatcher.JMSQueueWatcherTrigger> jMSQueueWatcherTriggerList(java.util.function.Predicate<teseo.jmsqueuewatcher.JMSQueueWatcherTrigger> predicate) {
	    return jMSQueueWatcherTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.jmsqueuewatcher.JMSQueueWatcherTrigger jMSQueueWatcherTrigger(int index) {
		return jMSQueueWatcherTriggerList.get(index);
	}

	public List<teseo.jmstopicwatcher.JMSTopicWatcherTrigger> jMSTopicWatcherTriggerList(java.util.function.Predicate<teseo.jmstopicwatcher.JMSTopicWatcherTrigger> predicate) {
	    return jMSTopicWatcherTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.jmstopicwatcher.JMSTopicWatcherTrigger jMSTopicWatcherTrigger(int index) {
		return jMSTopicWatcherTriggerList.get(index);
	}

	public List<teseo.jmsrequest.JMSRequestTrigger> jMSRequestTriggerList(java.util.function.Predicate<teseo.jmsrequest.JMSRequestTrigger> predicate) {
	    return jMSRequestTriggerList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.jmsrequest.JMSRequestTrigger jMSRequestTrigger(int index) {
		return jMSRequestTriggerList.get(index);
	}

	public tara.magritte.Graph graph() {
		return graph;
	}

	public Create create() {
		return new Create("Misc", null);
	}

	public Create create(String namespace) {
		return new Create(namespace, null);
	}

	public Create create(String namespace, String name) {
		return new Create(namespace, name);
	}

	public class Create {
		private final String namespace;
		private final String name;

		public Create(String namespace, String name) {
			this.namespace = namespace;
			this.name = name;
		}

		public teseo.type.TypeData typeData(tara.magritte.Expression<java.lang.String> type) {
			teseo.type.TypeData newElement = GraphWrapper.this.graph.createRoot(teseo.type.TypeData.class, namespace, name).as(teseo.type.TypeData.class);
			newElement.node().set(newElement, "type", java.util.Collections.singletonList(type));
			return newElement;
		}

		public teseo.real.RealData realData() {
			teseo.real.RealData newElement = GraphWrapper.this.graph.createRoot(teseo.real.RealData.class, namespace, name).as(teseo.real.RealData.class);
			
			return newElement;
		}

		public teseo.integer.IntegerData integerData() {
			teseo.integer.IntegerData newElement = GraphWrapper.this.graph.createRoot(teseo.integer.IntegerData.class, namespace, name).as(teseo.integer.IntegerData.class);
			
			return newElement;
		}

		public teseo.bool.BoolData boolData() {
			teseo.bool.BoolData newElement = GraphWrapper.this.graph.createRoot(teseo.bool.BoolData.class, namespace, name).as(teseo.bool.BoolData.class);
			
			return newElement;
		}

		public teseo.text.TextData textData() {
			teseo.text.TextData newElement = GraphWrapper.this.graph.createRoot(teseo.text.TextData.class, namespace, name).as(teseo.text.TextData.class);
			
			return newElement;
		}

		public teseo.date.DateData dateData() {
			teseo.date.DateData newElement = GraphWrapper.this.graph.createRoot(teseo.date.DateData.class, namespace, name).as(teseo.date.DateData.class);
			
			return newElement;
		}

		public teseo.datetime.DateTimeData dateTimeData() {
			teseo.datetime.DateTimeData newElement = GraphWrapper.this.graph.createRoot(teseo.datetime.DateTimeData.class, namespace, name).as(teseo.datetime.DateTimeData.class);
			
			return newElement;
		}

		public teseo.file.FileData fileData() {
			teseo.file.FileData newElement = GraphWrapper.this.graph.createRoot(teseo.file.FileData.class, namespace, name).as(teseo.file.FileData.class);
			
			return newElement;
		}

		public teseo.object.ObjectData objectData(teseo.Schema schema) {
			teseo.object.ObjectData newElement = GraphWrapper.this.graph.createRoot(teseo.object.ObjectData.class, namespace, name).as(teseo.object.ObjectData.class);
			newElement.node().set(newElement, "schema", java.util.Collections.singletonList(schema));
			return newElement;
		}

		public teseo.list.ListData listData() {
			teseo.list.ListData newElement = GraphWrapper.this.graph.createRoot(teseo.list.ListData.class, namespace, name).as(teseo.list.ListData.class);
			
			return newElement;
		}

		public teseo.Schema schema() {
			teseo.Schema newElement = GraphWrapper.this.graph.createRoot(teseo.Schema.class, namespace, name).as(teseo.Schema.class);
			
			return newElement;
		}

		public teseo.Channel channel() {
			teseo.Channel newElement = GraphWrapper.this.graph.createRoot(teseo.Channel.class, namespace, name).as(teseo.Channel.class);
			
			return newElement;
		}

		public teseo.Service service() {
			teseo.Service newElement = GraphWrapper.this.graph.createRoot(teseo.Service.class, namespace, name).as(teseo.Service.class);
			
			return newElement;
		}

		public teseo.rest.RESTService rESTService(java.lang.String title, java.lang.String path) {
			teseo.rest.RESTService newElement = GraphWrapper.this.graph.createRoot(teseo.rest.RESTService.class, namespace, name).as(teseo.rest.RESTService.class);
			newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.node().set(newElement, "path", java.util.Collections.singletonList(path));
			return newElement;
		}

		public teseo.jmx.JMXService jMXService() {
			teseo.jmx.JMXService newElement = GraphWrapper.this.graph.createRoot(teseo.jmx.JMXService.class, namespace, name).as(teseo.jmx.JMXService.class);
			
			return newElement;
		}

		public teseo.jms.JMSService jMSService() {
			teseo.jms.JMSService newElement = GraphWrapper.this.graph.createRoot(teseo.jms.JMSService.class, namespace, name).as(teseo.jms.JMSService.class);
			
			return newElement;
		}

		public teseo.Trigger trigger() {
			teseo.Trigger newElement = GraphWrapper.this.graph.createRoot(teseo.Trigger.class, namespace, name).as(teseo.Trigger.class);
			
			return newElement;
		}

		public teseo.scheduled.ScheduledTrigger scheduledTrigger() {
			teseo.scheduled.ScheduledTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.scheduled.ScheduledTrigger.class, namespace, name).as(teseo.scheduled.ScheduledTrigger.class);
			
			return newElement;
		}

		public teseo.cron.CronTrigger cronTrigger(java.lang.String pattern) {
			teseo.cron.CronTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.cron.CronTrigger.class, namespace, name).as(teseo.cron.CronTrigger.class);
			newElement.node().set(newElement, "pattern", java.util.Collections.singletonList(pattern));
			return newElement;
		}

		public teseo.boot.BootTrigger bootTrigger() {
			teseo.boot.BootTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.boot.BootTrigger.class, namespace, name).as(teseo.boot.BootTrigger.class);
			
			return newElement;
		}

		public teseo.processwatcher.ProcessWatcherTrigger processWatcherTrigger(java.lang.String processName, List<teseo.processwatcher.ProcessWatcherTrigger.Events> events, teseo.functions.ProcessChecker check) {
			teseo.processwatcher.ProcessWatcherTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.processwatcher.ProcessWatcherTrigger.class, namespace, name).as(teseo.processwatcher.ProcessWatcherTrigger.class);
			newElement.node().set(newElement, "processName", java.util.Collections.singletonList(processName));
			newElement.node().set(newElement, "events", events);
			newElement.node().set(newElement, "check", java.util.Collections.singletonList(check));
			return newElement;
		}

		public teseo.directorywatcher.DirectoryWatcherTrigger directoryWatcherTrigger(java.net.URL directory, List<teseo.directorywatcher.DirectoryWatcherTrigger.Events> events, teseo.functions.DirectoryChecker check) {
			teseo.directorywatcher.DirectoryWatcherTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.directorywatcher.DirectoryWatcherTrigger.class, namespace, name).as(teseo.directorywatcher.DirectoryWatcherTrigger.class);
			newElement.node().set(newElement, "directory", java.util.Collections.singletonList(directory));
			newElement.node().set(newElement, "events", events);
			newElement.node().set(newElement, "check", java.util.Collections.singletonList(check));
			return newElement;
		}

		public teseo.jmsqueuewatcher.JMSQueueWatcherTrigger jMSQueueWatcherTrigger(java.lang.String queue) {
			teseo.jmsqueuewatcher.JMSQueueWatcherTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class, namespace, name).as(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
			newElement.node().set(newElement, "queue", java.util.Collections.singletonList(queue));
			return newElement;
		}

		public teseo.jmstopicwatcher.JMSTopicWatcherTrigger jMSTopicWatcherTrigger(java.lang.String topic) {
			teseo.jmstopicwatcher.JMSTopicWatcherTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class, namespace, name).as(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
			newElement.node().set(newElement, "topic", java.util.Collections.singletonList(topic));
			return newElement;
		}

		public teseo.jmsrequest.JMSRequestTrigger jMSRequestTrigger() {
			teseo.jmsrequest.JMSRequestTrigger newElement = GraphWrapper.this.graph.createRoot(teseo.jmsrequest.JMSRequestTrigger.class, namespace, name).as(teseo.jmsrequest.JMSRequestTrigger.class);
			
			return newElement;
		}

	}


}