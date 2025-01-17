public class Pipes {
    int pipel, pipew, pipex, pipey, pipec;
    public Pipes (int pipex, int pipey, int pipew, int pipel, int pipec) {
        this.pipex = pipex;
        this.pipey = pipey;
        this.pipew = pipew;
        this.pipel = pipel;
        this.pipec = pipec;
    }
    public void pipeMove() {
        pipex -= 5;
    }
}
