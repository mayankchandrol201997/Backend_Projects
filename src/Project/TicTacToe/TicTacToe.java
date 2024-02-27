package Project.TicTacToe;

import Project.TicTacToe.Controller.GameController;
import Project.TicTacToe.Models.*;
import Project.TicTacToe.Service.WinningStrategy.WinningStrategyType;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class TicTacToe {
    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        boolean startPlaying = true;
        while (startPlaying) {
          System.out.println("Enter Dimension of the board");
          int dimension = sc.nextInt();
          System.out.println("Do you want a bot in the game. Y or N");
          char botAns = sc.next().charAt(0);
          int id = 0;
          List<Player> playerList = new ArrayList<>();
          if (botAns == 'Y') {
              Player bot = new Bot(BotLevelOfDifficylty.EASY, id++, '$');
              playerList.add(bot);
          }
          while (id < dimension-1) {
           System.out.println("Enter Player Name");
           String name = sc.next();
           System.out.println("Enter Player Symbol");
           char symbol = sc.next().charAt(0);
           Player newPlayer = new Player(id, name, symbol, PlayerType.HUMAN);
           playerList.add(newPlayer);
           id++;
          }
          try {
              Game game = gameController.createGame(playerList, dimension, WinningStrategyType.ORDER_ONE_WINNING);
              System.out.println("Current state of the board");
              gameController.displayBoard(game);
              Collections.shuffle(playerList);
              int noOfPlayedMoves = 0;
              while (game.getGameState().equals(GameState.INPROGRESS)) {
                  for (int i = 0; i < playerList.size(); i++) {
                      Player player = playerList.get(i);
                      System.out.println("\n"+player.getName() + " your turn to make a move");
                      Move move = gameController.executeMove(game, player);
                      System.out.println("Current state of the board");
                      gameController.displayBoard(game);
                      Player winner = gameController.checkWinner(game, move);
                      noOfPlayedMoves++;

                      if (player.getPlayerType().equals(PlayerType.HUMAN)) {
                          System.out.println("Do you want to UNDO your move ? Y or N");
                          char undoMove = sc.next().charAt(0);
                          if (undoMove == 'Y') {
                              noOfPlayedMoves--;
                              i--;
                              gameController.undoMove(game, move);
                              gameController.displayBoard(game);
                              continue;
                          }
                      }
                      if (null != winner) {
                          game.setWinner(player);
                          game.getBoardList().add(game.getBoard());
                          game.setGameState(GameState.WINNER);
                          System.out.println(player.getName() + " Won the game");
                          break;
                      }
                      if (dimension * dimension == noOfPlayedMoves) {
                          game.setGameState(GameState.DRAW);
                          System.out.println("Game Over. Its a Draw");
                          break;
                      }

                  }
              }
              System.out.println("Do You Want see the replay? Y or N");
              char replay = sc.next().charAt(0);
              if (replay == 'Y') {
                  gameController.replayGame(game);
              }
          }
          catch (Exception e)
              {
                  System.out.println(e.getMessage());
              }
              System.out.println("Do You Want to Play Again? Y or N");
              char play = sc.next().charAt(0);
              startPlaying= play == 'Y';
          }
        }
    }
