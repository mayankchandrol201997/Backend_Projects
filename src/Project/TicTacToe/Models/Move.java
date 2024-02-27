package Project.TicTacToe.Models;

public class Move {
    private Cells cells;

    public Cells getCells() {
        return cells;
    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }

    public Move(Cells cells)
    {
        this.cells=cells;
    }

    public void undoLastMove(Game game)
    {
        game.setBoard(game.getBoardList().get(game.getBoardList().size()-1));
        game.getMoveList().remove(game.getMoveList().size()-1);
        game.getBoardList().remove(game.getBoardList().size()-1);
        game.getWinningStrategy().undoMove(this);
    }
}
