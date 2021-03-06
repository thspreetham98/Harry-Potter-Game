/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harry_potter;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 *
 * @author ANURAG
 */
public class Test extends Application implements Runnable{
    ArrayList<String> kks_t = new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    KnockKnockServer k=new KnockKnockServer();
   // Test t=new Test();
   // Thread test = new Thread(t);
    //Training tr=new Training();
     public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        
        theStage.setTitle( "Harry Potter" );

        Group root = new Group();
        Scene theScene = new Scene( root,800, 600 );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1366, 768 );
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> in=new ArrayList<>();

        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                    
                    String code = e.getCode().toString();
                    
                    if ( !input.contains(code) )
                        input.add( code );
                    }
                
            });
        
        
                Thread th=new Thread(k);
                th.start();
                while(kks_t.size()==0){
                    kks_t = k.getstring();
                    System.out.println(kks_t);
                }
               // test.start();
                //while(kks_t.size()==0){
                //for(int i=0;i<2;i++){
                /*if(k.getstring().size()!=0){
                    
                    System.out.println(k.getstring());
                    kks_t = k.getstring();
                  // System.out.println(kks_t);
                }
                */
                
                
        

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                    
                    String code = e.getCode().toString();
                    input.remove( code );
                    
                }
            });
        

                        

               
        GraphicsContext gc = canvas.getGraphicsContext2D();

        
        Sprite harry_potter = new Sprite();
        
        harry_potter.setPosition(200, 0);
        harry_potter.setImage("/harry_potter/Movement/d1.png");
        
        ArrayList<Sprite> spellsList = new ArrayList<>();
        

        
        
        
        for (int i = 0; i < 20; i++)
        {
            Sprite spells = new Sprite();
            spells.setImage("/harry_potter/Movement/spell.png");
            double px = 1000 * Math.random() + 50;
            double py = 1000 * Math.random() + 50;  
            //if((px<=1366)&&(py<=768)){
            spells.setPosition(px,py);
            spellsList.add( spells );
            //}
        }
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);
        
        AnimatedImage right = new AnimatedImage();
        Image[] imageArrayr = new Image[2];
        for (int i = 0; i < 2; i++)
            imageArrayr[i] = new Image( "/harry_potter/Movement/r" + (i+1) + ".png" );
        right.frames = imageArrayr;
        right.duration = 0.100;
        
        AnimatedImage left = new AnimatedImage();
        Image[] imageArrayl = new Image[2];
        for (int i = 0; i < 2; i++)
            imageArrayl[i] = new Image( "/harry_potter/Movement/l" + (i+1) + ".png" );
        left.frames = imageArrayl;
        left.duration = 0.100;
        
        AnimatedImage down = new AnimatedImage();
        Image[] imageArrayd = new Image[2];
        for (int i = 0; i < 2; i++)
            imageArrayd[i] = new Image( "/harry_potter/Movement/d" + (i+1) + ".png" );
        down.frames = imageArrayd;
        down.duration = 0.100;
        
        AnimatedImage up = new AnimatedImage();
        Image[] imageArrayu = new Image[2];
        for (int i = 0; i < 2; i++)
            imageArrayu[i] = new Image( "/harry_potter/Movement/u" + (i+1) + ".png" );
        up.frames = imageArrayu;
        up.duration = 0.100;
        
         AnimatedImage spellcast = new AnimatedImage();
        Image[] imageArray = new Image[13];
        for (int i = 0; i < 13; i++)
            imageArray[i] = new Image( "/harry_potter/Spells/sp" + (i+1) + ".png" );
        spellcast.frames = imageArray;
        spellcast.duration = 0.100;
        
        final long startNanoTime = System.nanoTime();
        
        
        
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime) 
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                // game logic
                               
                harry_potter.setVelocity(0,0);

               
                 
                /*if(!(kks_t.contains(""))){
            
        }*/
                
                
                
                
                if(kks_t.contains("Wingardium Leviosa")|kks_t.contains("hello"))
                {                         
                    harry_potter.setImage("/harry_potter/Spells/Spell.gif");
                    kks_t.clear();
                }
                if (input.contains("LEFT")){
                    harry_potter.setImage(left.getFrame(t));
                    harry_potter.addVelocity(-150,0);
                }   
                if (input.contains("RIGHT")){
                    harry_potter.setImage(right.getFrame(t));
                    harry_potter.addVelocity(150,0);
                }
                if (input.contains("UP")){
                    harry_potter.setImage(up.getFrame(t));
                     harry_potter.addVelocity(0,-150);
                }
                if (input.contains("DOWN")){
                    harry_potter.setImage(down.getFrame(t));
                   harry_potter.addVelocity(0,150);
                }
                    
                harry_potter.update(elapsedTime);
                
                // collision detection
                
                Iterator<Sprite> spellsIter = spellsList.iterator();
                while ( spellsIter.hasNext() )
                {
                    Sprite spells = spellsIter.next();
                    if ( harry_potter.intersects(spells))
                    {
                        spellsIter.remove();
                        score.value++;
                    }
                }
                
                // render
                
                gc.clearRect(0, 0, 1366,768);
                harry_potter.render( gc );
                
                for (Sprite spells : spellsList )
                    spells.render( gc );
                
                String pointsText = "Score: " + (100 * score.value);
                gc.fillText( pointsText, 1300, 36 );
                gc.strokeText( pointsText, 1300, 36 );
            }
        }.start();
        
         
        
        

        theStage.show();
    } 


    @Override
    public void run() {
        while(kks_t.size()==0){
                    kks_t = k.getstring();
                   System.out.println(kks_t);
                }
    }
}