package tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public abstract class JobAction extends Morph {
    protected List<Job> jobList;

    public JobAction(Node node) {
        super(node);
    }

    public JobAction(Morph morph, Node node) {
        super(morph, node);
    }

    public List<Job> jobList() {
        return jobList;
    }

    public tafat.Job job(int index) {
        return jobList.get(index);
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("jobList", jobList);
        return map;
    }

    @Override
    protected void _add(Node component) {
    }

    @Override
    protected void _set(String name, Object object) {
        if (name.equalsIgnoreCase("jobList")) jobList = (List<Job>) object;
    }

}
