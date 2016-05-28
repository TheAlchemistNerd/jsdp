/**
 * jsdp: A Java Stochastic Dynamic Programming Library
 * 
 * MIT License
 * 
 * Copyright (c) 2016 Roberto Rossi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jsdp.sdp;

import java.util.Hashtable;

/**
 * An abstraction representing a repository for the value associated with each {@code State}.
 *  
 * @author Roberto Rossi
 *
 */
public abstract class ValueRepository {	
	protected Hashtable<StateAction,Double> valueHashTable = new Hashtable<StateAction,Double>();
	protected Hashtable<State,Double> optimalValueHashTable = new Hashtable<State,Double>();
	protected Hashtable<State,Action> optimalActionHashTable = new Hashtable<State,Action>();
	
	/**
	 * Returns the immediate value of a transition from {@code initialState} to {@code finalState} under a chosen {@code action}.
	 * 
	 * @param initialState the initial state of the stochastic process.
	 * @param action the chosen action.
	 * @param finalState the final state of the stochastic process.
	 * @return the immediate value of a transition from {@code initialState} to {@code finalState} under a chosen {@code action}.
	 */
	protected abstract double getImmediateValue(State initialState, Action action, State finalState);
	
	/**
	 * Returns the expected value associated with {@code initialState} and {@code action} under one-step transition probabilities
	 * described in {@code transitionProbability}.
	 * 
	 * @param initialState the initial state of the stochastic process.
	 * @param action the chosen action. 
	 * @param transitionProbability the transition probabilities of the stochastic process.
	 * @return the expected value associated with {@code initialState} and {@code action} under one-step transition probabilities
	 * described in {@code transitionProbability}.
	 */
	public abstract double getExpectedValue(State initialState, Action action, TransitionProbability transitionProbability);
	
	/**
	 * Associates an optimal expected value {@code expectedValue} to {@code state}.
	 * 
	 * @param state the target state.
	 * @param expectedValue the optimal expected total cost.
	 */
	public synchronized void setOptimalExpectedValue(State state, double expectedValue){
		this.optimalValueHashTable.put(state, new Double(expectedValue));
	}
	
	/**
	 * Returns the optimal expected value associated with {@code state}.
	 * 
	 * @param state the target state.
	 * @return the optimal expected value.
	 */
	public double getOptimalExpectedValue(State state){
		return this.optimalValueHashTable.get(state).doubleValue();
	}
	
	/**
	 * Associates an optimal action {@code action} to state {@code state}.
	 * 
	 * @param state the target state.
	 * @param action the optimal action.
	 */
	public synchronized void setOptimalAction(State state, Action action){
		this.optimalActionHashTable.put(state, action);
	}
	
	/**
	 * Returns the optimal action associated with {@code state}.
	 * 
	 * @param state the target state.
	 * @return the optimal action.
	 */
	public Action getOptimalAction(State state){
		return this.optimalActionHashTable.get(state);
	}
	
	/**
	 * An association abstraction for a pair state-action.
	 * 
	 * @author Roberto Rossi
	 *
	 */
	protected class StateAction{
		State initialState;
		Action action;
		
		/**
		 * Creates an instance of {@code StateAction} from state {@initialState} and action {@code action}. 
		 * 
		 * @param initialState the target state.
		 * @param action the target action.
		 */
		public StateAction(State initialState, Action action){
			this.initialState = initialState;
			this.action = action; 
		}
		
		public boolean equals(StateAction stateAction){
			return this.initialState.equals(stateAction.initialState) && this.action.equals(stateAction.action);
		}
		
		public int hashCode(){
			String hash = "";
	        hash = hash + initialState.hashCode() + action.hashCode();
	        return hash.hashCode();
		}
	}
}