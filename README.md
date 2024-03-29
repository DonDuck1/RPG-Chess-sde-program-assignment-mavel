# RPG Chess

To finish the course "Software Design" we were tasked with making a program using some of the design patterns we had learnt. We had to work in pairs, in order to be able to discuss with one another about how and when to apply the design patterns, which would hopefully result in a better program. 

The program should run in a java console. We decided on making a chess program, but with our own twist. It's the normal chess board and pieces you know and love, but with RPG mechanics integrated into it. The program's rules should be as follows:

    There are 2 players: a white and a black player. There is no AI, so you can only play 
    against yourself, locally against someone sitting next to you or online by streaming
    the program via for example discord.

    Per turn the player can move once and attack once, or move once and do a special action once
        - This does not have to be done with the same piece
        - The player must always move a piece, attacking or doing a special action is voluntary

    Pieces can move like normal chess pieces
        - Except for that the pawn can always only move 1 spot. It can't move 2 spots from its
        starting position.
        - Castling (with a rook and a king) is not possible
        - When reaching the other side of the board with a pawn, nothing happens.

    If a piece lands on an enemy piece they perform an attack using their damage against the 
    opponent's health and armour
        - The pawn is of course an exception, only moving forward and only hitting diagonally up 
        left or up right.
        - If the damage the piece does isn't enough to kill the enemy piece, neither piece moves

    Pieces can perform piece-specific special actions on any piece they could land on. This means 
    they don't actually move. We wanted all the pieces to have a special action, which can be seen 
    below. Due to time constraints, only the bishop, knight and queen have special actions. Those
    special actions had to be simplified (see even further blow).
        What we thought of:
        Pawn:
            - As usual special ability only hits diagonally
            - Allows the pawn to sacrifice itself to deal double damage to a piece
        Rook:
            - Applies Stunned state to an enemy piece in its movement range
            - Stunned makes the piece unable to move for the next turn, after which it returns to 
            default (healthy) state
        Bishop:
            - Applies Healing state (regeneration) to a friendly piece in its movement range
            - Healing state heals X health per turn for 3 turns, then returns to default (healthy) 
            state
        Knight:
            - Applies Poisoned state to an enemy piece in its movement range
            - Poisoned makes the piece lose X% of its damage stat for 3 turns
            - Afterwards returns to default (healthy) state with its damage returned to normal
        Queen:
            - Can only be used at the start of the turn
            - Generates a random number between 1 and 6
            - If the number is high enough (4, 5 or 6), allows the queen to move/attack twice
            - If not, the player's turn ends without moving/attacking
        King:
            - AOE attack that hits in a 2 square radius around the king and deals large damage
            - Gives the king the Stunned state for the next turn

        What we were able to implement:
        Bishop:
            - Applies Healing state (regeneration) to a friendly piece in its movement range
            - Healing state heals 1 health per turn for 3 turns, then returns to default (healthy) 
            state
        Knight:
            - Applies Poisoned state to an enemy piece in its movement range
            - Poisoned makes the piece lose 50% of its damage stat for 3 turns
            - Afterwards returns to default (healthy) state with its damage returned to normal
        Queen:
            - Applies Stunned state to an enemy piece in its movement range
            - Stunned makes the piece unable to move, attack or do a special action for 1 turn.
            - Afterwards returns to default (healthy) state with its damage returned to normal
    
    The game is won when the king piece of your opponent dies.

The RPG mechanics out themselves in 3 stats: health, armor and damage. The armor stat is implemented in the pieces, but due to time constraints we were unable to actually do anything with it (simply substracting the damage with the armour when running the function that lets a pawn take damage would unbalance the game heavily). The stats for all the pieces are as follows:

    Pawn:
        - Health: 1
        - Armor: 0
        - Damage: 1
    Rook:
        - Health: 3
        - Armor: 3
        - Damage: 2
    Bishop:
        - Health: 3
        - Armor: 0
        - Damage: 2
    Knight:
        - Health: 3
        - Armor: 1
        - Damage: 3
    Queen:
        - Health: 4
        - Armor: 3
        - Damage: 6
    King:
        - Health: 5
        - Armor: 2
        - Damage: 4

Before we go to the actual use of the program via console commands, we'll have to explain how the coordinates work in our chess app. Coordinate 0,0 references the bottom left square on the board. Coordinate 7,7 references the upper right square on the board. The coordinates should be: "x,y".

The possible commands to put in the console are (capital letters required):

    Move {coordinates of square containing the piece to be moved} {coordinates of square to 
    move piece to}

    Attack {coordinates of square containing the attacking piece} {coordinates of square 
    containing the piece to be attacked}

    Special-action {coordinates of square contain the piece doing the special move} {coordinates 
    of square containing the piece to be affected by the special action}

    Restore {turn number}

    Undo-turn

    End

    Stop

Example of using coordinates: Move 0,1 0,2. This will move the piece on the square with coordinates x=0 and y=1 to the square with coordinates x=0 and y=2.

The command "Restore", restores the game to the beginning of a turn that is signified with the given turn number. When starting the game, the game starts with turn 0. When the white player has ended his turn, before the black player makes any actions the new state of the board will be saved as turn 1. You can use "Undo turn" to go back to the start of the current turn. If you're in turn 1 for example and black made his moves but regrets them, you can use this command to go back to the beginning of turn 1 where black hasn't made any moves yet.

The command "End", ends the turn of the current player.

The command "Stop", ends the game and stops all processes in the terminal ("Process finished with exit code 0").

# Cooperation

To write this program in an orderly fashion, we consistently worked with separate branches, with each of us working on one branch at a time, that were merged into the "develop" branch once the feature the branch was for had been finished. This way we would both be able to work at the same time, without problems when committing.

We wrote out a short list of features for the program and set up the rules for the game so that we would know what still had to be done.

Unfortunately, at the end of finishing our project we had to change from an "IntelliJ" project to a "maven" project in order to import a needed dependency. This would cause a lot of merge conflicts if pushed to the same repo. We had to create a new repo for the "maven" project, which does mean that the submissions for only that "maven" project repo aren't an accurate representative of the work we've done.

The first GitHub repo (of the "IntelliJ" project) link: https://github.com/TheMineMan100/sde-program-assignment

The second GitHub repo (of the "maven" project) link: https://github.com/DonDuck1/RPG-Chess-sde-program-assignment-mavel

All in all though, this cooperation went quite well, and we managed to divide the workload fairly evenly.

# Design Patterns

We worked on 6 design patterns in total. The creational patterns are the builder and factory methods. The structural patterns are the bridge and facade methods. The behavioral patterns are the state and memento methods.

## Creational Patterns

### Builder

The builder method(s) can be found in the "builders" folder/package. There is 1 interface and 6 builders for the 6 different pieces. When creating the builder or calling the function reset, a new chess piece is made. With other methods inside the builder you can put values inside the chess piece object. These values are given via parameters to the functions. Via the getResult() you get the built chess piece returned. The builder automatically resets inside the getResult();

### Factory

The factory method(s) can be found in the "factories" folder/package. There is 1 interface and 6 factories for the 6 different pieces. A factory makes use of the relevant builder to fabricate a new piece. This piece is standardized in its values. There are 2 functions in each factory, "createWhitePiece" and "createBlackPiece". In the factories constructor the 2 colors (the colors are objects) and the builder to be used are defined. When using one of the 2 methods, you get a chess piece object returned.

## Structural Patterns

### Bridge

The relevant files for the bridge method can be found in the "allegiances" and "pieces" folders/packages. Every chess piece contains an allegiance object, namely black or white. The allegiance gives methods for moving, attacking and doing special actions (which can be different for either black or white pawns). This prevents us from having classes like "BlackPawnPiece" and "WhitePawnPiece", instead being able to just create a "PawnPiece" with an object of its "Allegiance" inside.

### Facade

Our "Main.java" file is our entry point to the program and a facade. It provides a simplified interface to our program, by enabling the player(s) to input simple commands in the command line and then automatically calling to other classes, instead of forcing the player to call to those classes themselves. For example, when ending the turn the curent player doesn't need to change players or save the current state of the board as a Memento, that's done automatically via 1 command.

## Behavioral Patterns

### State

The state method(s) can be found in the "states" folder/package. Every piece has a state. The "normal" state is the "HealthyState". This has no special effects. Via special actions by the players, pieces can gain different states like "StunnedState" and "PoisonedState", which will stop a piece from moving and make a piece take damage every turn. These states have a certain duration before they change back to the "HealthyState". When a new special action is taken against the pawn before the duration is up, the new State caused by the special action will overwrite the previous "special state". For example when a pawn is in a "PoisonedState" and gets stunned, it will change to the "StunnedState". No effects from the "PoisonedState" will apply after that. This enables most States to be reached by most other States.

### Memento

The memento method can be found in the "board" folder/package. This method enables us to save older versions/states of the chess board. The "Board" class contains 2 relevant functions: a function to set its "squares" to the squares that were provided via a parameter and a method to create a new "Memento" object and thus save the current version of the board. The "Memento" class has a reference to the instantiated board object and has an array of every "square" at the time of saving the board. When the function "restore" of a Memento object is called, it will set the squares of the current board to the squares variable inside the Memento class. A class "HistoryCaretaker" manages all of the saving and restoring. It too contains a reference to the instantiated board object and it has an array "history" filled with Memento objects of every turn. At the beginning of a new turn, the function "saveMemento" calls to the save function of the board and adds the returned "Memento" object to the history array. When its function "restoreMemento" is called, it calls in turn to "restore" function of the Memento of the turn specified in the parameter of the function. "restoreLastMemento" restores the last Memento saved in the "history" array.