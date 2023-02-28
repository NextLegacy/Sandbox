package tictactoe;

// this is a try to "efficiently" implement a TicTacToe game
// just for fun and to test out some bitwise operations
// Not finished yet, and gonna rethink it, but for now discountinued
public class TicTacToe 
{
    //field = 0bT__AAA_AAA_AAA_BBB_BBB_BBB | T = turn | A = used field | B = player
    private int field = 0b0__000_000_000_000_000_000;
    private int turn  = 0b1__000_000_000_000_000_000;

    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();

        t.makeMove(1);
        t.makeMove(2);
        t.makeMove(2);
        t.makeMove(2);
        t.makeMove(1);
        t.makeMove(1);

        System.out.println(Integer.toBinaryString(t.field));
    }

    public int turn() {
        return turn;
    }

    public void makeMove(int pos)
    {
        //field = field | (((field & 0b1) ? (0b1 << pos) : 0) | (0b1 << (pos + 9)));
        
        turn = turn ^ 0b1;
    }
}
