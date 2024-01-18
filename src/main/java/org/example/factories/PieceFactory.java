package org.example.factories;

import org.example.builders.PieceBuilder;
import org.example.pieces.Piece;

public interface PieceFactory<T> {
    public Piece createWhitePiece();
    public Piece createBlackPiece();
}
