import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {
    @Test
    void getTableString(){
        OX ox = new OX();
        assertEquals(" 012\n" +
                "0---\n" +
                "1---\n" +
                "2---\n", ox.getTableString());
    }

    @Test
    void getCurrentPlayer(){
        OX ox = new OX();
        assertEquals("X", ox.getCurrentPlayer());
    }

    @Test
    void switchPlayer(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals("O", ox.getCurrentPlayer());
    }

    @Test
    void putAt0_0(){
        OX ox = new OX();
        ox.put(0,0);
        assertEquals(" 012\n" +
                "0X--\n" +
                "1---\n" +
                "2---\n", ox.getTableString());
    }

    @Test
    void putAt0_0Twice(){
        OX ox = new OX();
        assertTrue(ox.put(0, 0));
        assertFalse(ox.put(0, 0));
    }

    @Test
    void putOverTable(){
        OX ox = new OX();
        assertFalse(ox.put(-1, 0));
        assertFalse(ox.put(3, 0));
        assertFalse(ox.put(-1, 3));
        assertFalse(ox.put(3, -1));
    }

    @Test
    void getAt0_0(){
        OX ox = new OX();
        ox.put(0,0);
        assertEquals(" 012\n" +
                "0X--\n" +
                "1---\n" +
                "2---\n", ox.getTableString());
        assertEquals("X",ox.get(0,0));
    }

    @Test
    void getOver(){
        OX ox = new OX();
        assertNull(ox.get(-1, 0));
        assertNull(ox.get(3, 0));
        assertNull(ox.get(-1, 3));
        assertNull(ox.get(3, -1));
    }

    @Test
    void checkWinCol0(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,0);
        ox.put(2,0);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(1,0));
        assertTrue(ox.checkWin(2,0));
        assertFalse(ox.checkWin(0,1));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(2,1));
    }

    @Test
    void checkWinCol2(){
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,2);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,2));
        assertTrue(ox.checkWin(1,2));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(0,1));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(2,1));
    }

    @Test
    void checkWinRow2(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(2,1));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(0,1));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinES(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(2,2));
    }

    @Test
    void checkWinSS(){
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,1);
        ox.put(2,0);
        assertTrue(ox.checkWin(0,2));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(2,0));
    }

    @Test
    void reset()
    {
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,1);
        ox.put(2,0);
        ox.reset();
        assertEquals(" 012\n" +
                "0---\n" +
                "1---\n" +
                "2---\n", ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getTurnCount());
    }

    @Test
    void getTurnCount(){
        OX ox = new OX();
        assertEquals(0,ox.getTurnCount());
        ox.put(0,0);
        assertEquals(1,ox.getTurnCount());
    }

    @Test
    void isDraw(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);

        assertTrue(ox.isDraw());
    }

    @Test
    void getScoreX(){
        OX ox = new OX();
        assertEquals(0,ox.getScoreX());
        ox.put(0,0);
        ox.put(1,0);
        ox.put(2,0);
        assertEquals(1,ox.getScoreX());
    }

    @Test
    void getScoreO(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals(0,ox.getScoreO());
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreO());
    }

    @Test
    void getScoreDraw(){
        OX ox = new OX();

        assertEquals(0,ox.getScoreDraw());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
        assertEquals(1,ox.getScoreDraw());
    }
}