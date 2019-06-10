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

Installation (Maven)

```maven
<repositories>
  
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>

</repositories>



<dependencies>

  <dependency>
    <groupId>com.github.EnderCrypt</groupId>
    <artifactId>MinesweeperChallange</artifactId>
    <version>6b02584e65ca0807deb3169d92e78bb36140dba5</version>
  </dependency>

</dependencies>
```
