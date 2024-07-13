This project is an enhanced version of a biker game with new features including additional lanes,
obstacles, prizes, crash sounds, and different game modes (sensors/buttons). Additionally, the game
now has a top 10 records table and a map to show where the game was played for each record, all
implemented using two fragments.

- Features
    **Five Lanes:** The game now features five lanes for more dynamic gameplay.
    **Obstacles and Prizes:** New obstacles (poos) and prizes (hamburgers) are added to increase the
    challenge.
    **Crash Sound:** A sound is played whenever the player crashes into an obstacle.
    **Game Modes:** Players can choose between two control modes - button-based or sensor-based.
    **Top 10 Records:** A leaderboard to track the top 10 scores.
    **Google Maps Integration:** Shows the location where each game was played.
- Libraries Used
    **Glide:** For image loading and caching.
    **Gson:** For converting Java objects to JSON and vice versa.
    **Google Maps:** To display and interact with maps.
-Implementation Details
 Game Flow:
    a. Menu Activity: The user selects the game mode (buttons or sensors) and speed (slow or fast).
    b. Main Activity:
     1. Controls:
     Button Mode: Uses left and right buttons to control the biker.
     Sensor Mode: Uses device sensors to detect movements.
     2. Obstacles and Prizes: Randomly generated on the lanes. The player must avoid obstacles and can
     collect prizes.
     3. Scoring: The score increases as the player avoids obstacles and collects prizes.
     4. Crash Detection: If the player crashes into an obstacle three times, the game ends.
    c. Lose Activity: Displays the final score. The player can optionally enter their name.
    d. Records Fragment: Displays the top 10 scores.
    e.Map Fragment: Displays the locations where games were played.
 Key Challenges and Solutions
    a. Data Transfer: Passing the selected game mode and speed mode from the menu activity to the main-
    activity was challenging. This was solved using a material switch button.
    b. Google Maps Integration: Implementing Google Maps and adding functionality to zoom into the map-
    when a record is selected required extensive research and online resources.
    c. Crash Detection Logic: Implemented using a countdown timer that checks if the player crashed into-
    one of the last row obstacles on every tick.
    d. Leaderboard Management: Ensuring the player's score is placed in the correct spot and the lowest-
    score is removed if it is within the top 10.
 Permissions
    The game requires location permissions to access the device's last known location to display on-
    the map. The permission is requested at runtime.

- Future Improvements
    Fix the issue with one by one obstacles.
    Enhance the visual and sound effects.