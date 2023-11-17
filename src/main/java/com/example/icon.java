package com.example;

import processing.core.PApplet;

public class icon 
{
   // float x = 0;
    //float y = 100;
   // float speed = 5;

    int rad;
    float xpos, ypos;
    float xSpeed = 1;
    float ySpeed = 1;
    int xDirection = 1;
    int YDirection = 1;

    int note;
    PApplet mainClass;

    icon(PApplet main)
    {
        mainClass =main;
    }
    
    void setNote( int generatedNote)

    {
        note = generatedNote;
    }

    void move()
    {
       // this.x = this.x + speed;
      //  x = x + speed;
    }

    void instances()
    {
        int letter = note % 12;

        xpos = xpos + (xSpeed * xDirection);
        ypos = ypos + (ySpeed * YDirection);

        if (xpos > mainClass.width)
        {
            xDirection *= -1;
        }
        if (ypos > mainClass.height)
        {
            YDirection *= -1;
        }


        if (letter == 0 || letter == 1)
        {
           // mainClass.rect(x, y, 50, 50);
            mainClass.rect(xpos, ypos, 50, 50);
            mainClass.fill(0, 128, 128);
        }
        if (letter == 2 || letter == 3)
        {
            mainClass.ellipse(xpos + 20, ypos + 20, 50, 50);
            mainClass.fill(242, 212, 215);
        }
        if (letter == 4 || letter == 5)
        {
            mainClass.rect(xpos + 30, ypos + 30, 50, 50);
            mainClass.fill(159, 226, 191);
        }
        if (letter == 6 || letter == 7)
        {
            mainClass.ellipse(xpos + 40, ypos + 40, 50, 50);
            mainClass.fill(230, 230, 250);
        }
        if (letter == 8 || letter == 9)
        {
            mainClass.rect(xpos + 50, ypos + 50, 50, 50);
            mainClass.fill(253, 94, 83);
        }
        if (letter == 10 || letter == 11)
        {
            mainClass.ellipse(xpos + 60, ypos + 60, 50, 50);
            mainClass.fill(227, 194, 163);
        }


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
