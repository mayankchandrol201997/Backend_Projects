package Project.TicTacToe.Models;

public class Cells {

    private int row;
    private int col;
    private Player player;
    private CellState cellState;

    public Cells(int row,int col) {
        this.row = row;
        this.col = col;
        this.player = null;
        this.cellState = CellState.EMPTY;
    }

    public Cells() {

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row =row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col =col;
    }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public CellState getCellState() {
        return cellState;
    }


    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
}
