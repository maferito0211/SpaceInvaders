/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinva;

/**
 *
 * @author Sebastian
 */
public class Space {
    private int drawLocationX;
    private int drawLocationY;
    
    public Space(int dLocationX, int dLocationY)
    {
        this.drawLocationX = dLocationX;
        this.drawLocationY = dLocationY;  
    }
    
    public void setDrawLocationX(int dLocationX)
    {
        this.drawLocationX = dLocationX;
    }
    
    public void setDrawLocationY(int dLocationY)
    {
        this.drawLocationY = dLocationY;
    }
    public int getDrawLocationX()
    {
        return drawLocationX;
    }
    public int getDrawLocationY()
    {
        return drawLocationY;
    }
}
