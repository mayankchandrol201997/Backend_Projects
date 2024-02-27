package Project.TicTacToe.Service.WinningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningFactory(WinningStrategyType winningStrategyType,int dimension)
    {
        switch (winningStrategyType)
        {
            case ORDER_N_WINNING:
                return new WinningStrategyOrderN(dimension);
            case ORDER_ONE_WINNING:
                return new WinningStrategyOrderOne(dimension);
            case ORDER_N_SQUARE_WINNING:
                return new WinningStrategyOrderNSquare(dimension);
            case ORDER_N_CUBE_WINNING:
                return new WinningStrategyOrderNCube(dimension);
        }
        return null;
    }

}
