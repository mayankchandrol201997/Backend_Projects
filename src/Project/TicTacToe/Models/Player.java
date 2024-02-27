package Project.TicTacToe.Models;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move executeMove(Game game,Player player)
    {
        Scanner sc = new Scanner(System.in);
        game.getBoardList().add(game.getBoard());
        Board currentBoard = game.getBoard().clone();
        game.setBoard(currentBoard);
        int dimension = currentBoard.getDimensions();
        while (true)
        {
            System.out.println("enter row number");
            int row = sc.nextInt();
            System.out.println("enter column number");
            int col = sc.nextInt();
            if(row>=dimension ||col>=dimension ||row<0 ||col<0)
            {
                System.out.println("Invalid move.Please make a move again");
            }
            else {
                Cells cells = currentBoard.getMatrix().get(row).get(col);
                if (cells.getCellState().equals(CellState.EMPTY))
                {
                    cells.setPlayer(player);
                    cells.setCellState(CellState.FILLED);
                    Move movePlayed = new Move(cells);
                    game.getMoveList().add(movePlayed);
                    return movePlayed;
                }
                else {
                    System.out.println("Entered cell already filled.Please make a move again");
                }
            }
        }
    }
}
