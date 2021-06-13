import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Drwal {
    private static final int MAX_SIZE = 5000;
    public static void main(String[] args){
        //check args length
        if(args.length != 5){
            klops();
        }
        try {
            //get arguments
            int xStart = Integer.parseInt(args[0]) - 1;
            int yStart = Integer.parseInt(args[1]) - 1;
            char color = args[2].charAt(0);
            int imageX = Integer.parseInt(args[3]);
            int imageY = Integer.parseInt(args[4]);

            //check arguments
            if(imageX < 0 || imageX > MAX_SIZE || imageY < 0 || imageY > MAX_SIZE) klops();
            if(xStart > imageX || xStart < 0 || yStart > imageY || yStart < 0) klops();

            //actual work
            char[][] picture = loadDraw(imageX, imageY);
            fill(picture, color, xStart, yStart, imageY, imageX);
            draw(picture);
            System.exit(0);
        } catch (Exception e){
            klops();
        }
    }

    private static char[][] loadDraw(int x, int y){
        char[][] picture = new char[x][y];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            for(int i = 0; i < x; i++){
                String line = br.readLine();
                if(line == null) break;

                char[] aLine = line.toCharArray();
                for(int j = 0; j < aLine.length; j++){
                    picture[i][j] = line.charAt(j);
                }
            }
        } catch (IOException e) {
            klops();
        }

        return picture;
    }

    private static void draw(char[][] picture){
        for(int i = 0; i < picture.length; i++){
            for(int j = 0; j < picture[0].length; j++){
                System.out.print(picture[i][j]);
            }
            System.out.println();
        }
    }

    private static void fill(char[][] picture, char color, int x, int y, int maxY, int maxX){
        if(picture[y][x] == ' ')
            picture[y][x] = color;
        //else return;

        //check up
        if(y-1 >= 0){
            if(picture[y-1][x] == ' '){
                picture[y-1][x] = color;
                fill(picture, color, x, y-1, maxY, maxX);
            }
        }
        //check right
        if(x+1 < maxX){
            if(picture[y][x+1] == ' '){
                picture[y][x+1] = color;
                fill(picture, color, x+1, y, maxY, maxX);
            }
        }
        //check left
        if(x-1 >= 0){
            if(picture[y][x-1] == ' '){
                picture[y][x-1] = color;
                fill(picture, color, x-1, y, maxY, maxX);
            }
        }
        //check down
        if(y+1 < maxY){
            if(picture[y+1][x] == ' '){
                picture[y+1][x] = color;
                fill(picture, color, x, y+1, maxY, maxX);
            }
        }
    }
    // exit method
    private static void klops(){
        System.out.println("klops");
        System.exit(0);
    }
}
