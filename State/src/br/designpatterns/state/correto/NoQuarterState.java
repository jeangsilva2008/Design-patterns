package br.designpatterns.state.correto;

public class NoQuarterState implements State {
	
	
	GumballMachine gumball;
	

	public NoQuarterState(GumballMachine gumball) {
		this.gumball = gumball;
	}

	
	
	@Override
	public void insertQuarter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejectQuarter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnCrank() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
