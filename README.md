# Live Odds Service

## Overview

This project is a simple Java library for managing a live odds service for football world cup score board. It supports operations to start a new match, update the score of an ongoing match, finish a match, and get a summary of matches in progress. The library uses an in-memory store to manage the matches and is implemented with a focus on quality, using Test-Driven Development (TDD), Object-Oriented (OO) design, Clean Code, and adherence to SOLID principles.

## Features

- **Start a new match**: Adds a new match to the scoreboard with an initial score of 0-0.
- **Update score**: Updates the score of an ongoing match.
- **Finish match**: Removes a match from the scoreboard.
- **Get summary**: Provides a summary of matches in progress, ordered by their total score. Matches with the same total score are ordered by the most recently started match.

## Assumptions

1. **One team per match**: A team can only participate in one match at a time.
2. **Non-negative scores**: Goals scored in a match cannot be negative.
3. **Case sensitivity**: Team names are case-sensitive and should be treated as unique based on their exact spelling.
4. **Unique match identifier**: Each match is identified by the home team name.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Usage of the library

#### Step-by-Step Guide

**Step 1:** Open your terminal and run the following git command to clone the repository:
```bash
git clone https://github.com/khairmuhammad/live-odds-service.git
cd live-odds-service
```

**Step 2:** Build the project using Maven:
```bash
mvn clean install
```

This command will compile the code, run the tests, and install the library to your local Maven repository.

**Step 3:** Add the following dependency to your project's pom.xml file:
```xml
<dependency>
    <groupId>com.football</groupId>
    <artifactId>live-odds-service</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Step 4:** Create a new Java class in your project and use the library's ScoreBoard class. Here is an example:
```java
import com.football.ScoreBoard;

public class Main {
    public static void main(String[] args) {
        // Create a new scoreboard instance
        ScoreBoard scoreboard = new ScoreBoard();

        // Start a new match
        scoreboard.startMatch("Mexico", "Canada");

        // Update the score
        scoreboard.updateScore("Mexico", 0, "Canada", 5);

        // Get the summary of ongoing matches
        System.out.println("Summary of ongoing matches:");
        scoreboard.getSummary().forEach(System.out::println);

        // Finish the match
        scoreboard.finishMatch("Mexico");

        // Verify the match has been removed
        System.out.println("Summary after finishing the match:");
        scoreboard.getSummary().forEach(System.out::println);
    }
}
```

### Example Output
When you run the above Main class, the output should be:
```text
Summary of ongoing matches:
Mexico 0 - Canada 5
```

### Running Test Cases

To run the tests, use the following command:

```bash
mvn test
```