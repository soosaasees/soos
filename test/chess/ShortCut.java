package chess;

import java.io.IOException;

public class ShortCut implements ChessSender {
    private ChessReceiver receiver;

    public void setReceiver(ChessReceiver receiver) {
        this.receiver=receiver;
    }
    @Override
    public void sendDice(int number) throws IOException, StatusException {
        this.receiver.receiveDice(number );
    }

    @Override
    public void sendChooseColor(boolean white) throws IOException, StatusException {
        this.receiver.receiveChooseColor(white);
    }

    @Override
    public void sendMove(int from, int to) throws IOException, StatusException, ChessException {
        this.receiver.receiveMove(from, to);
    }

    @Override
    public void sendMovePawnRule(int from, int to, int figureType) throws IOException, StatusException {
        this.receiver.receiveMovePawnRule(from, to, figureType);
    }

    @Override
    public void sendRochade(int from) throws IOException, StatusException {
        this.receiver.receiveRochade(from);
    }

    @Override
    public void sendEndGame(int reason) throws IOException, StatusException {
        this.receiver.receiveEndGame(reason);
    }

    @Override
    public void sendProposalEnd(int reason) throws IOException, StatusException {
        this.receiver.receiveProposalEnd(reason);
    }

    @Override
    public void sendProposalAnswer(boolean accept) throws IOException, StatusException {
        this.receiver.receiveProposalAnswer(accept);
    }
}
