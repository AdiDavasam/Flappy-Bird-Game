public class Bird {
    float x,y,xspeed,yspeed,size;
    public Bird (float x, float y, float xs, float ys, float s) {
        this.x = x;
        this.y = y;
        xspeed = xs;
        yspeed = ys;
        size = s;
    }
    public void move() {
        y += yspeed;
    }
    public void jump() {
        yspeed = -15;

    }
}
