package Project.TicTacToe.Service.WinningStrategy;

import Project.TicTacToe.Models.Game;
import Project.TicTacToe.Models.Move;
import Project.TicTacToe.Models.Player;

public interface WinningStrategy {
    Player checkWinner(Game game, Move lastPlayedMove);
    void undoMove(Move move);
}
