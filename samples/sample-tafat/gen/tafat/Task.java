package tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.time.LocalDateTime;
import java.util.*;


public class Task extends Morph {
    protected List<Days> days = new ArrayList<>();
    protected Start start;
    protected Finish finish;
    protected End end;
    protected Duration duration;
    protected List<Task> taskList = new ArrayList<>();
    protected List<JobAction> jobActionList = new ArrayList<>();

    public Task(Node node) {
        super(node);
    }

    public Task(Morph morph, Node node) {
        super(morph, node);
        set("days", ((Task) morph).days);
    }

    public List<Days> days() {
        return days;
    }

    public tafat.Task.Start start() {
        return start;
    }

    public tafat.Task.Finish finish() {
        return finish;
    }

    public tafat.Task.End end() {
        return end;
    }

    public tafat.Task.Duration duration() {
        return duration;
    }

    public List<Task> taskList() {
        return taskList;
    }

    public tafat.Task task(int index) {
        return taskList.get(index);
    }

    public List<tafat.JobAction> jobActionList() {
        return jobActionList;
    }

    public tafat.JobAction jobAction(int index) {
        return jobActionList.get(index);
    }

    @Override
    public List<Node> _components() {
        Set<Node> nodes = new LinkedHashSet<>();
        if (start != null) node.add(start.node());
        if (finish != null) node.add(finish.node());
        if (end != null) node.add(end.node());
        if (duration != null) node.add(duration.node());
        taskList.stream().forEach(c -> nodes.add(c.node()));
        jobActionList.stream().forEach(c -> nodes.add(c.node()));
        return new ArrayList<>(nodes);
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("days", days);
        return map;
    }

    @Override
    protected void add(Node component) {
        if (node.is("Task$Start")) start = component.morph(Start.class);
        if (node.is("Task$Finish")) finish = component.morph(Finish.class);
        if (node.is("Task$End")) end = component.morph(End.class);
        if (node.is("Task$duration")) duration = component.morph(Duration.class);
        if (node.is("Task")) taskList.add(component.morph(Task.class));
        if (node.is("JobAction")) jobActionList.add(component.morph(JobAction.class));
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("days")) days = (List<Days>) object;
    }

    public enum Days {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday;
    }

    public static class Start extends Morph {
        protected LocalDateTime time;
        protected double deviation;

        public Start(Node node) {
            super(node);
        }

        public Start(Morph morph, Node node) {
            super(morph, node);
        }

        public LocalDateTime time() {
            return time;
        }

        public void time(LocalDateTime value) {
            this.time = value;
        }

        public double deviation() {
            return deviation;
        }

        public void deviation(double value) {
            this.deviation = value;
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("time", time);
            map.put("deviation", deviation);
            return map;
        }

        @Override
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
            if (name.equalsIgnoreCase("time")) time = (LocalDateTime) object;
            if (name.equalsIgnoreCase("deviation")) deviation = (double) object;
        }
    }

    public static abstract class Finish extends Morph {
        public Finish(Node node) {
            super(node);
        }

        public Finish(Morph morph, Node node) {
            super(morph, node);
        }
    }

    public static class End extends tafat.Task.Finish {
        protected LocalDateTime time;
        protected double deviation;

        public End(Node node) {
            super(node);
        }

        public End(Morph morph, Node node) {
            super(morph, node);
        }


        public LocalDateTime time() {
            return time;
        }

        public void time(LocalDateTime value) {
            this.time = value;
        }

        public double deviation() {
            return deviation;
        }

        public void deviation(double value) {
            this.deviation = value;
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("time", time);
            map.put("deviation", deviation);
            return map;
        }

        @Override
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
            if (name.equalsIgnoreCase("time")) time = (LocalDateTime) object;
            if (name.equalsIgnoreCase("deviation")) deviation = (double) object;
        }
    }

    public static class Duration extends tafat.Task.Finish {
        protected double time;
        protected double deviation;

        public Duration(Node node) {
            super(node);
        }

        public Duration(Morph morph, Node node) {
            super(morph, node);
        }


        public double time() {
            return time;
        }

        public void time(double value) {
            this.time = value;
        }

        public double deviation() {
            return deviation;
        }

        public void deviation(double value) {
            this.deviation = value;
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("time", time);
            map.put("deviation", deviation);
            return map;
        }

        @Override
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
            if (name.equalsIgnoreCase("time")) time = (double) object;
            if (name.equalsIgnoreCase("deviation")) deviation = (double) object;
        }
    }
}
