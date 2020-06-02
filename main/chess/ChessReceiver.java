package chess;

import java.io.IOException;

public interface ChessReceiver {
    /**
     * erlaubt in Zustand START, SENT_DICE. führt zu START, START_WAITING_FOR_COLOR oder START_CHOOSING_COLOR
     * @param number
     * @throws IOException
     */
    void receiveDice(int number) throws IOException, StatusException;

    /**
     * erlaubt in Zustand START_WAITING_FOR_COLOR. Führt zu ACTIVE oder PASSIVE
     * @param white
     * @throws IOException
     */
    void receiveChooseColor(boolean white) throws IOException, StatusException;

    /**
     * erlaubt in Zustand PASSIVE. Führt zu ACTIVE
     * @param from
     * @param to
     * @throws IOException
     */
    void receiveMove(int from, int to) throws IOException, StatusException;

    /**
     * erlaubt im Zustand PASSIVE. Führt zu ACTIVE
     * @param from
     * @param figureType
     * @throws IOException
     */
    void receiveMovePawnRule(int from, int to, int figureType) throws IOException, StatusException;

    /**
     * erlaubt im Zustand PASSIVE. Führt zu ACTIVE
     * @param from
     * @throws IOException
     */
    void receiveRochade(int from) throws IOException, StatusException;

    /**
     * erlaubt im Zustand PASSIVE. Führt zu END
     * @param reason
     * @throws IOException
     */
    void receiveEndGame(int reason) throws IOException, StatusException;

    /**
     * erlaubt im Zustand PASSIVE. führt zu ANSWERING
     * @param reason
     * @throws IOException
     */
    void receiveProposalEnd(int reason) throws IOException, StatusException;

    /**
     * erlaubt im Zustand WAITING. Führt zu ACTIVE oder END
     * @param accept
     * @throws IOException
     */
    void receiveProposalAnswer(boolean accept) throws IOException, StatusException;
}
