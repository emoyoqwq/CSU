package com.tco.chess;
import com.tco.chess.BoardTranslator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class BoardTranslatorTest {
  
    @Test
    void testPositionObjectTo2DArray() {
        BoardTranslator brd = BoardTranslator.getInstance();
        String testPositionObject =  "{{ e5: 'wK', a2: 'bP', a3: 'wP' , e4: 'wP', e7: 'bK' }}";
        String[][] actual = brd.positionObjectTo2DArray(testPositionObject);
        String[][] expected = new String[8][8];
        expected[0][1]= "'bP'"; 
        expected[0][2]= "'wP'";
        expected[4][3]= "'wP'";
        expected[4][4]= "'wK'";
        expected[4][6]= "'bK'";
        assertArrayEquals(expected, actual);
    }
  
    @Test
    void test2DArrayToPositionObject() {
    BoardTranslator convert2DToPosObj = BoardTranslator.getInstance();
    String[][] testChess2DArray = new String[8][8];
    testChess2DArray[0][1]= "bP"; 
    testChess2DArray[0][2]= "wP";
    testChess2DArray[4][3]= "wP";
    testChess2DArray[4][4]= "wK";
    testChess2DArray[4][6]= "bK";
    String actual = convert2DToPosObj.TwoDArrayToPositionObject(testChess2DArray);
    String expected = "{{ e5: 'wK', a2: 'bP', a3: 'wP', e7: 'bK', e4: 'wP' }}";
    assertEquals(expected, actual);
    }
}
