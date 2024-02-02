# Programming Project 2022-23

## The Goal
To create a 2D Chess Game in Java.

## Group Members
This Project was developed by:
* Sharjeel Zahid Mahmood
* Ridvan Plluzhina
* Fabio Schick

## Project Description
This Project is a chess game developed in Java completely, featuring a Graphical User Interface where two players can play a chess game.  <br>
The application enforces standard chess rules and provides a visually appealing game board.

## Building and Running the Project

### Prerequisites
* To run this application, you need to have Java and Java Development Kit(JDK) 17 pre-installed on your computer.<br>
If you don't have Java already installed, you can download it at [oracle.com](https://www.oracle.com/java/technologies/downloads/).

### Build and Run
Follow these instructions to run the application
1. Clone the repository to your local machine using:<br>
   ```git clone https://github.com/mrschick/ChessGame-2023.git```<br>
2. To run the application via maven commands follow these instructions:
   * In your terminal, navigate inside the directory ChessGame-2023.
   * Clean the repository using the command:<br> ```mvn clean```<br>
   * Build the project using the command:<br> ```mvn package```<br>
   * Finally, to run the application type in the command: <br> ```mvn exec: java```<br>
   * If this doesn't work, type in the command: <br>  ```java -jar ./target/ChessGame-1.0-jar-with-dependencies.jar```
    
3. You can run the tests with the command: <br> ```mvn tests``` <br>
4. JavaDocs can be found inside the directory ChessGame-1.0/apidocs.

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
class uses these square objects to create a functioning 2D chess board.

#### Programming Techniques Used:
We used the following techniques while developing our project:
* __Interface__ was used to allow for uniform implementation of all Chess piece Objects.
* __Generic Methods__ were used to allow for different chess peices to be used
* __Collections__ were used as they allowed store and manage objects such as chess board squares.
* __Try-catch blocks__ were an integral part of the code as they allowed for smooth running of the code when an error <br>
  was already expected.
* __Method Overriding__ was used as it allowed for a uniform implementaion of all Chess piece classes while implementing <br>
 an interface
* __Lambda Expressions__ were used for easier operations on EventListeners.
* __File I/O__ was used to load chess piece images.
* __Component Design Pattern__ was used as it allowed for a heirachical design, increased scalability and uniformity.
* __Graphical User Interface__ was used the core part of the project as it allowed players to interact with the game.
* __Casting__ was often used to convert data types with allowed for a vast range of operations on certain variables.

### Experience
#### Organization
The project was loosely organized, allowing for each memberr to choose what they want to work on.
* __Sharjeel Zahid Mahmood:__ My responsibilities encompassed the design of the structure and implementation of the  <br>
  Java classes that created chess objects, with a particular focus on the backend to ensure seamless execution of the  <br>
  chess game logic. I worked with the team on desingn efforts and integrated the backend with the frontend for a <br>
  visually appealing chess game and a smooth user interaction. Additionally, I collaborated with team members to <br>
  integrate and optimize the project using Maven, ensuring a streamlined and efficient development process. <br>
* __Ridvan Plluzhina:__
* __Fabio Schick:__

#### Git 
Git was mostly used for version control, creating branches for working on each scope of the project. Pull requests were <br>
used to review and merge the specific braches into master.

#### Challanges
* __Sharjeel Zahid Mahmood:__ I found it difficult to come up with the logic for the implementation of chess rules. But, <br>
  overtime it became easier to come up with these solutions.
* __Ridvan Plluzhina:__ Creating a chess game in Java was a great learning experience for me. I got to dive deeper into programming concepts like OOP, which is about organizing code by creating "classes" that represent different parts of the game, such as the chessboard and the pieces. This approach helped me understand how to make these parts work together smoothly.
I also explored JavaFX, a tool for building the game's interface, which is what players see and interact with. It was exciting to see how code turns into a visual game that people can play.
The project had its tough moments, especially when dealing with the complex rules of chess. Each piece moves differently, and I had to ensure the game followed these rules correctly. Another challenge was designing the game so that it looked good and was easy for players to use. Despite these challenges, overcoming them was incredibly satisfying. Through this project, I learned not just about programming, but also about problem-solving and persistence.
* __Fabio Schick:__ 
