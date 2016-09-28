package pandora;

import tara.magritte.Graph;

import java.util.List;

public class GraphWrapper extends tara.magritte.GraphWrapper {

	protected Graph graph;
	private List<pandora.type.TypeData> typeDataList;
	private List<pandora.real.RealData> realDataList;
	private List<pandora.integer.IntegerData> integerDataList;
	private List<pandora.bool.BoolData> boolDataList;
	private List<pandora.text.TextData> textDataList;
	private List<pandora.date.DateData> dateDataList;
	private List<pandora.datetime.DateTimeData> dateTimeDataList;
	private List<pandora.file.FileData> fileDataList;
	private List<pandora.object.ObjectData> objectDataList;
	private List<pandora.list.ListData> listDataList;
	private List<pandora.Format> formatList;
	private List<pandora.Task> taskList;
	private List<pandora.scheduled.ScheduledTask> scheduledTaskList;
	private List<pandora.boottrigger.BootTriggerTask> bootTriggerTaskList;
	private List<pandora.timeouttrigger.TimeoutTriggerTask> timeoutTriggerTaskList;
	private List<pandora.periodictrigger.PeriodicTriggerTask> periodicTriggerTaskList;
	private List<pandora.crontrigger.CronTriggerTask> cronTriggerTaskList;
	private List<pandora.shutdowntrigger.ShutdownTriggerTask> shutdownTriggerTaskList;
	private List<pandora.directorysentinel.DirectorySentinelTask> directorySentinelTaskList;
	private List<pandora.processsentinel.ProcessSentinelTask> processSentinelTaskList;
	private List<pandora.jmsqueuesentinel.JMSQueueSentinelTask> jMSQueueSentinelTaskList;
	private List<pandora.jmstopicsentinel.JMSTopicSentinelTask> jMSTopicSentinelTaskList;
	private List<pandora.Channel> channelList;
	private List<pandora.Queue> queueList;
	private List<pandora.Topic> topicList;
	private List<pandora.Service> serviceList;
	private List<pandora.rest.RESTService> rESTServiceList;
	private List<pandora.jmx.JMXService> jMXServiceList;
	private List<pandora.jms.JMSService> jMSServiceList;

	public GraphWrapper(Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("Pandora");
	    update();
	}

	protected void update() {
		typeDataList = this.graph.rootList(pandora.type.TypeData.class);
		realDataList = this.graph.rootList(pandora.real.RealData.class);
		integerDataList = this.graph.rootList(pandora.integer.IntegerData.class);
		boolDataList = this.graph.rootList(pandora.bool.BoolData.class);
		textDataList = this.graph.rootList(pandora.text.TextData.class);
		dateDataList = this.graph.rootList(pandora.date.DateData.class);
		dateTimeDataList = this.graph.rootList(pandora.datetime.DateTimeData.class);
		fileDataList = this.graph.rootList(pandora.file.FileData.class);
		objectDataList = this.graph.rootList(pandora.object.ObjectData.class);
		listDataList = this.graph.rootList(pandora.list.ListData.class);
		formatList = this.graph.rootList(pandora.Format.class);
		taskList = this.graph.rootList(pandora.Task.class);
		scheduledTaskList = this.graph.rootList(pandora.scheduled.ScheduledTask.class);
		bootTriggerTaskList = this.graph.rootList(pandora.boottrigger.BootTriggerTask.class);
		timeoutTriggerTaskList = this.graph.rootList(pandora.timeouttrigger.TimeoutTriggerTask.class);
		periodicTriggerTaskList = this.graph.rootList(pandora.periodictrigger.PeriodicTriggerTask.class);
		cronTriggerTaskList = this.graph.rootList(pandora.crontrigger.CronTriggerTask.class);
		shutdownTriggerTaskList = this.graph.rootList(pandora.shutdowntrigger.ShutdownTriggerTask.class);
		directorySentinelTaskList = this.graph.rootList(pandora.directorysentinel.DirectorySentinelTask.class);
		processSentinelTaskList = this.graph.rootList(pandora.processsentinel.ProcessSentinelTask.class);
		jMSQueueSentinelTaskList = this.graph.rootList(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
		jMSTopicSentinelTaskList = this.graph.rootList(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
		channelList = this.graph.rootList(pandora.Channel.class);
		queueList = this.graph.rootList(pandora.Queue.class);
		topicList = this.graph.rootList(pandora.Topic.class);
		serviceList = this.graph.rootList(pandora.Service.class);
		rESTServiceList = this.graph.rootList(pandora.rest.RESTService.class);
		jMXServiceList = this.graph.rootList(pandora.jmx.JMXService.class);
		jMSServiceList = this.graph.rootList(pandora.jms.JMSService.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		if (node.is("TypeData")) this.typeDataList.add(node.as(pandora.type.TypeData.class));
		if (node.is("RealData")) this.realDataList.add(node.as(pandora.real.RealData.class));
		if (node.is("IntegerData")) this.integerDataList.add(node.as(pandora.integer.IntegerData.class));
		if (node.is("BoolData")) this.boolDataList.add(node.as(pandora.bool.BoolData.class));
		if (node.is("TextData")) this.textDataList.add(node.as(pandora.text.TextData.class));
		if (node.is("DateData")) this.dateDataList.add(node.as(pandora.date.DateData.class));
		if (node.is("DateTimeData")) this.dateTimeDataList.add(node.as(pandora.datetime.DateTimeData.class));
		if (node.is("FileData")) this.fileDataList.add(node.as(pandora.file.FileData.class));
		if (node.is("ObjectData")) this.objectDataList.add(node.as(pandora.object.ObjectData.class));
		if (node.is("ListData")) this.listDataList.add(node.as(pandora.list.ListData.class));
		if (node.is("Format")) this.formatList.add(node.as(pandora.Format.class));
		if (node.is("Task")) this.taskList.add(node.as(pandora.Task.class));
		if (node.is("ScheduledTask")) this.scheduledTaskList.add(node.as(pandora.scheduled.ScheduledTask.class));
		if (node.is("BootTriggerTask")) this.bootTriggerTaskList.add(node.as(pandora.boottrigger.BootTriggerTask.class));
		if (node.is("TimeoutTriggerTask")) this.timeoutTriggerTaskList.add(node.as(pandora.timeouttrigger.TimeoutTriggerTask.class));
		if (node.is("PeriodicTriggerTask")) this.periodicTriggerTaskList.add(node.as(pandora.periodictrigger.PeriodicTriggerTask.class));
		if (node.is("CronTriggerTask")) this.cronTriggerTaskList.add(node.as(pandora.crontrigger.CronTriggerTask.class));
		if (node.is("ShutdownTriggerTask")) this.shutdownTriggerTaskList.add(node.as(pandora.shutdowntrigger.ShutdownTriggerTask.class));
		if (node.is("DirectorySentinelTask")) this.directorySentinelTaskList.add(node.as(pandora.directorysentinel.DirectorySentinelTask.class));
		if (node.is("ProcessSentinelTask")) this.processSentinelTaskList.add(node.as(pandora.processsentinel.ProcessSentinelTask.class));
		if (node.is("JMSQueueSentinelTask")) this.jMSQueueSentinelTaskList.add(node.as(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class));
		if (node.is("JMSTopicSentinelTask")) this.jMSTopicSentinelTaskList.add(node.as(pandora.jmstopicsentinel.JMSTopicSentinelTask.class));
		if (node.is("Channel")) this.channelList.add(node.as(pandora.Channel.class));
		if (node.is("Queue")) this.queueList.add(node.as(pandora.Queue.class));
		if (node.is("Topic")) this.topicList.add(node.as(pandora.Topic.class));
		if (node.is("Service")) this.serviceList.add(node.as(pandora.Service.class));
		if (node.is("RESTService")) this.rESTServiceList.add(node.as(pandora.rest.RESTService.class));
		if (node.is("JMXService")) this.jMXServiceList.add(node.as(pandora.jmx.JMXService.class));
		if (node.is("JMSService")) this.jMSServiceList.add(node.as(pandora.jms.JMSService.class));
	}

	@Override
	protected void removeNode(tara.magritte.Node node) {
		if (node.is("TypeData")) this.typeDataList.remove(node.as(pandora.type.TypeData.class));
		if (node.is("RealData")) this.realDataList.remove(node.as(pandora.real.RealData.class));
		if (node.is("IntegerData")) this.integerDataList.remove(node.as(pandora.integer.IntegerData.class));
		if (node.is("BoolData")) this.boolDataList.remove(node.as(pandora.bool.BoolData.class));
		if (node.is("TextData")) this.textDataList.remove(node.as(pandora.text.TextData.class));
		if (node.is("DateData")) this.dateDataList.remove(node.as(pandora.date.DateData.class));
		if (node.is("DateTimeData")) this.dateTimeDataList.remove(node.as(pandora.datetime.DateTimeData.class));
		if (node.is("FileData")) this.fileDataList.remove(node.as(pandora.file.FileData.class));
		if (node.is("ObjectData")) this.objectDataList.remove(node.as(pandora.object.ObjectData.class));
		if (node.is("ListData")) this.listDataList.remove(node.as(pandora.list.ListData.class));
		if (node.is("Format")) this.formatList.remove(node.as(pandora.Format.class));
		if (node.is("Task")) this.taskList.remove(node.as(pandora.Task.class));
		if (node.is("ScheduledTask")) this.scheduledTaskList.remove(node.as(pandora.scheduled.ScheduledTask.class));
		if (node.is("BootTriggerTask")) this.bootTriggerTaskList.remove(node.as(pandora.boottrigger.BootTriggerTask.class));
		if (node.is("TimeoutTriggerTask")) this.timeoutTriggerTaskList.remove(node.as(pandora.timeouttrigger.TimeoutTriggerTask.class));
		if (node.is("PeriodicTriggerTask")) this.periodicTriggerTaskList.remove(node.as(pandora.periodictrigger.PeriodicTriggerTask.class));
		if (node.is("CronTriggerTask")) this.cronTriggerTaskList.remove(node.as(pandora.crontrigger.CronTriggerTask.class));
		if (node.is("ShutdownTriggerTask")) this.shutdownTriggerTaskList.remove(node.as(pandora.shutdowntrigger.ShutdownTriggerTask.class));
		if (node.is("DirectorySentinelTask")) this.directorySentinelTaskList.remove(node.as(pandora.directorysentinel.DirectorySentinelTask.class));
		if (node.is("ProcessSentinelTask")) this.processSentinelTaskList.remove(node.as(pandora.processsentinel.ProcessSentinelTask.class));
		if (node.is("JMSQueueSentinelTask")) this.jMSQueueSentinelTaskList.remove(node.as(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class));
		if (node.is("JMSTopicSentinelTask")) this.jMSTopicSentinelTaskList.remove(node.as(pandora.jmstopicsentinel.JMSTopicSentinelTask.class));
		if (node.is("Channel")) this.channelList.remove(node.as(pandora.Channel.class));
		if (node.is("Queue")) this.queueList.remove(node.as(pandora.Queue.class));
		if (node.is("Topic")) this.topicList.remove(node.as(pandora.Topic.class));
		if (node.is("Service")) this.serviceList.remove(node.as(pandora.Service.class));
		if (node.is("RESTService")) this.rESTServiceList.remove(node.as(pandora.rest.RESTService.class));
		if (node.is("JMXService")) this.jMXServiceList.remove(node.as(pandora.jmx.JMXService.class));
		if (node.is("JMSService")) this.jMSServiceList.remove(node.as(pandora.jms.JMSService.class));
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

	public List<pandora.type.TypeData> typeDataList() {
	    return typeDataList;
	}

	public List<pandora.real.RealData> realDataList() {
	    return realDataList;
	}

	public List<pandora.integer.IntegerData> integerDataList() {
	    return integerDataList;
	}

	public List<pandora.bool.BoolData> boolDataList() {
	    return boolDataList;
	}

	public List<pandora.text.TextData> textDataList() {
	    return textDataList;
	}

	public List<pandora.date.DateData> dateDataList() {
	    return dateDataList;
	}

	public List<pandora.datetime.DateTimeData> dateTimeDataList() {
	    return dateTimeDataList;
	}

	public List<pandora.file.FileData> fileDataList() {
	    return fileDataList;
	}

	public List<pandora.object.ObjectData> objectDataList() {
	    return objectDataList;
	}

	public List<pandora.list.ListData> listDataList() {
	    return listDataList;
	}

	public List<pandora.Format> formatList() {
	    return formatList;
	}

	public List<pandora.Task> taskList() {
	    return taskList;
	}

	public List<pandora.scheduled.ScheduledTask> scheduledTaskList() {
	    return scheduledTaskList;
	}

	public List<pandora.boottrigger.BootTriggerTask> bootTriggerTaskList() {
	    return bootTriggerTaskList;
	}

	public List<pandora.timeouttrigger.TimeoutTriggerTask> timeoutTriggerTaskList() {
	    return timeoutTriggerTaskList;
	}

	public List<pandora.periodictrigger.PeriodicTriggerTask> periodicTriggerTaskList() {
	    return periodicTriggerTaskList;
	}

	public List<pandora.crontrigger.CronTriggerTask> cronTriggerTaskList() {
	    return cronTriggerTaskList;
	}

	public List<pandora.shutdowntrigger.ShutdownTriggerTask> shutdownTriggerTaskList() {
	    return shutdownTriggerTaskList;
	}

	public List<pandora.directorysentinel.DirectorySentinelTask> directorySentinelTaskList() {
	    return directorySentinelTaskList;
	}

	public List<pandora.processsentinel.ProcessSentinelTask> processSentinelTaskList() {
	    return processSentinelTaskList;
	}

	public List<pandora.jmsqueuesentinel.JMSQueueSentinelTask> jMSQueueSentinelTaskList() {
	    return jMSQueueSentinelTaskList;
	}

	public List<pandora.jmstopicsentinel.JMSTopicSentinelTask> jMSTopicSentinelTaskList() {
	    return jMSTopicSentinelTaskList;
	}

	public List<pandora.Channel> channelList() {
	    return channelList;
	}

	public List<pandora.Queue> queueList() {
	    return queueList;
	}

	public List<pandora.Topic> topicList() {
	    return topicList;
	}

	public List<pandora.Service> serviceList() {
	    return serviceList;
	}

	public List<pandora.rest.RESTService> rESTServiceList() {
	    return rESTServiceList;
	}

	public List<pandora.jmx.JMXService> jMXServiceList() {
	    return jMXServiceList;
	}

	public List<pandora.jms.JMSService> jMSServiceList() {
	    return jMSServiceList;
	}

	public List<pandora.type.TypeData> typeDataList(java.util.function.Predicate<pandora.type.TypeData> predicate) {
	    return typeDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.type.TypeData typeData(int index) {
		return typeDataList.get(index);
	}

	public List<pandora.real.RealData> realDataList(java.util.function.Predicate<pandora.real.RealData> predicate) {
	    return realDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.real.RealData realData(int index) {
		return realDataList.get(index);
	}

	public List<pandora.integer.IntegerData> integerDataList(java.util.function.Predicate<pandora.integer.IntegerData> predicate) {
	    return integerDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.integer.IntegerData integerData(int index) {
		return integerDataList.get(index);
	}

	public List<pandora.bool.BoolData> boolDataList(java.util.function.Predicate<pandora.bool.BoolData> predicate) {
	    return boolDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.bool.BoolData boolData(int index) {
		return boolDataList.get(index);
	}

	public List<pandora.text.TextData> textDataList(java.util.function.Predicate<pandora.text.TextData> predicate) {
	    return textDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.text.TextData textData(int index) {
		return textDataList.get(index);
	}

	public List<pandora.date.DateData> dateDataList(java.util.function.Predicate<pandora.date.DateData> predicate) {
	    return dateDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.date.DateData dateData(int index) {
		return dateDataList.get(index);
	}

	public List<pandora.datetime.DateTimeData> dateTimeDataList(java.util.function.Predicate<pandora.datetime.DateTimeData> predicate) {
	    return dateTimeDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.datetime.DateTimeData dateTimeData(int index) {
		return dateTimeDataList.get(index);
	}

	public List<pandora.file.FileData> fileDataList(java.util.function.Predicate<pandora.file.FileData> predicate) {
	    return fileDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.file.FileData fileData(int index) {
		return fileDataList.get(index);
	}

	public List<pandora.object.ObjectData> objectDataList(java.util.function.Predicate<pandora.object.ObjectData> predicate) {
	    return objectDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.object.ObjectData objectData(int index) {
		return objectDataList.get(index);
	}

	public List<pandora.list.ListData> listDataList(java.util.function.Predicate<pandora.list.ListData> predicate) {
	    return listDataList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.list.ListData listData(int index) {
		return listDataList.get(index);
	}

	public List<pandora.Format> formatList(java.util.function.Predicate<pandora.Format> predicate) {
	    return formatList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Format format(int index) {
		return formatList.get(index);
	}

	public List<pandora.Task> taskList(java.util.function.Predicate<pandora.Task> predicate) {
	    return taskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Task task(int index) {
		return taskList.get(index);
	}

	public List<pandora.scheduled.ScheduledTask> scheduledTaskList(java.util.function.Predicate<pandora.scheduled.ScheduledTask> predicate) {
	    return scheduledTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.scheduled.ScheduledTask scheduledTask(int index) {
		return scheduledTaskList.get(index);
	}

	public List<pandora.boottrigger.BootTriggerTask> bootTriggerTaskList(java.util.function.Predicate<pandora.boottrigger.BootTriggerTask> predicate) {
	    return bootTriggerTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.boottrigger.BootTriggerTask bootTriggerTask(int index) {
		return bootTriggerTaskList.get(index);
	}

	public List<pandora.timeouttrigger.TimeoutTriggerTask> timeoutTriggerTaskList(java.util.function.Predicate<pandora.timeouttrigger.TimeoutTriggerTask> predicate) {
	    return timeoutTriggerTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.timeouttrigger.TimeoutTriggerTask timeoutTriggerTask(int index) {
		return timeoutTriggerTaskList.get(index);
	}

	public List<pandora.periodictrigger.PeriodicTriggerTask> periodicTriggerTaskList(java.util.function.Predicate<pandora.periodictrigger.PeriodicTriggerTask> predicate) {
	    return periodicTriggerTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.periodictrigger.PeriodicTriggerTask periodicTriggerTask(int index) {
		return periodicTriggerTaskList.get(index);
	}

	public List<pandora.crontrigger.CronTriggerTask> cronTriggerTaskList(java.util.function.Predicate<pandora.crontrigger.CronTriggerTask> predicate) {
	    return cronTriggerTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.crontrigger.CronTriggerTask cronTriggerTask(int index) {
		return cronTriggerTaskList.get(index);
	}

	public List<pandora.shutdowntrigger.ShutdownTriggerTask> shutdownTriggerTaskList(java.util.function.Predicate<pandora.shutdowntrigger.ShutdownTriggerTask> predicate) {
	    return shutdownTriggerTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.shutdowntrigger.ShutdownTriggerTask shutdownTriggerTask(int index) {
		return shutdownTriggerTaskList.get(index);
	}

	public List<pandora.directorysentinel.DirectorySentinelTask> directorySentinelTaskList(java.util.function.Predicate<pandora.directorysentinel.DirectorySentinelTask> predicate) {
	    return directorySentinelTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.directorysentinel.DirectorySentinelTask directorySentinelTask(int index) {
		return directorySentinelTaskList.get(index);
	}

	public List<pandora.processsentinel.ProcessSentinelTask> processSentinelTaskList(java.util.function.Predicate<pandora.processsentinel.ProcessSentinelTask> predicate) {
	    return processSentinelTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.processsentinel.ProcessSentinelTask processSentinelTask(int index) {
		return processSentinelTaskList.get(index);
	}

	public List<pandora.jmsqueuesentinel.JMSQueueSentinelTask> jMSQueueSentinelTaskList(java.util.function.Predicate<pandora.jmsqueuesentinel.JMSQueueSentinelTask> predicate) {
	    return jMSQueueSentinelTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.jmsqueuesentinel.JMSQueueSentinelTask jMSQueueSentinelTask(int index) {
		return jMSQueueSentinelTaskList.get(index);
	}

	public List<pandora.jmstopicsentinel.JMSTopicSentinelTask> jMSTopicSentinelTaskList(java.util.function.Predicate<pandora.jmstopicsentinel.JMSTopicSentinelTask> predicate) {
	    return jMSTopicSentinelTaskList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.jmstopicsentinel.JMSTopicSentinelTask jMSTopicSentinelTask(int index) {
		return jMSTopicSentinelTaskList.get(index);
	}

	public List<pandora.Channel> channelList(java.util.function.Predicate<pandora.Channel> predicate) {
	    return channelList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Channel channel(int index) {
		return channelList.get(index);
	}

	public List<pandora.Queue> queueList(java.util.function.Predicate<pandora.Queue> predicate) {
	    return queueList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Queue queue(int index) {
		return queueList.get(index);
	}

	public List<pandora.Topic> topicList(java.util.function.Predicate<pandora.Topic> predicate) {
	    return topicList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Topic topic(int index) {
		return topicList.get(index);
	}

	public List<pandora.Service> serviceList(java.util.function.Predicate<pandora.Service> predicate) {
	    return serviceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Service service(int index) {
		return serviceList.get(index);
	}

	public List<pandora.rest.RESTService> rESTServiceList(java.util.function.Predicate<pandora.rest.RESTService> predicate) {
	    return rESTServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.rest.RESTService rESTService(int index) {
		return rESTServiceList.get(index);
	}

	public List<pandora.jmx.JMXService> jMXServiceList(java.util.function.Predicate<pandora.jmx.JMXService> predicate) {
	    return jMXServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.jmx.JMXService jMXService(int index) {
		return jMXServiceList.get(index);
	}

	public List<pandora.jms.JMSService> jMSServiceList(java.util.function.Predicate<pandora.jms.JMSService> predicate) {
	    return jMSServiceList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.jms.JMSService jMSService(int index) {
		return jMSServiceList.get(index);
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

		public pandora.type.TypeData typeData(tara.magritte.Expression<java.lang.String> type) {
			pandora.type.TypeData newElement = GraphWrapper.this.graph.createRoot(pandora.type.TypeData.class, namespace, name).as(pandora.type.TypeData.class);
			newElement.node().set(newElement, "type", java.util.Collections.singletonList(type));
			return newElement;
		}

		public pandora.real.RealData realData() {
			pandora.real.RealData newElement = GraphWrapper.this.graph.createRoot(pandora.real.RealData.class, namespace, name).as(pandora.real.RealData.class);
			
			return newElement;
		}

		public pandora.integer.IntegerData integerData() {
			pandora.integer.IntegerData newElement = GraphWrapper.this.graph.createRoot(pandora.integer.IntegerData.class, namespace, name).as(pandora.integer.IntegerData.class);
			
			return newElement;
		}

		public pandora.bool.BoolData boolData() {
			pandora.bool.BoolData newElement = GraphWrapper.this.graph.createRoot(pandora.bool.BoolData.class, namespace, name).as(pandora.bool.BoolData.class);
			
			return newElement;
		}

		public pandora.text.TextData textData() {
			pandora.text.TextData newElement = GraphWrapper.this.graph.createRoot(pandora.text.TextData.class, namespace, name).as(pandora.text.TextData.class);
			
			return newElement;
		}

		public pandora.date.DateData dateData() {
			pandora.date.DateData newElement = GraphWrapper.this.graph.createRoot(pandora.date.DateData.class, namespace, name).as(pandora.date.DateData.class);
			
			return newElement;
		}

		public pandora.datetime.DateTimeData dateTimeData() {
			pandora.datetime.DateTimeData newElement = GraphWrapper.this.graph.createRoot(pandora.datetime.DateTimeData.class, namespace, name).as(pandora.datetime.DateTimeData.class);
			
			return newElement;
		}

		public pandora.file.FileData fileData() {
			pandora.file.FileData newElement = GraphWrapper.this.graph.createRoot(pandora.file.FileData.class, namespace, name).as(pandora.file.FileData.class);
			
			return newElement;
		}

		public pandora.object.ObjectData objectData(pandora.Format format) {
			pandora.object.ObjectData newElement = GraphWrapper.this.graph.createRoot(pandora.object.ObjectData.class, namespace, name).as(pandora.object.ObjectData.class);
			newElement.node().set(newElement, "format", java.util.Collections.singletonList(format));
			return newElement;
		}

		public pandora.list.ListData listData() {
			pandora.list.ListData newElement = GraphWrapper.this.graph.createRoot(pandora.list.ListData.class, namespace, name).as(pandora.list.ListData.class);
			
			return newElement;
		}

		public pandora.Format format() {
			pandora.Format newElement = GraphWrapper.this.graph.createRoot(pandora.Format.class, namespace, name).as(pandora.Format.class);
			
			return newElement;
		}

		public pandora.Task task() {
			pandora.Task newElement = GraphWrapper.this.graph.createRoot(pandora.Task.class, namespace, name).as(pandora.Task.class);
			
			return newElement;
		}

		public pandora.scheduled.ScheduledTask scheduledTask() {
			pandora.scheduled.ScheduledTask newElement = GraphWrapper.this.graph.createRoot(pandora.scheduled.ScheduledTask.class, namespace, name).as(pandora.scheduled.ScheduledTask.class);
			
			return newElement;
		}

		public pandora.boottrigger.BootTriggerTask bootTriggerTask() {
			pandora.boottrigger.BootTriggerTask newElement = GraphWrapper.this.graph.createRoot(pandora.boottrigger.BootTriggerTask.class, namespace, name).as(pandora.boottrigger.BootTriggerTask.class);
			
			return newElement;
		}

		public pandora.timeouttrigger.TimeoutTriggerTask timeoutTriggerTask(int delay) {
			pandora.timeouttrigger.TimeoutTriggerTask newElement = GraphWrapper.this.graph.createRoot(pandora.timeouttrigger.TimeoutTriggerTask.class, namespace, name).as(pandora.timeouttrigger.TimeoutTriggerTask.class);
			newElement.node().set(newElement, "delay", java.util.Collections.singletonList(delay));
			return newElement;
		}

		public pandora.periodictrigger.PeriodicTriggerTask periodicTriggerTask(int delay, int interval) {
			pandora.periodictrigger.PeriodicTriggerTask newElement = GraphWrapper.this.graph.createRoot(pandora.periodictrigger.PeriodicTriggerTask.class, namespace, name).as(pandora.periodictrigger.PeriodicTriggerTask.class);
			newElement.node().set(newElement, "delay", java.util.Collections.singletonList(delay));
			newElement.node().set(newElement, "interval", java.util.Collections.singletonList(interval));
			return newElement;
		}

		public pandora.crontrigger.CronTriggerTask cronTriggerTask(java.lang.String pattern) {
			pandora.crontrigger.CronTriggerTask newElement = GraphWrapper.this.graph.createRoot(pandora.crontrigger.CronTriggerTask.class, namespace, name).as(pandora.crontrigger.CronTriggerTask.class);
			newElement.node().set(newElement, "pattern", java.util.Collections.singletonList(pattern));
			return newElement;
		}

		public pandora.shutdowntrigger.ShutdownTriggerTask shutdownTriggerTask() {
			pandora.shutdowntrigger.ShutdownTriggerTask newElement = GraphWrapper.this.graph.createRoot(pandora.shutdowntrigger.ShutdownTriggerTask.class, namespace, name).as(pandora.shutdowntrigger.ShutdownTriggerTask.class);
			
			return newElement;
		}

		public pandora.directorysentinel.DirectorySentinelTask directorySentinelTask(java.net.URL directory, List<pandora.directorysentinel.DirectorySentinelTask.Events> events, pandora.functions.DirectoryChecker check) {
			pandora.directorysentinel.DirectorySentinelTask newElement = GraphWrapper.this.graph.createRoot(pandora.directorysentinel.DirectorySentinelTask.class, namespace, name).as(pandora.directorysentinel.DirectorySentinelTask.class);
			newElement.node().set(newElement, "directory", java.util.Collections.singletonList(directory));
			newElement.node().set(newElement, "events", events);
			newElement.node().set(newElement, "check", java.util.Collections.singletonList(check));
			return newElement;
		}

		public pandora.processsentinel.ProcessSentinelTask processSentinelTask(java.lang.String processName, List<pandora.processsentinel.ProcessSentinelTask.Events> events, pandora.functions.ProcessChecker check) {
			pandora.processsentinel.ProcessSentinelTask newElement = GraphWrapper.this.graph.createRoot(pandora.processsentinel.ProcessSentinelTask.class, namespace, name).as(pandora.processsentinel.ProcessSentinelTask.class);
			newElement.node().set(newElement, "processName", java.util.Collections.singletonList(processName));
			newElement.node().set(newElement, "events", events);
			newElement.node().set(newElement, "check", java.util.Collections.singletonList(check));
			return newElement;
		}

		public pandora.jmsqueuesentinel.JMSQueueSentinelTask jMSQueueSentinelTask(java.lang.String queue) {
			pandora.jmsqueuesentinel.JMSQueueSentinelTask newElement = GraphWrapper.this.graph.createRoot(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class, namespace, name).as(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
			newElement.node().set(newElement, "queue", java.util.Collections.singletonList(queue));
			return newElement;
		}

		public pandora.jmstopicsentinel.JMSTopicSentinelTask jMSTopicSentinelTask(java.lang.String topic) {
			pandora.jmstopicsentinel.JMSTopicSentinelTask newElement = GraphWrapper.this.graph.createRoot(pandora.jmstopicsentinel.JMSTopicSentinelTask.class, namespace, name).as(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
			newElement.node().set(newElement, "topic", java.util.Collections.singletonList(topic));
			return newElement;
		}

		public pandora.Channel channel(java.lang.String id) {
			pandora.Channel newElement = GraphWrapper.this.graph.createRoot(pandora.Channel.class, namespace, name).as(pandora.Channel.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id));
			return newElement;
		}

		public pandora.Queue queue(java.lang.String id) {
			pandora.Queue newElement = GraphWrapper.this.graph.createRoot(pandora.Queue.class, namespace, name).as(pandora.Queue.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id));
			return newElement;
		}

		public pandora.Topic topic(java.lang.String id) {
			pandora.Topic newElement = GraphWrapper.this.graph.createRoot(pandora.Topic.class, namespace, name).as(pandora.Topic.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id));
			return newElement;
		}

		public pandora.Service service() {
			pandora.Service newElement = GraphWrapper.this.graph.createRoot(pandora.Service.class, namespace, name).as(pandora.Service.class);
			
			return newElement;
		}

		public pandora.rest.RESTService rESTService(java.lang.String title, java.lang.String path) {
			pandora.rest.RESTService newElement = GraphWrapper.this.graph.createRoot(pandora.rest.RESTService.class, namespace, name).as(pandora.rest.RESTService.class);
			newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.node().set(newElement, "path", java.util.Collections.singletonList(path));
			return newElement;
		}

		public pandora.jmx.JMXService jMXService() {
			pandora.jmx.JMXService newElement = GraphWrapper.this.graph.createRoot(pandora.jmx.JMXService.class, namespace, name).as(pandora.jmx.JMXService.class);
			
			return newElement;
		}

		public pandora.jms.JMSService jMSService() {
			pandora.jms.JMSService newElement = GraphWrapper.this.graph.createRoot(pandora.jms.JMSService.class, namespace, name).as(pandora.jms.JMSService.class);
			
			return newElement;
		}

	}


}