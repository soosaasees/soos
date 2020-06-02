package chess.pieces;

public class Bishop implements ChessPiece   {
    private boolean white;

    public Bishop(boolean white) {
        this.white=white;
    }

    @Override
    public PieceType getType() {
        return null;
    }

    @Override
    public boolean getWhite() { return this.white; }

    @Override
    public boolean check(ChessPiece[][] board, int from, int to) {
        return false;
    }

    public String toString()    {
        if(this.white=true)
            return "\u2657";
        else
            return "\u265C";
    }
}

