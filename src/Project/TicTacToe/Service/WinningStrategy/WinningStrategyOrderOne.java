package Project.TicTacToe.Service.WinningStrategy;

import Project.TicTacToe.Models.Game;
import Project.TicTacToe.Models.Move;
import Project.TicTacToe.Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WinningStrategyOrderOne implements WinningStrategy {

    private int dimension;

    private List<HashMap<Character,Integer>> rowlist;
    private List<HashMap<Character,Integer>> collist;
    private HashMap<Character,Integer> leftDiagonalMap;

    private HashMap<Character,Integer> rightDiagonalMap;

    private HashMap<Character,Integer> cornerMap;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<HashMap<Character, Integer>> getRowlist() {
        return rowlist;
    }

    public void setRowlist(List<HashMap<Character, Integer>> rowlist) {
        this.rowlist = rowlist;
    }

    public List<HashMap<Character, Integer>> getCollist() {
        return collist;
    }

    public void setCollist(List<HashMap<Character, Integer>> collist) {
        this.collist = collist;
    }

    public HashMap<Character, Integer> getLeftDiagonalMap() {
        return leftDiagonalMap;
    }

    public void setLeftDiagonalMap(HashMap<Character, Integer> leftDiagonalMap) {
        this.leftDiagonalMap = leftDiagonalMap;
    }

    public HashMap<Character, Integer> getRightDiagonalMap() {
        return rightDiagonalMap;
    }

    public void setRightDiagonalMap(HashMap<Character, Integer> rightDiagonalMap) {
        this.rightDiagonalMap = rightDiagonalMap;
    }

    public HashMap<Character, Integer> getCornerMap() {
        return cornerMap;
    }

    public void setCornerMap(HashMap<Character, Integer> cornerMap) {
        this.cornerMap = cornerMap;
    }

    WinningStrategyOrderOne(int dimension)
    {
        this.dimension = dimension;
        rowlist = new ArrayList<>();
        collist = new ArrayList<>();
        leftDiagonalMap = new HashMap<>();
        rightDiagonalMap = new HashMap<>();
        cornerMap=new HashMap<>();
        for (int i=0;i<dimension;i++)
        {
            rowlist.add(new HashMap<>());
            collist.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Game game, Move lastPlayedMove) {
        int row = lastPlayedMove.getCells().getRow();
        int col = lastPlayedMove.getCells().getCol();
        char symbol = lastPlayedMove.getCells().getPlayer().getSymbol();
        boolean winner = checkAtRow(row,symbol)||checkAtColumn(col,symbol)||
                (row==col && checkAtLeftDiagonal(symbol))||
                (row+col==dimension-1 && checkAtRightDiagonal(symbol))||checkAtCorners(symbol);

        if(winner)
            return lastPlayedMove.getCells().getPlayer();
        return null;
    }

    private boolean checkAtCorners(char symbol) {
        if (cornerMap.containsKey(symbol))
        {
            if (cornerMap.get(symbol)+1==4)
                return true;
        }
        else
            cornerMap.put(symbol,1);

        return false;
    }

    private boolean checkAtRightDiagonal(char symbol) {
        return checkAndUpdate(rightDiagonalMap,symbol);
    }

    private boolean checkAtLeftDiagonal(char symbol) {
        return checkAndUpdate(leftDiagonalMap,symbol);
    }

    private boolean checkAtColumn(int col, char symbol) {
        return checkAndUpdate(collist.get(col),symbol);
    }

    private boolean checkAtRow(int row, char symbol) {
        return checkAndUpdate(rowlist.get(row),symbol);
    }

    @Override
    public void undoMove(Move lastPlayedMove) {
        int row = lastPlayedMove.getCells().getRow();
        int col = lastPlayedMove.getCells().getCol();
        char symbol = lastPlayedMove.getCells().getPlayer().getSymbol();
        removeAtRow(row,symbol);
        removeAtColumn(row,symbol);
        if(row+col==dimension-1)
        removeAtRightDiagonal(symbol);
        if(row==col)
        removeAtLeftDiagonal(symbol);
        if((row==0 && col==0)||(row==dimension-1 && col==dimension-1)||(row==0 && col==dimension-1)||(row==dimension-1 && col==0))
        removeAtCorners(symbol);
    }

    public void removeFromMap(HashMap<Character,Integer> map,char symbol)
    {
        if(map.containsKey(symbol) && map.get(symbol)>0)
        {
            int symbolCount = map.get(symbol)-1;
            map.put(symbol,symbolCount);
        }
    }
    private void removeAtCorners(char symbol) {
        removeFromMap(cornerMap,symbol);
    }

    private void removeAtLeftDiagonal(char symbol) {
        removeFromMap(leftDiagonalMap,symbol);
    }

    private void removeAtRightDiagonal(char symbol) {
        removeFromMap(rightDiagonalMap,symbol);
    }

    private void removeAtColumn(int col, char symbol) {
        removeFromMap(collist.get(col),symbol);
    }

    private void removeAtRow(int row, char symbol) {
        removeFromMap(rowlist.get(row),symbol);
    }

    public boolean checkAndUpdate(HashMap<Character,Integer> map,char symbol)
    {
        if(map.containsKey(symbol))
        {
            int symbolCount = map.get(symbol)+1;
            map.put(symbol,symbolCount);
            return symbolCount==dimension;
        }
        else {
            map.put(symbol,1);
        }
        return false;
    }
}
