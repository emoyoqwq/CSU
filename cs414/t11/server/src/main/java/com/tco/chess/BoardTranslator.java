package com.tco.chess;
import java.util.*;

public class BoardTranslator {
    
    private static BoardTranslator uniqueInstance = new BoardTranslator();
    
    private BoardTranslator(){}

    public static BoardTranslator getInstance() {
        return uniqueInstance;
    }

    public static String[][] positionObjectTo2DArray(String positionObject) {
         
        String resultWithoutBrackets = positionObject.replaceAll("[(){}]","");
        
    
        String[][] arrBoard = new String[8][];
         for(int i = 0 ; i < 8  ;i ++){
             arrBoard[i] = new String[8];
        }
        String[] rowlst = resultWithoutBrackets.split(",");
         for(int i = 0 ; i < rowlst.length; i++){
            String[] sublst = rowlst[i].trim().split(":");
            if(sublst.length != 2)
                continue;
                sublst[0] = sublst[0].trim();
                int index1 = sublst[0].charAt(0) - 'a';
                int index2 = sublst[0].charAt(1) - '1';
                arrBoard[index1][index2] = sublst[1].trim();
         }
        
        return arrBoard;    
    }

    public static String TwoDArrayToPositionObject(String[][] chess2DArray) {
         
    Map<String, String> board = new HashMap<String, String>();
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if(chess2DArray[i][j] == null)
                    continue;
                char first = (char)('a' + i);
                char second = (char)('1' + j);
                String key = "";
                key += first;
                key += second;
                board.put(key,chess2DArray[i][j]);
             }
         }
        String positionObject = "";
        for(Map.Entry<String, String> entry: board.entrySet()){
            
            String positionObjectString = (entry.getKey() + ": " + "'" + entry.getValue() + "'") + ", ";
            positionObject += positionObjectString;
            
        }
        positionObject = "{{ " + positionObject.substring(0, positionObject.length()-2) + " }}";
        
        return positionObject;
        
      }
    }
