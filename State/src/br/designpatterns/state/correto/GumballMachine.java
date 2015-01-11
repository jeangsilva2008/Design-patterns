package br.designpatterns.state.correto;

public class GumballMachine {

	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	
	
	State state = soldOutState;
	int count = 0;
	
	
	public GumballMachine(int numberOfGumballs) {
		
		noQuarterState = new NoQuarterState(this);
		
		this.count = numberOfGumballs;
		
		if(count > 0){
			state = noQuarterState;
		}
	}
	
	
	


	@Override
	public String toString() {
		return "GumballMachine [state=" + state + ", count=" + count + "]";
	}
	
	
	
	
	
}
