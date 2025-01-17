import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class Main extends PApplet {
        Bird b1;
        float x,y,xspeed,size;
        float yspeed;
        int upgravity;
        //
        Pipes p;
        int px;
        int birdColor;
        float score;
        int health;
        boolean start;
        ArrayList<Pipes> gamePipes;
        PImage bg,lbg,sbg,wbg;
        PImage[] birdimg;
        PImage bimg1,bimg2;
        // PImage activeBird;
//        PImage pup, pdown;


    public void settings(){

        size(600,600);

    }

    public void setup(){
        score = 0;
        textSize(25);
        health = 1;
        start = false;
        bg = loadImage("mybg.png");
        bg.resize(600,600);
        lbg = loadImage("losingbg.jpg");
        lbg.resize(600,600);
        sbg = loadImage("startimg.png");
        sbg.resize(600,600);
        wbg = loadImage("winningimg3.png");
        wbg.resize(600,600);


        bimg1 = loadImage("fbframe1.2.png");
        bimg2 = loadImage("fbframe3.2.png");
        bimg1.resize(75,75);
        bimg2.resize(75,75);

//        for(int i = 0; i < 20; i++) {
//            if (i < 10) {
//                birdimg.add(bimg1);
//            } else if (i <20) {
//                birdimg.add(bimg2);
//            }
//        }
        birdimg = new PImage[] {bimg1,bimg1,bimg1,bimg1,bimg1,bimg1,bimg1,bimg1,bimg1,bimg1,
                                bimg2,bimg2,bimg2,bimg2,bimg2,bimg2,bimg2,bimg2,bimg2,bimg2};


//        pup = loadImage("pipeup.png");
//        pdown = loadImage("pipedown.png");

        gamePipes = new ArrayList<>();
//        b1.x = 150;
//        b1.y = 300;
//        b1.xspeed = 0;
//        b1.yspeed = 2;
//        size = 50;
        background(180);
        upgravity = -7;

        b1 = new Bird(150, 200, 0,2, 50);
//
        px = 600;

        for (int i = 0; i < 25; i++) {
            px = px + 400;
            p = new Pipes(px , 0, 50, (int)(Math.random()*350 + 50), color(82, 138, 44));
            gamePipes.add(p);

            p = new Pipes(px, p.pipel + 175, 50, 600 - (p.pipel + 175), color(82, 138, 44));
            gamePipes.add(p);
        }
//
        birdColor = color(255,255,0);
    }

    public void draw() {
        if (start == false) {
            image(sbg,0,0);
//            background(180);
            fill(0);
            text("Press the space bar to move", 140, 250);
            text("Press space to start!", 187,350 );



        } else if (start == true) {
            if (health == 1) {

//              background(180);
                image(bg,0,0);

                fill(birdColor);
//                ellipse(b1.x, b1.y, b1.size, b1.size);
                image(birdimg[frameCount%20], b1.x - 75/2,b1.y - 75/2);
                b1.y += (b1.yspeed);
                if (b1.yspeed < 20) {
                    b1.yspeed += 0.4f;
                }
//
                for (int i = 0; i < gamePipes.size(); i++) {
                    Pipes pipe = gamePipes.get(i);
                    fill(pipe.pipec);
                    rect(pipe.pipex, pipe.pipey, pipe.pipew, pipe.pipel);
//                    if(i%2 == 1) {
//                        pdown.resize((pipe.pipew * 5) - 4 * pipe.pipew, pipe.pipel);
//                        image(pdown,pipe.pipex, pipe.pipey);
//                    } else if(i%2 == 0) {
//                        pup.resize((pipe.pipew * 5) - 4 * pipe.pipew, pipe.pipel);
//                        image(pup, pipe.pipex, pipe.pipey);
//                    }
                    pipe.pipeMove();
//

                    if (b1.y - (b1.size / 2) <= (pipe.pipey + pipe.pipel) && b1.y + (b1.size / 2) >= pipe.pipey) {
                        if (b1.x + (b1.size / 2) >= pipe.pipex && b1.x - (b1.size / 2) <= (pipe.pipex + pipe.pipew)) {
                            birdColor = color(255, 0, 0);
                            health = 0;
//                   System.out.println("ball " + b1.x + ", " + b1.y + " with a radius of " + b1.size/2);
//                   System.out.print("pipex " + pipe.pipex + " to " +(pipe.pipex+pipe.pipew));
//                   System.out.println("   pipey " +pipe.pipey + " to " +(pipe.pipey+pipe.pipel));
                            // System.out.println("hit");
                        }
                    }
                    if (b1.y - b1.size <= 0 || b1.y + b1.size >= height) {
                        birdColor = color(0, 0, 255);
                        health = 0;
                    }
                    if (b1.x - (b1.size / 2) == (pipe.pipex + pipe.pipew)) {
                        score = (float) (score + 0.5);

                    }
                }
                fill(0);
                text("Score: " + (int) (score), 20, 50);
            } else if (health == 0) {
                image(lbg,0,0);
//                background(180);
                fill(0);
                text("You lose :(", 300, 250);
                textAlign(CENTER);
                text("Your score was: " + (int) (score), 300, 350);
                textAlign(CENTER);
            } else if (health == 19) {
                image(wbg, 0,0);
//                background(180);
                fill(0);
                text("You win :D", 300, 300);
                textAlign(CENTER);

            }


            if ((b1.x + b1.size) >= p.pipex + 150) {
                health = 19;
            }
//        }
//
//        }

//        if(b1.y - b1.size<= 0 || b1.y + b1.size >= height) {
//            birdColor = color(0,0,255);
//        }
        }
    }
    public void keyReleased() {
        if (key == ' ') {
            b1.yspeed = upgravity;
            start = true;
        }
//        if (key == 'a') {
//            start = true;
//        }
        //

    }
//    public void distance(int cx, int cy, int csize, int rx, int ry, int rw, int rl) {
//
//    }


    public static void main(String[] args) {

        PApplet.main("Main");

    }

}