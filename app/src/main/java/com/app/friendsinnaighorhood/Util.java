package com.app.friendsinnaighorhood;

import com.app.friendsinnaighorhood.grapgh.Index;

/**
 * Created by apps on 31/5/16.
 */
public class Util {

    public static Index getRowColFromIndex(int item, int maxRowSize){
        Index indexRC = new Index();
        indexRC.setI(item/maxRowSize);
        indexRC.setJ(item%maxRowSize);
        return indexRC;
    }

    public static int getIndexFromRowCol(Index indexRC, int maxRowSize){
        return indexRC.getI()*maxRowSize + indexRC.getJ();
    }
}
