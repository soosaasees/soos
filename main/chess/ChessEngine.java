package chess;


import java.io.IOException;

public class ChessEngine implements ChessSender, ChessReceiver{

    public static final int UNDEFINED_DICE=-1;

    private ChessStatus status;
    private int sentDice=UNDEFINED_DICE;
    private ChessBoardInt board;
    private boolean white;
    ChessEngine()   {
        this.status=ChessStatus.START;
        this.board=new ChessBoard();
    }

    //EMPFÄNGERSEITE
    @Override
    public void receiveDice(int number) throws IOException, StatusException {
        if(this.status != ChessStatus.START && this.status != ChessStatus.SENT_DICE)
            throw new StatusException();

        if(this.status==ChessStatus.SENT_DICE)   {
        if(this.sentDice==number)
            this.status=ChessStatus.START;
        else if(this.sentDice>=number)
            this.status=ChessStatus.START_CHOOSING_COLOR;
        else
            this.status=ChessStatus.START_WAITING_FOR_COLOR;
        }
    }

    @Override
    public void receiveChooseColor(boolean white) throws IOException, StatusException {
        if(this.status != ChessStatus.START_WAITING_FOR_COLOR)
            throw new StatusException();

        if(white==true) {
            this.status = ChessStatus.PASSIVE;
            this.white  = false;
        }
        else    {
            this.status=ChessStatus.ACTIVE;
            this.white = true;
        }
    }

    @Override
    public void receiveMove(int from, int to) throws IOException, StatusException {
        if(this.status != ChessStatus.PASSIVE)
            throw new StatusException();

        //übertrage move in Spielmatrix
        board.move(from, to);
        this.status=ChessStatus.ACTIVE;
    }

    @Override
    public void receiveMovePawnRule(int from, int to, int figureType) throws IOException, StatusException {
        if(this.status != ChessStatus.PASSIVE)
            throw new StatusException();

    //übertrage move in die Spielmatrix
        this.status=ChessStatus.ACTIVE;
    }

    @Override
    public void receiveRochade(int from) throws IOException, StatusException {
        if(this.status != ChessStatus.PASSIVE)
            throw new StatusException();

    //übertrage move in die Spielmatrix
        this.status=ChessStatus.ACTIVE;
    }

    @Override
    public void receiveEndGame(int reason) throws IOException, StatusException {
        if(this.status != ChessStatus.PASSIVE)
            throw new StatusException();
        if(reason<0||reason>2)
            throw new IOException("reason must be between 0 and 2");

        this.status=ChessStatus.END;
    }

    @Override
    public void receiveProposalEnd(int reason) throws IOException, StatusException {
        if(this.status != ChessStatus.PASSIVE)
            throw new StatusException();
        if(reason<0||reason>1)
            throw new IOException("reason must be 0 or 1");

        this.status=ChessStatus.ANSWERING;
    }

    @Override
    public void receiveProposalAnswer(boolean accept) throws StatusException {
        if(this.status != ChessStatus.WAITING)
            throw new StatusException();
        if(accept==true)
            this.status=ChessStatus.END;
        else
            this.status=ChessStatus.ACTIVE;
    }


    //SENDERSEITE

    @Override
    public void sendDice(int number) throws IOException, StatusException {
        if(this.status != ChessStatus.START)
            throw new StatusException();

        this.sentDice=number;
        //sende per TCP
        this.status=ChessStatus.SENT_DICE;
    }

    @Override
    public void sendChooseColor(boolean white) throws IOException, StatusException {
        if(this.status != ChessStatus.START_CHOOSING_COLOR)
            throw new StatusException();
        this.white=white;
        //sende per TCP
        if(white==true)
            this.status=ChessStatus.ACTIVE;
        else
            this.status=ChessStatus.PASSIVE;
    }

    @Override
    public void sendMove(int from, int to) throws IOException, StatusException {
        if(this.status != ChessStatus.ACTIVE)
            throw new StatusException();
        //sende move

        this.status=ChessStatus.PASSIVE;
    }

    @Override
    public void sendMovePawnRule(int from, int to, int figureType) throws IOException, StatusException {
        if(this.status != ChessStatus.ACTIVE)
            throw new StatusException();

        //sende move
        this.status=ChessStatus.PASSIVE;
    }

    @Override
    public void sendRochade(int from) throws IOException, StatusException {
        if(this.status != ChessStatus.ACTIVE)
            throw new StatusException();

        //sende move
        this.status=ChessStatus.PASSIVE;
    }

    @Override
    public void sendEndGame(int reason) throws IOException, StatusException {
        if(this.status != ChessStatus.ACTIVE)
            throw new StatusException();

        //sende Ende
        this.status=ChessStatus.END;

    }

    @Override
    public void sendProposalEnd(int reason) throws IOException, StatusException {
        if(this.status != ChessStatus.ACTIVE)
            throw new StatusException();

        //sende proposal
        this.status=ChessStatus.WAITING;

    }

    @Override
    public void sendProposalAnswer(boolean accept) throws IOException, StatusException {
        if(this.status != ChessStatus.ANSWERING)
            throw new StatusException();

        //sende answer
        if(accept==true)
            this.status=ChessStatus.END;
        else
            this.status=ChessStatus.PASSIVE;
    }
}
