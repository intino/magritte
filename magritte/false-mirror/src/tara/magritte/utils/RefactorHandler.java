package tara.magritte.utils;

import tara.io.refactor.Refactors;

public class RefactorHandler {

	private Refactors engine;
	private Refactors domain;

	public RefactorHandler(Refactors engine, Refactors domain) {
		this.engine = engine;
		this.domain = domain;
	}

	public String last(String oldQn, int fromRefactorId) {
		String engineNewQn = last(engine, oldQn, fromRefactorId);
		String domainNewQn = last(domain, oldQn, fromRefactorId);
		return engineNewQn.equals(oldQn) ? domainNewQn : engineNewQn;
	}

	private String last(Refactors refactors, String oldQn, int fromRefactorId) {
		String[] result = {oldQn};
		refactors.subListById(fromRefactorId).forEach(r -> {
			if(r.oldQn.equals(result[0]))
				result[0] = r.newQn;
		});
		return result[0];
	}

	public int lastEngineRefactor(){
		return engine.size() - 1;
	}

	public int lastDomainRefactor(){
		return domain.size() - 1;
	}

}
