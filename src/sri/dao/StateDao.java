package sri.dao;

import java.util.List;
import sri.data.State;

public interface StateDao {

	public List<State> getAllState();

	public State getStateById(String stateId);
	
}
