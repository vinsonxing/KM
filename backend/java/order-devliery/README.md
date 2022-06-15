# How to run the program

## Run from fat jar
1. Go to the root folder, and run "./gradlew shadowJar" (linux like OS) or "gradlew.bat shadowJar" (windows) to build a runnable fat jar
2. java -jar lib/build/libs/lib-all.jar
3. Check the log at the end when the program running is done.
> 2022-06-15 10:12:38 [pool-5-thread-1] INFO  SystemRunner:78 - The average food waiting time is: 5199 ms
2022-06-15 10:12:38 [pool-5-thread-1] INFO  SystemRunner:85 - The average courier waiting time is: 5688 ms
## Run from IDE
The main class is com.css.interview.running.SystemRunner
Run it as an java application in IDE

# How to run UTs
Go to the root folder, and run "./gradlew test"

# Hints
The order type (specifed/arbitrary) is generated randomly, the general rate is 3:2, so the final statistics result should be diff.

# Env reqiurement
Java 11
Gradle 6.8