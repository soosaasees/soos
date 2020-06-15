package chess;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ChessTest {

    @Test
    public void usageTest() throws ChessException, StatusException, IOException {
        ShortCut sender1 = new ShortCut();
        ChessEngine game1 = new ChessEngine(sender1);

        ShortCut sender2 = new ShortCut();
        ChessEngine game2 = new ChessEngine(sender2);

        //conect both games
        sender1.setReceiver(game2);
        sender2.setReceiver(game1);

        //test dice
        game1.doDice();
        game2.doDice();

        //choosing color
        ChessUsage choosingColor = game1.is(ChessStatus.START_CHOOSING_COLOR) ? game1 : game2;
        choosingColor.chooseColor(true);

        ChessUsage activeGame = game1.is(ChessStatus.ACTIVE) ? game1 : game2;

        activeGame.move(8, 16);
        Assert.assertFalse(activeGame.is(ChessStatus.ACTIVE));
    }
}
