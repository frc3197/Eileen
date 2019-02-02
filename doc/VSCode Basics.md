# Keyboard Shortcuts and Command Line/Palette Commands
## Keyboard Shortcuts in VSCode
`Ctrl+Shift+P` opens up the command palette.

`Alt+Click` allows you to place multiple cursors. This way, you can edit multiple similar lines of code at the same time.

## Gradlew Commands
Gradlew is the system that we use for building and deploying our robot code. It's contained as a file, and the command `./gradlew` is required at the beginning of each of these commands. The `.` just signifies the current directory and `/gradlew` directs the computer to this folder where it can run the command. 

`./gradlew downloadAll` downloads all dependencies. This doesn't work on school WiFi as many of the dependencies are blocked. Download these on a hotspot.

`./gradlew build` builds all dependencies. This requires a downloadAll.

## Command Palette
The command palette is a VSCode function that can be accessed by using `Ctrl+Shift+P`. It contains many functions that we will be using.

`WPILib: Build Robot Code` builds the robot code. This is required in order to deploy the code onto the robot. By opening the command palette and typing in "build" this command will usually come up first.

`WPILib: Deploy Robot Code` deploys the robot code. You must be connected to the RoboRIO first for this to work. By opening the command palette and typing in "deploy" this command will usually come up first.

`Java: Clean the Java language server workspace` cleans the Java workspace. Primarily used when VSCode starts to not recognize many of the imports. Primarily used in "VSCode does not recognize WPILib imports and functions" under "Known Problems and Fixes." 
# Known Problems and Fixes
## VSCode does not recognize WPILib imports and functions.
VSCode will occasionally throw errors such as `[Java] The import edu.wpi.first.wpilibj.import.example cannot be resolved [268435846]`. This is because VSCode cannot find the dependencies, and as such doesn't know what you're trying to import. This will most likely result in all WPILib methods/objects throwing errors such as `[Java] ObjectName cannot be resolved to a type [16777218]` or `[Java] The method exampleMethod(input) is undefined for the type class.ObjectName [67108964]`.

To solve this, you need to re-download all dependencies.

1. Go to the terminal and run `./gradlew downloadAll`. This step does not work on school WiFi and requires a hotspot.
2. Once it's done downloading all, open up the command palette with `Ctrl+Shift+P` and type in `clean` to access the command `Java: Clean the Java language server workspace`. Once VSCode has restarted, all related errors should be resolved.

# Required Extensions
WPILib

VS Live Share Extension Pack (VS Live Share Audio not necessary)

Java Extension Pack