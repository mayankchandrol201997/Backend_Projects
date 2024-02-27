package Project.TicTacToe.Service.WinningStrategy;

import Project.TicTacToe.Models.Game;
import Project.TicTacToe.Models.Move;
import Project.TicTacToe.Models.Player;

public class WinningStrategyOrderN implements WinningStrategy {
    public WinningStrategyOrderN(int dimesion) {
    }

    @Override
    public Player checkWinner(Game game, Move lastPlayedMove) {
        return null;
    }

    @Override
    public void undoMove(Move move) {

    }
}
