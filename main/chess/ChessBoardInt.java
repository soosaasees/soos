package chess;

import java.io.IOException;

public interface ChessBoardInt {

    //zeigt Brett an
    void printBoard();

    //prüft auf Schach, Matt, Patt
    void check();

    //trägt move ins Brett ein
    void move(int from, int to) throws ChessException;

    //trägt move mit Bauernregel ins Brett ein
    void movePawnRule(int from, int to, int figureType);

    //trägt Rochade ins Brett ein
    void rochade(int from);

    //Zeigt an, ob Figur auf gegebener Position weiß ist
    boolean posWhite(int pos);
}
