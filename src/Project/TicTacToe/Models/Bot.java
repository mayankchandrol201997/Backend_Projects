package Project.TicTacToe.Models;

import Project.TicTacToe.Exception.InvalidPlayerException;

import java.util.List;

public class Bot extends Player {

    private BotLevelOfDifficylty botLevelOfDifficylty;

    public Bot(BotLevelOfDifficylty easy, int id, char symbol) {
        super(id,"BOT",symbol,PlayerType.BOT);
        this.botLevelOfDifficylty=getBotLevelOfDifficylty();
    }


    public BotLevelOfDifficylty getBotLevelOfDifficylty() { return botLevelOfDifficylty; }


    public void setBotLevelOfDifficylty(BotLevelOfDifficylty botLevelOfDifficylty) {
        this.botLevelOfDifficylty = botLevelOfDifficylty;
    }

    @Override
    public Move executeMove(Game game, Player player) {
        game.getBoardList().add(game.getBoard());
        Board currentBoard = game.getBoard().clone();
        game.setBoard(currentBoard);

        for (List<Cells> cellsList:currentBoard.getMatrix())
        {
            for (Cells cells:cellsList)
            {
                if (cells.getCellState().equals(CellState.EMPTY))
                {
                    cells.setPlayer(player);
                    cells.setCellState(CellState.FILLED);
                    Move move = new Move(cells);
                    game.getMoveList().add(move);
                    return move;
                }
            }
        }
        return null;
    }
}
