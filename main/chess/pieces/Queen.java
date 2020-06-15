package chess.pieces;

public class Queen implements ChessPiece    {
    private boolean white;

    public Queen(boolean white) {
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
        return false;
    }

    public String toString()    {
        if(this.white=true)
            return "\u2655";
        else
            return "\u265B";
    }

}
