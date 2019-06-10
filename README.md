# MinesweeperChallange

Minesweeper challange library
this library will present you with a minesweeper board
try to code an ai to win the game

very basic example:
```java
MinesweeperBuilder builder = new MinesweeperBuilder();
builder.seed().any();
builder.width().setSpecific(10);
builder.height().setSpecific(10);
builder.bombs().percent().setSpecific(0.2);
Minesweeper minesweeper = builder.build();
minesweeper.setAutoprint(true);

for (int y = 0; y < minesweeper.getInformation().getHeight(); y++)
{
  for (int x = 0; x < minesweeper.getInformation().getWidth(); x++)
  {
    minesweeper.get(x, y).open();
  }
}
```

Console output:

![Console output](https://image.prntscr.com/image/7_KCqz_ORaubrUrvZOhrzA.png)
