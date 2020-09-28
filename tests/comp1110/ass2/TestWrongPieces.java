package comp1110.ass2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestWrongPieces {
    String[][] questionBank_noPiece = {{"","B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N","BSGSiSLNnEoSpErWSNyN"},
            {"","B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W","BNgSiElWNWoEPSrWsSYW"},
            {"","b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N","bWGSISlWnNoWPErESSyN"},
            {"","B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N","BWGSiSlSNWoNpErSSNyN"},
            {"","B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E","BSgSINlWNNoEpNRWsNyE"}};

    String[][] questionBank_withWrongPieces = {{"B03SG50Si52SL00Nn31Eo63Sp20Er41SS40Ny62W","B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N","GSnErWyN"},
            {"B00NI82El01WO60EP03SS43SY80W","B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W","gSiENWoErWsS"},
            {"G60SI03Sl00Wo31WR80Es43Sy42N","b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N","bWGSnNPErESS"},
            {"b81WI03SP10Er80ES43Sy42N","B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N","BWGSiSlSNWoNpErSSNyN"},
            {"b23SG30SI42Nl82W","B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E","BSgSNNoEpNRWsNyE"}};

    String[][] questionBank_onlyCorrectPieces = {{"B03SG70Si52SL00Nn01Er41WS40Ny62N","B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N","oSpE"},
            {"B00Ng11Sl01WP03Sr40Ws43S","B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W","iENWoEYW"},
            {"b81WI03SP10Er80ES43Sy42N","b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N","GSlWnNoW"},
            {"G03Si32SN72Wo11Np81E","B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N","BWlSrSSNyN"},
            {"B23Sg30Sp00NR01Ws50Ny61E","B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E","INlWNNoE"}};

    String[][] questionBank_finishedStatement = {{"B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N","B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N",""},
            {"B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W","B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W",""},
            {"b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N","b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N",""},
            {"B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N","B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N",""},
            {"B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E","B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E",""}};

    @Test
    public void testEmptyString() {
        for (int i = 0; i < 5; i++) {
            assertFalse("Method is wrong when the current string is empty ", !questionBank_noPiece[i][2].equals(FitGame.findWrongPieces(questionBank_noPiece[i][0],questionBank_noPiece[i][1])));
        }
    }

    @Test
    public void testWrongString() {
        for (int i = 0; i < 5; i++) {
            assertFalse("Method is wrong when it includes wrong pieces ", !questionBank_withWrongPieces[i][2].equals(FitGame.findWrongPieces(questionBank_withWrongPieces[i][0], questionBank_withWrongPieces[i][1])));
        }
    }

    @Test
    public void testMissingString() {
        for (int i = 0; i < 5; i++) {
            assertFalse("Method is wrong when there's only missing pieces ", !questionBank_onlyCorrectPieces[i][2].equals(FitGame.findWrongPieces(questionBank_onlyCorrectPieces[i][0], questionBank_onlyCorrectPieces[i][1])));
        }
    }

    @Test
    public void testFinishedString() {
        for (int i = 0; i < 5; i++) {
            assertFalse("Method is wrong when the pieces are all well placed ",!questionBank_finishedStatement[i][2].equals(FitGame.findWrongPieces(questionBank_finishedStatement[i][0],questionBank_finishedStatement[i][1])));
        }
    }

}
