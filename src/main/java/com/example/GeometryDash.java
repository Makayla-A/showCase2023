package com.example;

import processing.core.PApplet;

public class GeometryDash
{
   // MarkovChainGenerator<E> mChain = new MarkovChainGenerator<>();

    PApplet myMain;

   // int gameScreen = 0;
   float x = 0;
   float y = 100;
   float speed = 5;

//    public class icon()
//     {
//           float x = 0;
//           float y = 0;
//          float speed = 5;
//     }


    GeometryDash(PApplet mainProgram)
    {
        myMain = mainProgram;
    }


    // void Screens()
    // {
    //     if (gameScreen == 0)
    //     {
    //         StartScreen();
    //     }
    //     else if (gameScreen == 1)
    //     {
    //         startGame();
    //     }
    //     else if (gameScreen == 2)
    //     {
    //         GameOverScreen();
    //     }
    //     else if (gameScreen == 3)
    //     {
    //         GameWonScreen();
    //     }
    // }

    // void mousePressed()
    // {
    //     if (gameScreen == 0)
    //     {
    //         startGame();
    //     }
    // }

    // void obstacles(E note)
    // {

    // }

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
       // mousePressed();
      // startGame();
    }

    //game over screen

    void GameOverScreen()
    {

    }

    //game won screen
    void GameWonScreen()
    {

    }

    void move()
    {
        x = x + speed;
    }

    void startGame()
    {
        myMain.rect(0,500,40,40);
        myMain.fill(0, 128, 128);
        myMain.translate(300, 0);
       // myMain.rect(0,500,40,40);
        //myMain.fill(0, 128, 128);

        //i want the square to go to the right at a constant speed
        //i want the squre to jump when i press space
        //i want obstacles to generate based on the note produced in markov
        //this function is gonna be the bulk of the program

    }
    //make triangle
    void triangle()
    {
        //myMain.triangle(120, 300, 232, 80, 344, 300);
        myMain.triangle(60,150,116,40,172,150);
    }

    //make floating block?
}
