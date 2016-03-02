package tara.magritte.utils;

import tara.io.refactor.Refactors;

public class RefactorHandler {

	private Refactors platform;
	private Refactors application;

	public RefactorHandler(Refactors platform, Refactors application) {
		this.platform = platform;
		this.application = application;
	}

	public String lastPlatformRefactor(String oldQn, int fromRefactorId) {
		if(fromRefactorId < 0) return oldQn;
		return last(platform, oldQn, fromRefactorId);
	}

	public String lastApplicationRefactor(String oldQn, int fromRefactorId) {
		if(fromRefactorId < 0) return oldQn;
		return last(application, oldQn, fromRefactorId);
	}

	private String last(Refactors refactors, String oldQn, int fromRefactorId) {
		String[] result = {oldQn};
		refactors.subListById(fromRefactorId).forEach(r -> {
			if(r.oldQn.equals(result[0]))
				result[0] = r.newQn;
		});
		return result[0];
	}

	public int lastPlatformRefactor(){
		return platform.size() - 1;
	}

	public int lastApplicationRefactor(){
		return application.size() - 1;
	}

}
