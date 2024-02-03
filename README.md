# UNIBZ Programming Project 2023

## The Goal
To create a 2D Chess Game in Java, by applying the knowledge gained in the "Programming Project" course at [UNIBZ](https://www.unibz.it/), during the Academic Year 2023.

## Group Members
This Project was developed by:
* Sharjeel Zahid Mahmood
* Ridvan Plluzhina
* Fabio Schick

## Project Description
This Project is a chess game developed entirely in Java, featuring a Graphical User Interface on which two players can play chess.
The application enforces standard chess rules and provides a visually appealing game board.

## Demo video:
```https://youtu.be/ANdLiSBN52g```

## Building and Running the Project

### Prerequisites
* To build this application, you need to have both [Java](https://www.java.com/en/download/) and the [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) pre-installed on your computer.

### Build and Run
Follow these instructions to run the application
1. Clone the repository to your local machine using:<br>
   ```git clone https://github.com/mrschick/ChessGame-2023.git```
2. To run the application via maven commands follow these instructions:
   * In your terminal, navigate into the main directory of the cloned `ChessGame-2023` repository.
   * Clean the repository using the command:<br>
     ```mvn clean```
   * Assemble a single-file `.jar` package of the project by using the command:<br>
     ```mvn package```
   * Finally, to run the application type in the command:<br>
     ```mvn exec:java```
   * If this doesn't work, type in the command:<br>
     ```java -jar ./target/ChessGame-1.0-jar-with-dependencies.jar```
    
3. You can run the unit tests with the command:<br>
   ```mvn test```
4. JavaDocs can be found inside the directory `ChessGame-1.0/apidocs`.

### Run packaged release
Alternatively, you can download the latest packaged `.jar` file from the [release section](https://github.com/mrschick/ChessGame-2023/releases). It contains all data needed to run the game and only requires Java to be installed.

### User's Guide
1. Launch the application
2. Click on the Button "Start Game"
3. The White Player has the first turn.
4. The Black Player gets the second turn.
5. Click on the chess piece that you want to move.
   * The square with a yellow outline means a move is available on that square.
   * The square with a green outline means a kill is available on that square.
   * The king with a yellow outline means that the king is under check.
6. If any pawn reaches the other end of board, choose the piece you want to promote your pawn to by clicking on the piece.
7. The game ends when one of the players is checkmated.

### Project Implementation
We implemented the projected Java and the javafx library for the Graphical User Interface. All the chess piece classes implement the interface "Piece.java". This allows for generic return types and generic parameters to be used in methods. The Sqaure.java class is used to create chess board squares that can contain chess pieces or can be empty. The Board class uses these square objects to create a functioning 2D chess board.

#### Third Party Libraries:
The following maven plugins were used:
* org.apache.maven.plugins:maven-compiler-plugin (Version: 3.8.1)
* org.apache.maven.plugins:maven-resources-plugin (Version: 3.2.0)
* org.apache.maven.plugins:maven-surefire-plugin (Version: 3.0.0)
* org.codehaus.mojo: exec-maven-plugin (Version: 3.1.1)
* org.apache.maven.plugins:maven-javadoc-plugin (Version: 3.6.3)
In addition, we used the following dependencies:
* org.junit.jupiter:junit-jupiter-api (Version: 5.7.0)
* org.junit.jupiter:junit-jupiter-engine (Version: 5.7.0)
* org.jacoco.org.jacoco.report (Version: 0.8.7)

#### Programming Techniques Used:
We used the following techniques while developing our project:
* __Interface__ was used to allow for uniform implementation of all Chess piece Objects.
* __Generic Methods__ were used to allow for different chess pieces to be used.
* __Collections__ were used as they allowed store and manage objects such as chess board squares.
* __Try-catch blocks__ were an integral part of the code as they allowed for smooth execution when an error was to be expected.
* __Method Overriding__ was used as it allowed for a uniform implementaion of all Chess piece classes while implementing an interface
* __Lambda Expressions__ were used for easier operations on EventListeners.
* __File I/O__ was used to load chess piece texture files.
* __Component Design Pattern__ was used as it allowed for a heirarchical design, increased scalability and uniformity.
* __Graphical User Interface__ was the core part of the project as it allowed players to interact with the game.
* __Casting__ was often used to convert data types with allowed for a vast range of operations on certain variables.

### Experience
#### Organization
The project was loosely organized, allowing for each memberr to choose what they want to work on.
* __Sharjeel Zahid Mahmood:__ My responsibilities encompassed the design of the structure and implementation of the Java classes that created chess objects, with a particular focus on the backend to ensure seamless execution of the chess game logic. I worked with the team on desingn efforts and integrated the backend with the frontend for a visually appealing chess game and a smooth user interaction. Additionally, I collaborated with team members to integrate and optimize the project using Maven, ensuring a streamlined and efficient development process.
* __Ridvan Plluzhina:__ Creating a chess game in Java was a great learning experience for me. I got to dive deeper into OOP programming. I also explored JavaFX, a tool for building the game's interface, which is what players see and interact with. It was exciting to see how code turns into a visual game that people can play.
* __Fabio Schick:__

#### Git 
Git was mostly used for version control, creating branches for working on each scope of the project. Pull requests were used to review and merge the specific braches into master.

#### Challanges
* __Sharjeel Zahid Mahmood:__ I found it difficult to come up with the logic for the implementation of chess rules. But, overtime it became easier to come up with these solutions.
* __Ridvan Plluzhina:__ The project had its tough moments, especially when dealing with the complex rules of chess.
* Each piece moves differently, and I had to ensure the game followed these rules correctly. Another challenge was designing the game so that it looked good and was easy for players to use. Despite these challenges, overcoming them was incredibly satisfying. Through this project, I learned not just about programming, but also about problem-solving and persistence.
* __Fabio Schick:__ 
