package sdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public abstract class State implements Comparable<State>{
	
	protected ArrayList<Action> permissibleActions;
	protected Action noAction;
	
	public Enumeration<Action> getPermissibleActions() {
		Enumeration<Action> e = Collections.enumeration(this.permissibleActions);
		return e;
	}
	
	public Action getNoAction(){
		return noAction;
	}
	
	public abstract int compareTo(State state);
	public abstract boolean equals(Object state);
	public abstract int hashCode();
	protected abstract void buildActionList();
}
