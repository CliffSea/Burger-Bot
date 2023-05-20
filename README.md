# Burger Bot (WIP)

Burger Bot is a Discord bot written in Kotlin using the Kord library (https://github.com/kordlib/kord). It is currently a work in progress, aimed at providing a modular and extensible bot framework for Discord servers.

## Features

- **Modular Design:** Burger Bot is designed with modularity in mind. You can easily add new commands without the need to edit the main file. Each command has its own file, making it straightforward to add new functionality to the bot.

- **Embed Messages:** Burger Bot supports embedded messages for commands. This allows for more visually appealing and informative responses to user interactions.

## Build

To build the Bot, you need to have Java JDK 18 installed on your system. Once you have Java JDK 18 set up, follow these steps:

1. First of all you need a bot account already create on discord developer portal (https://discord.com/developers/applications)
2. Create a .env file on the project's root directory and add this

```env
TOKEN ="YOUR BOT TOKEN"
```

3. Open a terminal or command prompt.
4. Navigate to the project's root directory.
5. Run the following command:

```shell
gradlew build
```

This will compile the source code, run tests, and generate the output artifacts.


This will compile the source code, run tests, and generate the output artifacts.

## Future Updates

Burger Bot is an ongoing project, and updates will be added as time permits. Stay tuned for new features and improvements in the future.

Contributions are welcome, so feel free to contribute to the project by submitting pull requests or suggesting new features.

For more information, visit the project repository: https://github.com/<your-username>/<your-repo>

Enjoy using Burger Bot!