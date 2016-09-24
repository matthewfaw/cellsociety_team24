package models.grid;

public class CellState {
	private int[] myState;
	
	public CellState(int[] state) {
		myState = state;
	}

	public int getStateElement(int i){
		if(i >= 0 && i < myState.length)
			return myState[i];
		else
			throw new IndexOutOfBoundsException();
	}

	public int[] getState(){
		return myState;
	}
	
	public boolean setStateElement(int i, int value){
		if(i >= 0 && i < myState.length){
			myState[i] = value;
			return true;
		} else
			return false;
	}
}
