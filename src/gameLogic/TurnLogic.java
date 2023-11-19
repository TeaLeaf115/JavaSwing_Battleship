package gameLogic;

public class TurnLogic {
    private int turnOrder;
    private final int PLAYER_TURN = 0;
    private final int COMPUTER_TURN = 1;

    public TurnLogic() {
		turnOrder = 0;
    }

	public void nextTurn() {
		turnOrder++;
	}

	public int getTurn() {
		return turnOrder % 2 == 0 ? PLAYER_TURN : COMPUTER_TURN;
	}

	public boolean isPlayerTurn() {
		return turnOrder % 2 == 0;
	}

	public boolean isComputerTurn() {
		return turnOrder % 2 == 1;
	}

}
