package com.example;

import processing.core.PApplet;

public class icon 
{
    float x = 0;
    float y = 100;
    float speed = 5;
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
        this.x = this.x + speed;
      //  x = x + speed;
    }

    void draw()
    {
        int letter = note % 12;
        if (letter == 0 || letter == 1)
        {
            mainClass.rect(x, y, 50, 50);
            mainClass.fill(0, 128, 128);
        }
       // mainClass.rect(x, y, 50, 50);

    }


    //i'm going to make the triangles bounce across the screen. they will be filled with different colors based on the note. run for 30 secs
    //adapt constructor to accept an integer parameter. write a function to set it. fill based on the parameter
}
