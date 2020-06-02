package chess;

import java.io.IOException;

public interface ChessSender {
    /**
     * erlaubt in START. Führt zu DICE_SENT
     * @param number
     * @throws IOException
     */
    void sendDice(int number) throws IOException, StatusException;

    /**
     * erlaubt in START_CHOOSING_COLOR. Führt zu ACTIVE oder PASSIVE
     * @param white
     * @throws IOException
     */
    void sendChooseColor(boolean white) throws IOException, StatusException;

    /**
     * erlaubt in ACTIVE, führt zu PASSIVE
     * @param from
     * @param to
     * @throws IOException
     */
    void sendMove(int from, int to) throws IOException, StatusException;

    /**
     * erlaubt in ACTIVE, führt zu PASSIVE
     * @param from
     * @param figureType
     * @throws IOException
     */
    void sendMovePawnRule(int from, int to, int figureType) throws IOException, StatusException;

    /**
     * erlaubt in ACTIVE, führt zu PASSIVE
     * @param from
     * @throws IOException
     */
    void sendRochade(int from) throws IOException, StatusException;

    /**
     * erlaubt in ACTIVE, führt zu END
     * @param reason
     * @throws IOException
     */
    void sendEndGame(int reason) throws IOException, StatusException;

    /**
     * erlaubt in ACTIVE, führt zu WAITING
     * @param reason
     * @throws IOException
     */
    void sendProposalEnd(int reason) throws IOException, StatusException;

    /**
     * erlaubt in ANSWERING, führt zu PASSIVE oder END
     * @param accept
     * @throws IOException
     */
    void sendProposalAnswer(boolean accept) throws IOException, StatusException;
}
