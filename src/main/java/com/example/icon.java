/*
 * NAME: Makayla Alston
 * COURSE: CRCP 3315 001-1237
 * INSTRUCTOR: Professor Courtney Brown
 * DATE: Nov 2023
 * Description: This is a class that creates icons, which represents a given MIDI note 
 * CLASS: icon.java 
 */

 package com.example;

import processing.core.PApplet;

public class icon 
{

    //radius of an icon
    int rad;
    // handle's an icon's x and y coordinates
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
    //value of a given MIDI note
    int note;
    PApplet mainClass;

    //icon's constructor
    icon(PApplet main)
    {
        mainClass =main;
        xpos = mainClass.random(0, 1000);
        ypos = mainClass.random(0,1000);
    }
    
    // sets each icon's assigned note
    void setNote( int generatedNote)

    {
        note = generatedNote;
    }


    //bounces the icons
    void move(icon example)
    {
        //increments velocity by the value of gravity
        example.velocity += example.gravity;
        //increments the icon's y position by the value of velocity
        example.ypos += example.velocity;
        //if the icon's y posistion exceeds the screen's height
        if (example.ypos > mainClass.height - example.rad / 2)
        {
            //chanes the velocity, multiplies the value by -0.9 so the icon reverses direction
            example.velocity *= -0.9;
            //updates icon's y position
            example.ypos = mainClass.height - example.rad / 2;
        }

    }
 
    //creates the icons
    void instances()
    {
        //mods the MIDI note by 12 to retrieve the nearest whole note
        int letter = note % 12;

       //resets the icon's x position if it exceeds the boundaries of the screen
        if (xpos > mainClass.width || xpos < 0)
        {
           xpos = 0;
        }
        //resets the icon's y position if it exceeds the boundaries of the screen
        if (ypos > mainClass.height || ypos < 0)
        {
            ypos = 0;
        }

        //if statements that draw and fill shapes (icons) depending on the nearesst whole note
        if (letter == 0 || letter == 1)
        {
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

        //calls the move() function
        move(this);

    }

 

    //calls the instances() function multiple times
    void draw()
    {
        instances();
    }

}
