package chess.pieces;

public class Pawn implements ChessPiece {
    private boolean white;

    public Pawn(boolean white) {
        this.white=white;
    }
    @Override
    public PieceType getType() {
        return null;
    }

    @Override
    public boolean getWhite() { return this.white; }

    @Override
    public boolean check(ChessPiece[][] board, int[] from, int[] to) {

        if(PlayerRow(to[0])-PlayerRow(from[0])==2 && to[1]-from[1]==0)  {
            if(from[0]==1 && board[nextRow(from[0])][from[1]]==null && board[to[0]][to[1]]==null)  {
                return true;
            }
        }

        else if(PlayerRow(to[0])-PlayerRow(from[0])==1 && to[1]-from[1]==0)   {
            if(board[to[0]][to[1]]==null)
                return true;
        }

        else if(to[0]-from[0]==1 && ( to[1]-from[1]==1 || to[1]-from[1]==-1 ))  {
            if(board[to[0]][to[1]]==null)
                return false;
            else if(board[to[0]][to[1]].getWhite()!=this.white)
                return true;
        }
        return false;
    }

    public String toString() {
        if (this.white = true)
            return "\u2659";
        else
            return "\u265F";
    }

    //gibt an, in welcher Reihe die Figur vom Spieler aus ist.
    private int PlayerRow(int row)     {
        if(this.white==true)
            return row;
        else return (7-row);

    }

    private int nextRow(int row)   {
        if(this.white==true)
            return row+1;
        else
            return row-1;
    }
}

