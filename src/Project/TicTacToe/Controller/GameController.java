package Project.TicTacToe.Controller;

import Project.TicTacToe.Models.Game;
import Project.TicTacToe.Models.Move;
import Project.TicTacToe.Models.Player;
import Project.TicTacToe.Service.WinningStrategy.WinningStrategyFactory;
import Project.TicTacToe.Service.WinningStrategy.WinningStrategyType;

import java.util.List;

public class GameController {

    public Game createGame(List<Player> playerList, int dimension, WinningStrategyType winningStrategyType)
    {
        Game game =Game.builder().
                setWinningStrategy(WinningStrategyFactory.getWinningFactory(winningStrategyType,dimension))
                .setDimension(dimension).setPlayerList(playerList).build();
        return game;
    }

    public Move executeMove(Game game, Player player)
    {
        Move movePlayed = player.executeMove(game,player);
        return movePlayed;
    }

    public Player checkWinner(Game game, Move lastPlayedMove)
    {
        return game.getWinningStrategy().checkWinner(game,lastPlayedMove);
    }

    public void undoMove(Game game, Move lastPlayedMove)
    {
        lastPlayedMove.undoLastMove(game);
    }

    public void replayGame(Game game) throws InterruptedException {
        game.replay();
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }
}
