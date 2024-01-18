package org.example.board;

import org.apache.commons.lang3.SerializationUtils;

public class Memento {
    private Board board;

    private Square[][] squares;

    public Memento(Board board, Square[][] squares) {
        this.board = board;
        this.squares = squares;
    }

    public void restore() {
        Square[][] squaresCopy = SerializationUtils.clone(this.squares);
        this.board.setSquares(squaresCopy);
    }
}
