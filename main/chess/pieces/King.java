package chess.pieces;

public class King implements ChessPiece {
    private boolean white;

    public King(boolean white) {
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
            return "\u2654";
        else
            return "\u265A";
    }
}
