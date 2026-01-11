# BorderHoarder

Collect unique items to expand the border!
_This is a fork of the [original repo](https://github.com/SimonDMC/BorderHoarder), written to be easier to keep up to date with new Minecraft versions. See [this section](#for-developers) for more information._
As of writing, this plugin is verified to be up to date with Minecraft 1.21.11. The plugin is intended to be used with [PaperMC](papermc.io).

## Starting a Game

- Start a game by running `/startbordergame`
- To start a game with a set seed, use `/startbordergame <seed>`
- To join the game after it has started, use `/joinbordergame`

## How to Play

### The Basics

- The world starts with a 1x1 border, expand the border by
  obtaining new items
- The border will expand by 1 block in each direction for each
  unique item you obtain
- To view all the items you have collected, use
  `/viewcollecteditems`
- To view all the items you are yet to collect, use
  `/viewmissingitems`
- As the game goes on, figuring out whether you already have an
  item or not gets more difficult, so you can use
  `/iscollected <name>` to check if one or more items have been collected. All items matching the text you provide will be listed.

### Limitations

- The game (theoretically) ends when you collect every single
  obtainable items, however in practice that is impossible on
  most seeds, due to biome-specific items
- The nether works, and still has the border (which is synced
  with the overworld), but the end doesn't have a border, just
  so that obtaining an elytra and outer end island items is
  possible

### Competitive Mode

- The game is designed to be played in a cooperative manner,
  where players work together to get as many items as possible,
  however the game can also be played competitively.
- Pressing TAB will show the number of items collected by each
  player, so players can compete to see who can get the most
  items.

## Compatibility

- The plugin runs on PaperMC.
- The game is intended to be played on 1.21.11. It may not work on previous versions, though it is intended to keep working on future versions by using a blacklist system.
- The game can be played with any number of players, but it is
  recommended to play with 2-4 players. The more players there
  are, the easier the game will get.

## Feature Requests

If you have any feature requests, please open an issue on
GitHub.

## Download

The direct download link for the latest version is available
[here](https://github.com/SimonDMC/BorderHoarder/releases/latest/download/BorderHoarder.jar).

# For Developers
- This fork uses a blacklist system instead of the original whitelist. 