package models.grid;

import java.util.Arrays;

public class CellState {
	private int[] myState;
	
	public CellState(int[] state) {
		myState = state;
	}

	public int getState(int i){
		if(i >= 0 && i < myState.length)
			return myState[i];
		else
			throw new IndexOutOfBoundsException();
	}

	public int[] getState(){
		return myState;
	}
	
	public void setState(int val, int i){
		if (i >= 0 && i < myState.length)
			myState[i] = val;
		else
			throw new IndexOutOfBoundsException();
	}
	
	public void increment(int i){
		if (i >= 0 && i < myState.length)
			myState[i] = myState[i] + 1;
		else
			throw new IndexOutOfBoundsException();
	}

	public CellState copy() {
		return new CellState(Arrays.copyOf(myState, myState.length));
	}
	
}
