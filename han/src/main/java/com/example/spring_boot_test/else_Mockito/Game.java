package com.example.spring_boot_test.else_Mockito;

public class Game {
  private GameNumGen gameNumGen;

  public Game(GameNumGen gameNumGen) {
    this.gameNumGen = gameNumGen;
  }

  void init(GameLevel level) {
    this.gameNumGen.generate(level);
  }
  ;
}
