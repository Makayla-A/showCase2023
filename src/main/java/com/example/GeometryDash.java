package com.example;

import processing.core.PApplet;

public class GeometryDash 
{
    PApplet myMain;

    int gameScreen = 0;


    GeometryDash(PApplet mainProgram)
    {
        myMain = mainProgram;
    }


    void Screens()
    {
        if (gameScreen == 0)
        {
            StartScreen();
        }
        else if (gameScreen == 1)
        {
            startGame();
        }
        else if (gameScreen == 2)
        {
            GameOverScreen();
        }
        else if (gameScreen == 3)
        {
            GameWonScreen();
        }
    }

    void mousePressed()
    {
        if (gameScreen == 0)
        {
            startGame();
        }
    }

    void draw()
    {
        myMain.rect(0,500,40,40);
        myMain.fill(0, 128, 128);
    }
    
    //start screen
    void StartScreen()
    {
        //myMain.rect()
        myMain.push();
        myMain.background(0);
        if (myMain.frameCount % 30 < 15)
        {
            myMain.fill(0);
        }
        else
        {
            myMain.fill(0,255,0);
        }
       // myMain.textAlign(CENTER);
        myMain.textSize(32);
        myMain.text("Click to start!", 450,150);
        myMain.pop();
    }

    //game over screen

    void GameOverScreen()
    {

    }

    //game won screen
    void GameWonScreen()
    {

    }

    void startGame()
    {

    }
    //make triangle

    //make floating block?
}
