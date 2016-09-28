package pandora;

import pandora.*;
import java.io.File;

import java.util.*;

public class Task extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	

	public Task(tara.magritte.Node node) {
		super(node);
	}

	public pandora.scheduled.ScheduledTask asScheduled() {
		tara.magritte.Layer as = this.as(pandora.scheduled.ScheduledTask.class);
		return as != null ? (pandora.scheduled.ScheduledTask) as : addFacet(pandora.scheduled.ScheduledTask.class);
	}

	public boolean isScheduled() {
		return is(pandora.scheduled.ScheduledTask.class);
	}

	public pandora.crontrigger.CronTriggerTask asCronTrigger() {
		return this.as(pandora.crontrigger.CronTriggerTask.class);
	}

	public pandora.crontrigger.CronTriggerTask asCronTrigger(java.lang.String pattern) {
		pandora.crontrigger.CronTriggerTask newElement = addFacet(pandora.crontrigger.CronTriggerTask.class);
		newElement.node().set(newElement, "pattern", java.util.Collections.singletonList(pattern)); 
	    return newElement;
	}

	public boolean isCronTrigger() {
		return is(pandora.crontrigger.CronTriggerTask.class);
	}

	public void removeCronTrigger() {
		this.removeFacet(pandora.crontrigger.CronTriggerTask.class);
	}

	public pandora.timeouttrigger.TimeoutTriggerTask asTimeoutTrigger() {
		return this.as(pandora.timeouttrigger.TimeoutTriggerTask.class);
	}

	public pandora.timeouttrigger.TimeoutTriggerTask asTimeoutTrigger(int delay) {
		pandora.timeouttrigger.TimeoutTriggerTask newElement = addFacet(pandora.timeouttrigger.TimeoutTriggerTask.class);
		newElement.node().set(newElement, "delay", java.util.Collections.singletonList(delay)); 
	    return newElement;
	}

	public boolean isTimeoutTrigger() {
		return is(pandora.timeouttrigger.TimeoutTriggerTask.class);
	}

	public void removeTimeoutTrigger() {
		this.removeFacet(pandora.timeouttrigger.TimeoutTriggerTask.class);
	}

	public pandora.jmsqueuesentinel.JMSQueueSentinelTask asJMSQueueSentinel() {
		return this.as(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
	}

	public pandora.jmsqueuesentinel.JMSQueueSentinelTask asJMSQueueSentinel(java.lang.String queue) {
		pandora.jmsqueuesentinel.JMSQueueSentinelTask newElement = addFacet(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
		newElement.node().set(newElement, "queue", java.util.Collections.singletonList(queue)); 
	    return newElement;
	}

	public boolean isJMSQueueSentinel() {
		return is(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
	}

	public void removeJMSQueueSentinel() {
		this.removeFacet(pandora.jmsqueuesentinel.JMSQueueSentinelTask.class);
	}

	public pandora.boottrigger.BootTriggerTask asBootTrigger() {
		tara.magritte.Layer as = this.as(pandora.boottrigger.BootTriggerTask.class);
		return as != null ? (pandora.boottrigger.BootTriggerTask) as : addFacet(pandora.boottrigger.BootTriggerTask.class);
	}

	public boolean isBootTrigger() {
		return is(pandora.boottrigger.BootTriggerTask.class);
	}

	public pandora.directorysentinel.DirectorySentinelTask asDirectorySentinel() {
		return this.as(pandora.directorysentinel.DirectorySentinelTask.class);
	}

	public pandora.directorysentinel.DirectorySentinelTask asDirectorySentinel(java.net.URL directory, List<pandora.directorysentinel.DirectorySentinelTask.Events> events, pandora.functions.DirectoryChecker check) {
		pandora.directorysentinel.DirectorySentinelTask newElement = addFacet(pandora.directorysentinel.DirectorySentinelTask.class);
		newElement.node().set(newElement, "directory", java.util.Collections.singletonList(directory));
		newElement.node().set(newElement, "events", events);
		newElement.node().set(newElement, "check", java.util.Collections.singletonList(check)); 
	    return newElement;
	}

	public boolean isDirectorySentinel() {
		return is(pandora.directorysentinel.DirectorySentinelTask.class);
	}

	public void removeDirectorySentinel() {
		this.removeFacet(pandora.directorysentinel.DirectorySentinelTask.class);
	}

	public pandora.jmstopicsentinel.JMSTopicSentinelTask asJMSTopicSentinel() {
		return this.as(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
	}

	public pandora.jmstopicsentinel.JMSTopicSentinelTask asJMSTopicSentinel(java.lang.String topic) {
		pandora.jmstopicsentinel.JMSTopicSentinelTask newElement = addFacet(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
		newElement.node().set(newElement, "topic", java.util.Collections.singletonList(topic)); 
	    return newElement;
	}

	public boolean isJMSTopicSentinel() {
		return is(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
	}

	public void removeJMSTopicSentinel() {
		this.removeFacet(pandora.jmstopicsentinel.JMSTopicSentinelTask.class);
	}

	public pandora.shutdowntrigger.ShutdownTriggerTask asShutdownTrigger() {
		tara.magritte.Layer as = this.as(pandora.shutdowntrigger.ShutdownTriggerTask.class);
		return as != null ? (pandora.shutdowntrigger.ShutdownTriggerTask) as : addFacet(pandora.shutdowntrigger.ShutdownTriggerTask.class);
	}

	public boolean isShutdownTrigger() {
		return is(pandora.shutdowntrigger.ShutdownTriggerTask.class);
	}

	public pandora.processsentinel.ProcessSentinelTask asProcessSentinel() {
		return this.as(pandora.processsentinel.ProcessSentinelTask.class);
	}

	public pandora.processsentinel.ProcessSentinelTask asProcessSentinel(java.lang.String processName, List<pandora.processsentinel.ProcessSentinelTask.Events> events, pandora.functions.ProcessChecker check) {
		pandora.processsentinel.ProcessSentinelTask newElement = addFacet(pandora.processsentinel.ProcessSentinelTask.class);
		newElement.node().set(newElement, "processName", java.util.Collections.singletonList(processName));
		newElement.node().set(newElement, "events", events);
		newElement.node().set(newElement, "check", java.util.Collections.singletonList(check)); 
	    return newElement;
	}

	public boolean isProcessSentinel() {
		return is(pandora.processsentinel.ProcessSentinelTask.class);
	}

	public void removeProcessSentinel() {
		this.removeFacet(pandora.processsentinel.ProcessSentinelTask.class);
	}

	public pandora.periodictrigger.PeriodicTriggerTask asPeriodicTrigger() {
		return this.as(pandora.periodictrigger.PeriodicTriggerTask.class);
	}

	public pandora.periodictrigger.PeriodicTriggerTask asPeriodicTrigger(int delay, int interval) {
		pandora.periodictrigger.PeriodicTriggerTask newElement = addFacet(pandora.periodictrigger.PeriodicTriggerTask.class);
		newElement.node().set(newElement, "delay", java.util.Collections.singletonList(delay));
		newElement.node().set(newElement, "interval", java.util.Collections.singletonList(interval)); 
	    return newElement;
	}

	public boolean isPeriodicTrigger() {
		return is(pandora.periodictrigger.PeriodicTriggerTask.class);
	}

	public void removePeriodicTrigger() {
		this.removeFacet(pandora.periodictrigger.PeriodicTriggerTask.class);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Task.class);
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
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
