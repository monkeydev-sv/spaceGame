package com.christerholmcode.duckforthebullet;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingShipView extends View {
private final Bitmap[] ship = new Bitmap[2];
private final int shipX = 10;
private int shipY;
private int shipSpeed;
private int yellowX;
    private int yellowY;
    private final Paint yellowPaint = new Paint();
    private int greenX;
    private int greenY;
    private final Paint greenPaint = new Paint();

    private int redX;
    private int redY;
    private final Paint redPaint = new Paint();

    private int whiteX;
    private int whiteY;
    private final Paint whitePaint = new Paint();


    private int score , lifeCounterOfShip;
    private boolean touch = false;
private final Bitmap backgroundImage;
private final Paint scorePaint = new Paint();
private final Bitmap[] life = new Bitmap[2];
    public FlyingShipView(Context context) {
        super(context);
        ship[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ship1);
        ship[1] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2);

        backgroundImage= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(false);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart1);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart2);
        shipY = 550;
        score = 0;
        lifeCounterOfShip = 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        canvas.drawBitmap(backgroundImage,0,0,null);
      int minShipY = ship[0].getHeight();
      int maxShipY = canvasHeight - ship[0].getHeight() * 0;
      shipY = shipY + shipSpeed;
      if (shipY < minShipY)
      {
          shipY = minShipY;

      }
        if (shipY > maxShipY)
        {
            shipY = maxShipY;

        }
        shipSpeed = shipSpeed + 2;
if (touch){
canvas.drawBitmap(ship[1],shipX,shipY,null);
    touch = false;
}
else
{
    canvas.drawBitmap(ship[0],shipX,shipY,null);

}
//YELLOW
        int yellowSpeed = 14;
        yellowX = yellowX - yellowSpeed;
        if (hitballChecker(yellowX, yellowY))
        {
            score = score + 10;
            yellowX = -100;

        }
if(yellowX < 0){
    yellowX = canvasWidth + 21;
    yellowY = (int) Math.floor(Math.random()* (maxShipY-minShipY))+ minShipY;
}
canvas.drawCircle(yellowX,yellowY, 25,yellowPaint);
//GREEN
        int greenSpeed = 16;
        greenX = greenX - greenSpeed;
        if (hitballChecker(greenX, greenY))
        {
            score = score + 15;
            greenX = -100;

        }
        if(greenX < 0){
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random()* (maxShipY-minShipY))+ minShipY;
        }
        canvas.drawCircle(greenX,greenY, 25,greenPaint);

        //white
        int whiteSpeed = 25;
        whiteX = whiteX - whiteSpeed;
        if (hitballChecker(whiteX, whiteY))
        {
            score = score - 5;
            whiteX = -100;

        }
        if(whiteX < 0){
            whiteX = canvasWidth + 500;
            whiteY = (int) Math.floor(Math.random()* (maxShipY-minShipY))+ minShipY;
        }
        canvas.drawCircle(whiteX,whiteY, 15,whitePaint);

        //RED
        int redSpeed = 25;
        redX = redX - redSpeed;
        if (hitballChecker(redX, redY))
        {
            redX = -100;
            lifeCounterOfShip--;
            if(lifeCounterOfShip==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
getContext().startActivity(gameOverIntent);
            }

        }
        if(redX < 0){
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random()* (maxShipY-minShipY))+ minShipY;
        }
        canvas.drawCircle(redX,redY, 30,redPaint);

        canvas.drawText("Score:" + score, 20, 60, scorePaint);
        for (int i=0; i<5; i++)
        {
            int x =(int)(450 +life[0].getWidth()* 1.2* i);
            int y = 30;
            if (i < lifeCounterOfShip)
            {
                canvas.drawBitmap(life[0], x,y, null);
            } else {
                canvas.drawBitmap(life[1], x,y, null);
            }
        }






    }
    public boolean hitballChecker(int x , int y)
    {
        return shipX < x && x < (shipX + ship[0].getWidth()) && shipY < y && y < (shipY + ship[0].getHeight());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            shipSpeed = -22;
        }
        return true;
    }
}
