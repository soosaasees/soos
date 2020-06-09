package chess.protocolBinding;

import chess.ChessSender;
import chess.StatusException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static chess.protocolBinding.StreamBinding.*;

//enkodiert Methoden und Parameter und versendet mittels OutputStream
public class StreamBindingSender implements ChessSender {
    private final DataOutputStream dos;

    public StreamBindingSender(OutputStream os)   {
        this.dos = new DataOutputStream(os);
    }

    @Override
    public void sendDice(int number) throws IOException, StatusException {
        this.dos.writeInt(DICE);
        this.dos.writeInt(number);
    }

    @Override
    public void sendChooseColor(boolean white) throws IOException, StatusException {
        this.dos.writeBoolean(white);
    }

    @Override
    public void sendMove(int from, int to) throws IOException, StatusException {
        this.dos.writeInt(MOVE);
        this.dos.writeInt(from);
        this.dos.writeInt(to);
    }

    @Override
    public void sendMovePawnRule(int from, int to, int figureType) throws IOException, StatusException {
        this.dos.writeInt(MOVEPAWNRULE);
        this.dos.writeInt(from);
        this.dos.writeInt(to);
        this.dos.writeInt(figureType);
    }

    @Override
    public void sendRochade(int from) throws IOException, StatusException {
        this.dos.writeInt(ROCHADE);
        this.dos.writeInt(from);
    }

    @Override
    public void sendEndGame(int reason) throws IOException, StatusException {
        this.dos.writeInt(ENDGAME);
        this.dos.writeInt(reason);
    }

    @Override
    public void sendProposalEnd(int reason) throws IOException, StatusException {
        this.dos.writeInt(PROPOSEEND);
        this.dos.writeInt(reason);
    }

    @Override
    public void sendProposalAnswer(boolean accept) throws IOException, StatusException {
        this.dos.writeBoolean(accept);
    }
}
