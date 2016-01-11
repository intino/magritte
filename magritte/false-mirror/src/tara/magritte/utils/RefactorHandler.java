package tara.magritte.utils;

import tara.io.refactor.Refactors;

import java.util.logging.Logger;

public class RefactorHandler {

	private static final Logger LOG = Logger.getLogger(RefactorHandler.class.getName());

	private Refactors refactors;

	public RefactorHandler(Refactors refactors) {
		this.refactors = refactors;
	}

	public String last(String oldQn, int fromRefactorId) {
		if(fromRefactorId > refactors.size()) LOG.severe("Provided refactor id is higher than the number of refactors");
		String[] result = {oldQn};
		refactors.subListById(fromRefactorId).forEach(r -> {
			if(r.oldQn.equals(result[0]))
				result[0] = r.newQn;
		});
		return result[0];
	}

	public int lastRefactorId(){
		return refactors.size() - 1;
	}

}
