package chess;

import java.io.IOException;

public interface ChessReceiver {

    //erlaubt in Zustand START, SENT_DICE. führt zu START, START_WAITING_FOR_COLOR oder START_CHOOSING_COLOR
    void receiveDice(int number) throws IOException, StatusException;


    //erlaubt in Zustand START_WAITING_FOR_COLOR. Führt zu ACTIVE oder PASSIVE
    void receiveChooseColor(boolean white) throws IOException, StatusException;

    //erlaubt in Zustand PASSIVE. Führt zu ACTIVE
    void receiveMove(int from, int to) throws IOException, ChessException, StatusException;


    //erlaubt im Zustand PASSIVE. Führt zu ACTIVE
    void receiveMovePawnRule(int from, int to, int figureType) throws IOException, StatusException;


    //erlaubt im Zustand PASSIVE. Führt zu ACTIVE
    void receiveRochade(int from) throws IOException, StatusException;


    //erlaubt im Zustand PASSIVE. Führt zu END
    void receiveEndGame(int reason) throws IOException, StatusException;


    //erlaubt im Zustand PASSIVE. führt zu ANSWERING
    void receiveProposalEnd(int reason) throws IOException, StatusException;


    //erlaubt im Zustand WAITING. Führt zu ACTIVE oder END
    void receiveProposalAnswer(boolean accept) throws IOException, StatusException;
}
