package Project.TicTacToe.Models;

import Project.TicTacToe.Exception.InvalidBotCountException;
import Project.TicTacToe.Exception.InvalidPlayerException;
import Project.TicTacToe.Exception.InvalidSymbolException;
import Project.TicTacToe.Service.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {

    private int dimension;
    private WinningStrategy winningStrategy;
    private List<Player> playerList;
    private Board board;
    private List<Board> boardList;
    private List<Move> moveList;
    private int noOfSymbols;

    private Player winner;
    private GameState gameState;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public int getNoOfSymbols() {
        return noOfSymbols;
    }

    public void setNoOfSymbols(int noOfSymbols) {
        this.noOfSymbols = noOfSymbols;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Game(Builder builder){
        this.dimension = builder.dimension;
        this.board = new Board(dimension);
        this.playerList = builder.playerList;
        this.boardList = new ArrayList<>();
        this.moveList = new ArrayList<>();
        this.noOfSymbols = playerList.size();
        this.winningStrategy = builder.winningStrategy;
        this.gameState = GameState.INPROGRESS;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public void replay() throws InterruptedException{
        int index=0;
        boolean initialStatePrinted = false;
        for (Board board: boardList)
        {
            if(initialStatePrinted)
            {
                Move move = getMoveList().get(index);
                int row = move.getCells().getRow();
                int col = move.getCells().getCol();
                String player = move.getCells().getPlayer().getName();
                char symbol = move.getCells().getPlayer().getSymbol();
                System.out.println(player+" played "+symbol+ " on("+row+","+col+")");
                index++;
            }
            initialStatePrinted=true;
            board.displayBoard();
            Thread.sleep(2000);
            System.out.println();
        }
        System.out.println(winner.getName()+" Won the game.");
    }

    public static class Builder{
        private int dimension;
        private WinningStrategy winningStrategy;
        private List<Player> playerList;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public void validatePlayers(){
            if(playerList.size()!=dimension-1)
                throw new InvalidPlayerException("There can oly be N-1 or N-2 players in the game");
        }
        public void validateBotcount(){
            int botCount=0;
            for (Player player:playerList){
                if(player.getPlayerType().equals(PlayerType.BOT))
                    botCount++;
            }
            if(botCount>1)
                throw new InvalidBotCountException("There can only be one bot int the game.");
        }
        public void validateSymbols(){
            HashSet<Character> symbolSet = new HashSet<>();
            for (Player player:playerList){
                symbolSet.add(player.getSymbol());
            }

            if(symbolSet.size()!=playerList.size())
                throw new InvalidSymbolException("Every player should have unique symbol.");
        }

        public void validate()
        {
            validatePlayers();
            validateBotcount();
            validateSymbols();
        }
        public Game build()
        {
            validate();
            return new Game(this);
        }
    }
}
