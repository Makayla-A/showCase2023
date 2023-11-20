package com.example;

import processing.core.PApplet;

public class icon 
{
   // float x = 0;
    //float y = 100;
   // float speed = 5;

    int rad;
    //coordinates
    float xpos, ypos;
    //spped along x axis
    float xSpeed = 1;
    //speed along y axis
    float ySpeed = 1;
    //falling in x direction
    int xDirection = 1;
    //falling in y direction
    int YDirection = 1;
    //gravity
    double gravity = 0.1;
    //velocity
    float velocity=0;

    int note;
    PApplet mainClass;

    icon(PApplet main)
    {
        mainClass =main;
        xpos = mainClass.random(0, 1000);
        ypos = mainClass.random(0,1000);
    }
    
    void setNote( int generatedNote)

    {
        note = generatedNote;
    }

    void move() //attempt 1
    {
      //should make it fall down
     // YDirection += gravity;
      //YDirection += ySpeed;
      //make it bounce
     // if (YDirection > (mainClass.height))
      //{
       // YDirection *= -1;
      //}
    }

    void move(icon example)
    {
        example.velocity += example.gravity;
        example.ypos += example.velocity;
        if (example.ypos > mainClass.height - example.rad / 2)
        {
            example.velocity *= -0.9;
            example.ypos = mainClass.height - example.rad / 2;
        }

    }

    
      

    

    void instances()
    {
        int letter = note % 12;

       // xpos = xpos + (xSpeed * xDirection);
       //xpos = mainClass.random(mainClass.width);
      // xpos = mainClass.random(0, 1000);
      // ypos = mainClass.random(mainClass.height);
      // ypos = mainClass.random(0, 1000);


       // ypos = ypos + (ySpeed * YDirection);

        if (xpos > mainClass.width || xpos < 0)
        {
           // xDirection *= -1;
           //xpos *= -1;
           xpos = 0;

        }
        if (ypos > mainClass.height || ypos < 0)
        {
            //YDirection *= -1;
            //ypos *= -1;
            ypos = 0;
        }


        if (letter == 0 || letter == 1)
        {
           // mainClass.rect(x, y, 50, 50);
            mainClass.rect(xpos, ypos, 50, 50);
           // mainClass.rotate(mainClass.PI);
            mainClass.fill(0, 128, 128);
           // move();
        }
        if (letter == 2 || letter == 3)
        {
            mainClass.ellipse(xpos + 20, ypos + 20, 50, 50);
            mainClass.fill(242, 212, 215);
           // move();
        }
        if (letter == 4 || letter == 5)
        {
            mainClass.rect(xpos + 30, ypos + 30, 50, 50);
            mainClass.fill(159, 226, 191);
          //  move();
        }
        if (letter == 6 || letter == 7)
        {
            mainClass.ellipse(xpos + 40, ypos + 40, 50, 50);
            mainClass.fill(230, 230, 250);
           // move();
        }
        if (letter == 8 || letter == 9)
        {
            mainClass.rect(xpos + 50, ypos + 50, 50, 50);
            mainClass.fill(253, 94, 83);
           // move();
        }
        if (letter == 10 || letter == 11)
        {
            mainClass.ellipse(xpos + 60, ypos + 60, 50, 50);
            mainClass.fill(227, 194, 163);
           // move();
        }

        move(this);

        //missing a note??


       // mainClass.rect(x, y, 50, 50);

    }

 

    void draw()
    {
        instances();
    }




    //i'm going to make the triangles bounce across the screen. they will be filled with different colors based on the note. run for 30 secs
    //adapt constructor to accept an integer parameter. write a function to set it. fill based on the parameter
}
