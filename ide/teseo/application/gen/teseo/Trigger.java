package teseo;

import teseo.*;
import java.io.File;

import java.util.*;

public class Trigger extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	

	public Trigger(tara.magritte.Node node) {
		super(node);
	}

	public teseo.scheduled.ScheduledTrigger asScheduled() {
		tara.magritte.Layer as = this.as(teseo.scheduled.ScheduledTrigger.class);
		return as != null ? (teseo.scheduled.ScheduledTrigger) as : null;
	}

	public boolean isScheduled() {
		return is(teseo.scheduled.ScheduledTrigger.class);
	}

	public teseo.jmstopicwatcher.JMSTopicWatcherTrigger asJMSTopicWatcher() {
		return this.as(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
	}

	public teseo.jmstopicwatcher.JMSTopicWatcherTrigger asJMSTopicWatcher(java.lang.String topic) {
		teseo.jmstopicwatcher.JMSTopicWatcherTrigger newElement = addFacet(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
		newElement.node().set(newElement, "topic", java.util.Collections.singletonList(topic)); 
	    return newElement;
	}

	public boolean isJMSTopicWatcher() {
		return is(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
	}

	public void removeJMSTopicWatcher() {
		this.removeFacet(teseo.jmstopicwatcher.JMSTopicWatcherTrigger.class);
	}

	public teseo.directorywatcher.DirectoryWatcherTrigger asDirectoryWatcher() {
		return this.as(teseo.directorywatcher.DirectoryWatcherTrigger.class);
	}

	public teseo.directorywatcher.DirectoryWatcherTrigger asDirectoryWatcher(java.net.URL directory, List<teseo.directorywatcher.DirectoryWatcherTrigger.Events> events, teseo.functions.DirectoryChecker check) {
		teseo.directorywatcher.DirectoryWatcherTrigger newElement = addFacet(teseo.directorywatcher.DirectoryWatcherTrigger.class);
		newElement.node().set(newElement, "directory", java.util.Collections.singletonList(directory));
		newElement.node().set(newElement, "events", events);
		newElement.node().set(newElement, "check", java.util.Collections.singletonList(check)); 
	    return newElement;
	}

	public boolean isDirectoryWatcher() {
		return is(teseo.directorywatcher.DirectoryWatcherTrigger.class);
	}

	public void removeDirectoryWatcher() {
		this.removeFacet(teseo.directorywatcher.DirectoryWatcherTrigger.class);
	}

	public teseo.jmsrequest.JMSRequestTrigger asJMSRequest() {
		tara.magritte.Layer as = this.as(teseo.jmsrequest.JMSRequestTrigger.class);
		return as != null ? (teseo.jmsrequest.JMSRequestTrigger) as : addFacet(teseo.jmsrequest.JMSRequestTrigger.class);
	}

	public boolean isJMSRequest() {
		return is(teseo.jmsrequest.JMSRequestTrigger.class);
	}

	public teseo.boot.BootTrigger asBoot() {
		tara.magritte.Layer as = this.as(teseo.boot.BootTrigger.class);
		return as != null ? (teseo.boot.BootTrigger) as : addFacet(teseo.boot.BootTrigger.class);
	}

	public boolean isBoot() {
		return is(teseo.boot.BootTrigger.class);
	}

	public teseo.cron.CronTrigger asCron() {
		return this.as(teseo.cron.CronTrigger.class);
	}

	public teseo.cron.CronTrigger asCron(java.lang.String pattern) {
		teseo.cron.CronTrigger newElement = addFacet(teseo.cron.CronTrigger.class);
		newElement.node().set(newElement, "pattern", java.util.Collections.singletonList(pattern)); 
	    return newElement;
	}

	public boolean isCron() {
		return is(teseo.cron.CronTrigger.class);
	}

	public void removeCron() {
		this.removeFacet(teseo.cron.CronTrigger.class);
	}

	public teseo.processwatcher.ProcessWatcherTrigger asProcessWatcher() {
		return this.as(teseo.processwatcher.ProcessWatcherTrigger.class);
	}

	public teseo.processwatcher.ProcessWatcherTrigger asProcessWatcher(java.lang.String processName, List<teseo.processwatcher.ProcessWatcherTrigger.Events> events, teseo.functions.ProcessChecker check) {
		teseo.processwatcher.ProcessWatcherTrigger newElement = addFacet(teseo.processwatcher.ProcessWatcherTrigger.class);
		newElement.node().set(newElement, "processName", java.util.Collections.singletonList(processName));
		newElement.node().set(newElement, "events", events);
		newElement.node().set(newElement, "check", java.util.Collections.singletonList(check)); 
	    return newElement;
	}

	public boolean isProcessWatcher() {
		return is(teseo.processwatcher.ProcessWatcherTrigger.class);
	}

	public void removeProcessWatcher() {
		this.removeFacet(teseo.processwatcher.ProcessWatcherTrigger.class);
	}

	public teseo.jmsqueuewatcher.JMSQueueWatcherTrigger asJMSQueueWatcher() {
		return this.as(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
	}

	public teseo.jmsqueuewatcher.JMSQueueWatcherTrigger asJMSQueueWatcher(java.lang.String queue) {
		teseo.jmsqueuewatcher.JMSQueueWatcherTrigger newElement = addFacet(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
		newElement.node().set(newElement, "queue", java.util.Collections.singletonList(queue)); 
	    return newElement;
	}

	public boolean isJMSQueueWatcher() {
		return is(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
	}

	public void removeJMSQueueWatcher() {
		this.removeFacet(teseo.jmsqueuewatcher.JMSQueueWatcherTrigger.class);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Trigger.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create {
		protected final java.lang.String name;

		public Create(java.lang.String name) {
			this.name = name;
		}
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
