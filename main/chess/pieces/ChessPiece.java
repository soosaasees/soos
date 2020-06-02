package chess.pieces;

public interface ChessPiece {

    //zeigt Typ an
    PieceType getType();

    //sagt, ob Figur weiß ist
    boolean getWhite();

    //checkt, ob move erlaubt ist. ignoriert dabei aber, ob diese Figur von diesem Spieler bewegt werden darf.
    boolean check(ChessPiece[][] board, int[] from, int[] to);
}

