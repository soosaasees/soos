package chess;

import java.io.IOException;

public interface  ChessUsage {

    //determine who starts
    void doDice() throws StatusException, IOException;

    //@return true if you are in this status
    boolean is(ChessStatus status);


    //set game stone from a position to another
    void move(int from, int to) throws ChessException, StatusException, IOException;

    //Determines if the player winning the diceroll picks white
    void chooseColor(boolean b) throws StatusException, IOException;
}
