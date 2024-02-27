package Project.TicTacToe.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimensions;

    private List<List<Cells>> matrix;

    public Board(Board oldBoard){
        this.dimensions= oldBoard.dimensions;
        this.matrix=new ArrayList<>();
        for (List<Cells> oldCellsList:oldBoard.getMatrix())
        {
            List<Cells> newCellList =new ArrayList<>();
            for (Cells oldcell:oldCellsList)
            {
                Cells newCell = new Cells() ;
                newCell.setCellState(oldcell.getCellState());
                newCell.setPlayer(oldcell.getPlayer());
                newCell.setCol(oldcell.getCol());
                newCell.setRow(oldcell.getRow());
                newCellList.add(newCell);
            }
            this.matrix.add(newCellList);
        }
    }
    public Board clone()
    {
        return new Board(this);
    }

    public int getDimensions() { return dimensions; }

    public void setDimensions(int dimensions) {this.dimensions = dimensions; }

    public List<List<Cells>> getMatrix()
    {
        return matrix;
    }

    public void setMatrix(List<List<Cells>> matrix) { this.matrix = matrix; }

    public Board(int dimensions) {
        this.dimensions=dimensions;
        matrix=new ArrayList<>();
        for (int i=0 ;i<dimensions;i++)
        {
            List<Cells> cellsList= new ArrayList<>();
            for (int j=0;j<dimensions;j++)
            {
                cellsList.add(new Cells(i,j));
            }
            matrix.add(cellsList);
        }
    }

    public void displayBoard() {
        for (int i=0;i<this.dimensions;i++)
        {
            for (int k = 0; k < this.dimensions; k++)
            {
                System.out.print(" _ _ _ _ _ ");
            }
            System.out.println();
            for (int j = 0; j < this.dimensions; j++) {
                System.out.print("|         |");
            }
            System.out.println();
            for (int j = 0; j < this.dimensions; j++) {
                Cells cells = matrix.get(i).get(j);
                if (cells.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|         |");
                } else {
                    System.out.print("|    " + cells.getPlayer().getSymbol() + "    |");
                }
            }
            System.out.println();
            for (int j = 0; j < this.dimensions; j++) {
                System.out.print("|         |");
            }
            System.out.println();
        }
        for (int k=0;k<this.dimensions;k++)
        {
            System.out.print(" _ _ _ _ _ ");
        }
        System.out.println();
    }
}
