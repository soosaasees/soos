package chess;

import java.io.IOException;

public interface ChessSender {

     // erlaubt in START. Führt zu DICE_SENT

    void sendDice(int number) throws IOException, StatusException;


    //erlaubt in START_CHOOSING_COLOR. Führt zu ACTIVE oder PASSIVE
    void sendChooseColor(boolean white) throws IOException, StatusException;


    //erlaubt in ACTIVE, führt zu PASSIVE
    void sendMove(int from, int to) throws IOException, StatusException, ChessException;


    //erlaubt in ACTIVE, führt zu PASSIVE
    void sendMovePawnRule(int from, int to, int figureType) throws IOException, StatusException;


     //erlaubt in ACTIVE, führt zu PASSIVE
    void sendRochade(int from) throws IOException, StatusException;


    //erlaubt in ACTIVE, führt zu END

    void sendEndGame(int reason) throws IOException, StatusException;


    //erlaubt in ACTIVE, führt zu WAITING
    void sendProposalEnd(int reason) throws IOException, StatusException;


    //erlaubt in ANSWERING, führt zu PASSIVE oder END
    void sendProposalAnswer(boolean accept) throws IOException, StatusException;
}
