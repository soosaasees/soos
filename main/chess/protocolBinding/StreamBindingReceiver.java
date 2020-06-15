package chess.protocolBinding;

import chess.ChessException;
import chess.ChessReceiver;
import chess.StatusException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
//TODO: finde raus, wie man von der einen am besten die andere Methode aufruft
public class StreamBindingReceiver extends Thread   {
    private final DataInputStream dis;
    private final ChessReceiver receiver;

    public StreamBindingReceiver(InputStream is, ChessReceiver receiver)    {
        this.dis = new DataInputStream(is);
        this.receiver=receiver;
    }

    public void readDice() throws IOException, StatusException  {
        int number=this.dis.readInt();
        this.receiver.receiveDice(number);
    }

    public void readChooseColor() throws IOException, StatusException   {
        boolean white=this.dis.readBoolean();
        this.receiver.receiveChooseColor(white);
    }

    public void readMove() throws IOException, StatusException {
        int from=this.dis.readInt();
        int to=this.dis.readInt();
        try {
            this.receiver.receiveMove(from, to);
        } catch (ChessException e) {
            throw new IOException();
        }
    }

    public void readMovePawnRule() throws IOException, StatusException   {
        int from=this.dis.readInt();
        int to=this.dis.readInt();
        int figureType=this.dis.readInt();
        this.receiver.receiveMovePawnRule(from, to, figureType);
    }

    public void readRochade() throws IOException, StatusException    {
        int from=this.dis.readInt();
        this.receiver.receiveRochade(from);
    }

    public void readEndGame() throws IOException, StatusException    {
        int reason=this.dis.readInt();
        this.receiver.receiveEndGame(reason);
    }

    public void readProposalEnd() throws IOException, StatusException    {
        int reason=this.dis.readInt();
        this.receiver.receiveProposalEnd(reason);
    }

    public void readProposalAnswer() throws IOException, StatusException {
        boolean answer=this.dis.readBoolean();
        this.receiver.receiveProposalAnswer(answer);
    }

    public void run() {
        boolean again= true;
        while(again=true) {
            try {
                int cmd= this.dis.readInt();

                switch(cmd) {
                    case StreamBinding.DICE: this.readDice(); break;
                    case StreamBinding.MOVE: this.readMove(); break;
                    case StreamBinding.MOVEPAWNRULE: this.readMovePawnRule(); break;
                    case StreamBinding.ROCHADE: this.readRochade(); break;
                    case StreamBinding.ENDGAME: this.readEndGame(); break;
                    case StreamBinding.PROPOSEEND: this.readProposalEnd(); break;
                    case StreamBinding.CHOOSECOLOR: this.readChooseColor(); break;
                    case StreamBinding.PROPOSALANSWER: this.readProposalAnswer(); break;
                    default: again=false; System.err.println("unknown command code: "+ cmd); break;
                }
            } catch (IOException | StatusException e) {
                System.err.println("Status Exception: " + e.getLocalizedMessage());
                again = false;
            }
        }
    }
}
