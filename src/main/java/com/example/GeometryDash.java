package com.example;

import processing.core.PApplet;

public class GeometryDash 
{
    PApplet myMain;


    GeometryDash(PApplet mainProgram)
    {
        myMain = mainProgram;
    }

    void draw()
    {
        myMain.rect(40,40,40,40);
        myMain.fill(0, 128, 128);
    }
    
}
