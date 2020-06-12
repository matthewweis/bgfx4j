package com.bariumhoof.bgfx4j.layout;

public interface Num {

    ONE ONE = new ONE(){};
    TWO TWO = new TWO(){};
    THREE THREE = new THREE(){};
    FOUR FOUR = new FOUR(){};

    int value();

    interface ONE extends Num { default int value(){ return 1; } }
    interface TWO extends Num { default int value(){ return 2; } }
    interface THREE extends Num { default int value(){ return 3; } }
    interface FOUR extends Num { default int value(){ return 4; } }
}