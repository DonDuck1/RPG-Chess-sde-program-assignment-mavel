package org.example;

import org.example.board.Board;
import org.example.board.HistoryCaretaker;
import org.example.board.Square;
import org.example.pieces.Piece;
import org.example.states.PieceState;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();

        Board board = new Board();

        board.initialiseBoard();

        board.printBoard();

        writer.writeLine("The white player begins. The white pieces are at the bottom, the black pieces on top. For possible commands, see readme file");

        HistoryCaretaker historyCareTaker = new HistoryCaretaker(board);

        boolean whitePlayerTurn = true;
        boolean hasMoved = false;
        boolean hasAttacked = false;

        historyCareTaker.saveMemento();

        gameLoop(
            reader,
            writer,
            board,
            historyCareTaker,
            whitePlayerTurn,
            hasMoved,
            hasAttacked
        );
    }

    public static void gameLoop(
            ConsoleReader reader,
            ConsoleWriter writer,
            Board board,
            HistoryCaretaker historyCareTaker,
            boolean whitePlayerTurn,
            boolean hasMoved,
            boolean hasAttacked
    ) {
        boolean stopGame = false;

        String incomingCommand = reader.readLine();
        String[] parts = incomingCommand.split(" ");
        if (parts[0].equals("Move")) {
            if(parts.length == 3) {
                boolean errorDetected = false;

                int currentSquareXCoordinate = 0;
                int currentSquareYCoordinate = 0;

                int squareToMoveToXCoordinate = 0;
                int squareToMoveToYCoordinate = 0;
                try {
                    currentSquareXCoordinate = Integer.parseInt(parts[1].split(",")[0]);
                    currentSquareYCoordinate = Integer.parseInt(parts[1].split(",")[1]);

                    squareToMoveToXCoordinate = Integer.parseInt(parts[2].split(",")[0]);
                    squareToMoveToYCoordinate = Integer.parseInt(parts[2].split(",")[1]);
                } catch (NumberFormatException err) {
                    errorDetected = true;
                    writer.writeLine("Invalid integer input");
                }
                if (!errorDetected) {
                    if (!hasMoved) {
                        Square currentSquare = board.getSquare(currentSquareXCoordinate, currentSquareYCoordinate);
                        Square squareToMoveTo = board.getSquare(squareToMoveToXCoordinate, squareToMoveToYCoordinate);

                        Piece piece = currentSquare.getPiece();

                        if (piece != null) {
                            if (
                                (piece.getAllegiance().getClass().getSimpleName().equals("White") &&
                                    whitePlayerTurn) ||
                                (piece.getAllegiance().getClass().getSimpleName().equals("Black") &&
                                    !whitePlayerTurn)
                            ) {
                                piece.move(currentSquare, squareToMoveTo, board.getSquares());

                                if (squareToMoveTo.getPiece() == piece) {
                                    board.printBoard();
                                    hasMoved = true;
                                } else {
                                    writer.writeLine("Could not move piece");
                                }
                            } else {
                                writer.writeLine("Can not move piece of other player");
                            }
                        } else {
                            writer.writeLine("No piece detected on selected square");
                        }
                    } else {
                        writer.writeLine("You may only move once per turn");
                    }
                }
            } else {
                if (parts.length < 3) {
                    writer.writeLine("Command missing parameters");
                } else {
                    writer.writeLine("Command contains too many parameters");
                }
            }
        } else if (parts[0].equals("Attack")) {
            if(parts.length == 3) {
                boolean errorDetected = false;

                int currentSquareXCoordinate = 0;
                int currentSquareYCoordinate = 0;

                int squareToAttackXCoordinate = 0;
                int squareToAttackYCoordinate = 0;
                try {
                    currentSquareXCoordinate = Integer.parseInt(parts[1].split(",")[0]);
                    currentSquareYCoordinate = Integer.parseInt(parts[1].split(",")[1]);

                    squareToAttackXCoordinate = Integer.parseInt(parts[2].split(",")[0]);
                    squareToAttackYCoordinate = Integer.parseInt(parts[2].split(",")[1]);
                } catch (NumberFormatException err) {
                    errorDetected = true;
                    writer.writeLine("Invalid integer input");
                }
                if (!errorDetected) {
                    if (!hasAttacked) {
                        Square currentSquare = board.getSquare(currentSquareXCoordinate, currentSquareYCoordinate);
                        Square squareToAttack = board.getSquare(squareToAttackXCoordinate, squareToAttackYCoordinate);

                        Piece piece = currentSquare.getPiece();
                        Piece pieceToAttack = squareToAttack.getPiece();

                        if (piece != null) {
                            if (pieceToAttack != null ) {
                                if (
                                    (piece.getAllegiance().getClass().getSimpleName().equals("White") &&
                                        whitePlayerTurn) ||
                                    (piece.getAllegiance().getClass().getSimpleName().equals("Black") &&
                                        !whitePlayerTurn)
                                ) {
                                    double startingHealthOfAttackedPawn = pieceToAttack.getHealth();
                                    piece.attack(currentSquare, squareToAttack, board.getSquares());

                                    if (pieceToAttack.getHealth() < startingHealthOfAttackedPawn) {
                                        if (pieceToAttack.getHealth() < 0) {
                                            squareToAttack.setPiece(currentSquare.getPiece());
                                            currentSquare.setPiece(null);

                                            board.printBoard();

                                            writer.writeLine("You killed an enemy");
                                        } else {
                                            board.printBoard();
                                            writer.writeLine("You attacked an enemy");
                                        }
                                        hasAttacked = true;
                                    } else {
                                        writer.writeLine("Could not attack piece on selected square");
                                    }
                                } else {
                                    writer.writeLine("Can not attack using piece of the other player");
                                }
                            } else {
                                writer.writeLine("No piece detected on the attacked square");
                            }
                        } else {
                            writer.writeLine("No piece detected on selected square");
                        }
                    } else {
                        writer.writeLine("You may only attack once per turn");
                    }
                }
            } else {
                if (parts.length < 3) {
                    writer.writeLine("Command missing parameters");
                } else {
                    writer.writeLine("Command contains too many parameters");
                }
            }
        } else if (parts[0].equals("Special-action")) {
            if(parts.length == 3) {
                boolean errorDetected = false;

                int currentSquareXCoordinate = 0;
                int currentSquareYCoordinate = 0;

                int squareToAffectXCoordinate = 0;
                int squareToAffectYCoordinate = 0;
                try {
                    currentSquareXCoordinate = Integer.parseInt(parts[1].split(",")[0]);
                    currentSquareYCoordinate = Integer.parseInt(parts[1].split(",")[1]);

                    squareToAffectXCoordinate = Integer.parseInt(parts[2].split(",")[0]);
                    squareToAffectYCoordinate = Integer.parseInt(parts[2].split(",")[1]);
                } catch (NumberFormatException err) {
                    errorDetected = true;
                    writer.writeLine("Invalid integer input");
                }
                if (!errorDetected) {
                    if (!hasAttacked) {
                        Square currentSquare = board.getSquare(currentSquareXCoordinate, currentSquareYCoordinate);
                        Square squareToAffect = board.getSquare(squareToAffectXCoordinate, squareToAffectYCoordinate);

                        Piece piece = currentSquare.getPiece();
                        Piece pieceToAffect = squareToAffect.getPiece();

                        if (piece != null) {
                            if (pieceToAffect != null ) {
                                if (
                                    (piece.getAllegiance().getClass().getSimpleName().equals("White") &&
                                        whitePlayerTurn) ||
                                    (piece.getAllegiance().getClass().getSimpleName().equals("Black") &&
                                        !whitePlayerTurn)
                                ) {
                                    PieceState startingStateOfAffectedPawn = pieceToAffect.getState();
                                    piece.specialAction(currentSquare, squareToAffect, board.getSquares());

                                    if (
                                        pieceToAffect.getState() != startingStateOfAffectedPawn ||
                                        (
                                            pieceToAffect.getState() == startingStateOfAffectedPawn &&
                                            pieceToAffect.getState().getDuration() != startingStateOfAffectedPawn.getDuration()
                                        )
                                    ) {
                                        board.printBoard();

                                        writer.writeLine("You successfully did a special action that affected a piece");

                                        hasAttacked = true;
                                    } else {
                                        writer.writeLine("Could not affect piece on selected square");
                                    }
                                } else {
                                    writer.writeLine("Can not attack using piece of the other player");
                                }
                            } else {
                                writer.writeLine("No piece detected on the attacked square");
                            }
                        } else {
                            writer.writeLine("No piece detected on selected square");
                        }
                    } else {
                        writer.writeLine("You may only attack or do a special action once per turn");
                    }
                }
            } else {
                if (parts.length < 3) {
                    writer.writeLine("Command missing parameters");
                } else {
                    writer.writeLine("Command contains too many parameters");
                }
            }
        } else if (parts[0].equals("Restore")) {
            if(parts.length == 2) {
                boolean errorDetected = false;

                int turn = 0;
                try {
                    turn = Integer.parseInt(parts[1]);
                } catch (NumberFormatException err) {
                    errorDetected = true;
                    writer.writeLine("Invalid integer input");
                }
                if (!errorDetected) {
                    historyCareTaker.restoreMemento(turn);

                    board.printBoard();

                    if (turn % 2 == 0) {
                        whitePlayerTurn = true;
                        writer.writeLine("The white player can now move and attack");
                    } else {
                        whitePlayerTurn = false;
                        writer.writeLine("The black player can now move and attack");
                    }
                    hasMoved = false;
                    hasAttacked = false;
                }
            } else {
                if (parts.length < 2) {
                    writer.writeLine("Command missing parameters");
                } else {
                    writer.writeLine("Command contains too many parameters");
                }
            }
        } else if (parts[0].equals("Undo-turn")) {
            if(parts.length == 1) {

                historyCareTaker.restoreLastMemento();
                hasMoved = false;
                hasAttacked = false;

                board.printBoard();
            } else {
                if (parts.length < 1) {
                    writer.writeLine("How did you manage this? Too few parameters??");
                } else {
                    writer.writeLine("Command contains too many parameters");
                }
            }
        } else if (parts[0].equals("End")) {
            if (hasMoved) {
                if (whitePlayerTurn) {
                    whitePlayerTurn = false;
                    hasMoved = false;
                    hasAttacked = false;
                    writer.writeLine("The black player can now take a turn.");
                } else {
                    whitePlayerTurn = true;
                    hasMoved = false;
                    hasAttacked = false;
                    writer.writeLine("The white player can now take a turn.");
                }
                historyCareTaker.saveMemento();
            } else {
                writer.writeLine("You HAVE to move");
            }
        } else if (parts[0].equals("Stop")) {
            stopGame = true;
        } else {
            writer.writeLine("Command not recognized");
        }

        if (!stopGame) {
            gameLoop(
                    reader,
                    writer,
                    board,
                    historyCareTaker,
                    whitePlayerTurn,
                    hasMoved,
                    hasAttacked
            );
        }
    }
}