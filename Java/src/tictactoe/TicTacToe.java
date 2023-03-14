package tictactoe;

// this is a try to "efficiently" implement a TicTacToe game
// just for fun and to test out some bitwise operations
public class TicTacToe 
{
    //field = 0bT__BBB_BBB_BBB_AAA_AAA_AAA | T = turn | A = used field | B = player
    private int game = 0b0__000_000_000_000_000_000;

    private final int winningGames[] = new int[] 
    {
        0b0_000_000_111_000_000_111,
        0b0_000_111_000_000_111_000,
        0b0_111_000_000_111_000_000,
        
        0b0_100_100_100_100_100_100,
        0b0_010_010_010_010_010_010,
        0b0_001_001_001_001_001_001,

        0b0_100_010_001_100_010_001,
        0b0_001_010_100_001_010_100
    };

    public static void main(String[] args)
    {
        TicTacToe ticTacToe = new TicTacToe();
        
        System.out.println(ticTacToe.fieldAsString());

        ticTacToe.makeMove(0);

        System.out.println(ticTacToe.fieldAsString());

        System.out.println(ticTacToe.gameAsBinaryString());
        
        System.out.println(ticTacToe.checkWinner());
    }

    private int field()
    {
        return (game & 0b0__111_111_111_111_111_111);
    }

    private int player()
    {
        return (game & 0b0__111_111_111_000_000_000) >> 9;
    }

    private int player(int index)
    {
        index += 9;

        return (game & (0b0__000_000_000_000_000_001 << index)) >> index;
    }

    private int used()
    {
        return (game & 0b0__000_000_000_111_111_111);
    }

    private int used(int index)
    {
        return (game & (0b0__000_000_000_000_000_001 << index)) >> index;
    }

    private int turn()
    {
        return game >>> 18;
    }

    private void nextTurn()
    {
        game ^= 0b1__000_000_000_000_000_000;
    }

    public void makeMove(int index)
    {
        if (used(index) == 0)
        {
            game |= 0b0__000_000_000_000_000_001 << index;
            game |= turn() << (index + 9);
        }

        nextTurn();
    }

    public String fieldAsString()
    {
        String result = 
            ((used(0) == 0) ? "[ ]" : (player(0) == 0) ? "[X]" : "[O]") +
            ((used(1) == 0) ? "[ ]" : (player(1) == 0) ? "[X]" : "[O]") +
            ((used(2) == 0) ? "[ ]" : (player(2) == 0) ? "[X]" : "[O]") + "\n" +
            ((used(3) == 0) ? "[ ]" : (player(3) == 0) ? "[X]" : "[O]") +
            ((used(4) == 0) ? "[ ]" : (player(4) == 0) ? "[X]" : "[O]") +
            ((used(5) == 0) ? "[ ]" : (player(5) == 0) ? "[X]" : "[O]") + "\n" +
            ((used(6) == 0) ? "[ ]" : (player(6) == 0) ? "[X]" : "[O]") +
            ((used(7) == 0) ? "[ ]" : (player(7) == 0) ? "[X]" : "[O]") +
            ((used(8) == 0) ? "[ ]" : (player(8) == 0) ? "[X]" : "[O]") + "\n";
        
        return result;
    }

    public String gameAsBinaryString()
    {
        return Integer.toBinaryString(game);
    }

    public int checkWinner()
    {
        // TODO: check if there is a winner

        int player1 = game & 0b0__111_111_111_111_111_111;
        int player2 = (~(game & 0b0__111_111_111_000_000_000) & 0b0__111_111_111_111_111_111) | (game & 0b0__000_000_000_111_111_111);

        System.out.println("player1: " + Integer.toBinaryString(player1));
        System.out.println("player2: " + Integer.toBinaryString(player2));

        for (int i = 0; i < winningGames.length; i++)
        {
            if ((player1 & winningGames[i]) == winningGames[i] ||
                (player2 & winningGames[i]) == winningGames[i])
            {
                return turn();
            }
        }

        return -1;
    }
}
