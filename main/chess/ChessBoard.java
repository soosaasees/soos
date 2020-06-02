package chess;

import chess.pieces.ChessPiece;
import chess.pieces.*;

import java.io.IOException;

public class ChessBoard implements ChessBoardInt{
    private static int BOARDROWS=8;
    private static int BOARDCOLLUMNS=8;
    public ChessPiece[][] board;
    ChessBoard()    {
        this.board = new ChessPiece[BOARDROWS][BOARDCOLLUMNS];
        board[0][0]=new Rook(true);
        board[0][1]=new Knight(true);
        board[0][2]=new Bishop(true);
        board[0][3]=new Queen(true);
        board[0][4]=new King(true);
        board[0][5]=new Bishop(true);
        board[0][6]=new Knight(true);
        board[0][7]=new Rook(true);
        for(int i=0;i<BOARDCOLLUMNS;i++) {
            board[1][i]=new Pawn(true);
            board[6][i]=new Pawn(false);
        }
        board[7][0]=new Rook(false);
        board[7][1]=new Knight(false);
        board[7][2]=new Bishop(false);
        board[7][3]=new Queen(false);
        board[7][4]=new King(false);
        board[7][5]=new Bishop(false);
        board[7][6]=new Knight(false);
        board[7][7]=new Rook(false);
    }
    @Override
    public void printBoard() {
        System.out.print("    A    B    C   D    E    F   G    H \n");
        for(int i=board.length-1;i>=0;i--)  {
            System.out.print((i+1)+" |");
            for(int j=0;j<board[0].length;j++) {
                if(board[i][j]!=null)
                    System.out.printf("_%s_|",board[i][j].toString());
                else
                    System.out.print("_\u2001_|");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void check() {

    }

    @Override
    public void move(int from, int to) throws IOException {
        int[] fromPos=parse(from);
        int[] toPos=parse(to);

        if(board[fromPos[0]][fromPos[1]].check(board, fromPos, toPos)==false)
            throw new IOException();
        board[toPos[0]][toPos[1]]=board[fromPos[0]][fromPos[1]];
    }

    @Override
    public void movePawnRule(int from, int to, int figureType) {

    }

    @Override
    public void rochade(int from) {

    }

    //parsed int zu entsprechender Position in Matrix als Array, mit Zeilen in erster, Spalten in zweiter Position
    private int[] parse(int x)   {
        int[] out=new int[2];
        int a=0;
        int b=0;
        while(x>0)  {
            if(a!=7)
                a=a+1;
            else  {
                a=0;
                b=b+1;
            }
        }
        out[0]=b;
        out[1]=a;
        return out;
    }

    public boolean posWhite(int pos)    {
        return board[parse(pos)[0]][parse(pos)[1]].getWhite();
    }
}
