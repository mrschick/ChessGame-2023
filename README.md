# Programming Project 2022-23

## The Goal
To create a 2D Chess Game in Java.

## Group Members
* Sharjeel Zahid Mahmood
* Ridvan Plluzhina
* Fabio Schick

## Project Description
This Project is a chess game developed in Java completely, featuring a Graphical User Interface where two players can play a chess game.  <br>
The application enforces standard chess rules and provides a visually appealing game board.

## Building and Running the Project

### Prerequisites
* Java Development Kit(JDK) 17.
* An IDE such as IntelliJ IDEA.

### Build and Run
1. Clone the repository to your local machine using:   git clone https://github.com/mrschick/ChessGame-2023.git-2
2. You can run the project using one of the 2 ways below.  <br>
   * Open the Project in your IDE, then compile and run the GUIStarter.java  <br>
   * Navigate inside the directory ChessGame-2023 and run the command:  <br>
     mvn exec:java
   * JavaDocs can be found inside the folder ChessGame-2023/target/classes/apidocs
3. #### Maven:  <br>
    * To build the project with maven, inside the ChessGame-2023 repository use the commands:  <br>
       mvn compile  <br>
       mvn install
    * Extract the .jar file "ChessGame-1.0.jar" to a directory of your choice.
    * JavaDocs can be found inside the directory: ChessGame-1.0/apidocs

### User's Guide
1. Launch the application
2. Click on the Button "Start Game"
3. The Black Player has the first turn.
4. The White Player gets the second turn.
5. Click on the chess piece that you want to move.  <br>
   * The square with a yellow outline means a move is available on that square.
   * The square with a green outline means a kill is available on that square.
   * The king with a yellow outline means that the king is under check.
6. If any pawn reaches the other end of board, choose the piece you want to promote your pawn to by clicking on the piece.
7. The game ends when one of the players is checkmated.

### Project Implementation
We implemented the projected Java and the javafx library for the Graphical User Interface. All the chess piece clasess  <br>
implement the interface "Piece.java". This allows for generic return types and generic parameters to be used in methods.<br>
The Sqaure.java class is used to create chess board squares that can contain chess pieces or can be empty. The Board    <br>
class uses these square objects to create a functioning 2D chess board. No third party libraries were used.

### Organization
The project was loosely organized, allowing for each memberr to choose what they want to work on.

### Git 
Git was mostly used for version control, creating branches for working on each scope of the project. Pull requests were <br>
used to review and merge the specific braches into master.

### Challanges
* Sharjeel Zahid Mahmood: I found it difficult to come up with the logic for the implementation of chess rules. But, <br>
  overtime it became easier to come up with these solutions.
