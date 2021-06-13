public class Uproszczenia {
    public static void main(String[] args){
        //check args length
        if(args.length != 1){
            klops();
        }
        try {
            int turns = Integer.parseInt(args[0].substring(0, args[0].length() - 1));

            char direction = args[0].charAt(args[0].length() - 1);
            //check direction and get board dimensions
            int x = 0, y = 0;
            switch(direction){
                case 'N': case 'S':
                    x = turns + 3;
                    y = turns + 2;
                    break;
                case 'E': case 'W':
                    x = turns + 2;
                    y = turns + 3;
                    break;
                default: klops();
            }
            //proceed to program
            makeSpiral(x, y, direction, turns);
        } catch (Exception e){
            klops();
        }
    }
    private static void makeSpiral(int x, int y, char direction, int turns){
        char[][] board = new char[y][x]; //painting board
        int spiralLength = 0;
        switch (direction){
            case 'N': spiralLength = makePathN(board); break;
            case 'E': spiralLength = makePathE(board); break;
            case 'S': spiralLength = makePathS(board); break;
            case 'W': spiralLength = makePathW(board); break;
        }
        draw(board);
        //calc path length
        System.out.println(spiralLength-(turns+2)*2);
        System.exit(0);
    }

    private static int makePathN(char[][] board){
        int x = board[0].length - 1, y = 0, direction = 0, length = 0;
        for(int i = 0; i < board.length; i++) {
            board[y][x] = '*';
            length++;
            y++;
        }
        y--;
        x--;
        for(int i = board.length; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                board[y][x] = '*';
                length++;
                switch (direction) {
                    case 0: //left
                        x--; break;
                    case 1: //up
                        y--; break;
                    case 2: //right
                        x++; break;
                    case 3: //down
                        y++; break;
                }
            }
            switch (direction){
                case 0: x++; y--; break;
                case 1: y++; x++; break;
                case 2: x--; y++; break;
                case 3: y--; x--; break;
            }
            direction++;
            direction%=4;
        }
        return length;
    }
    private static int makePathE(char[][] board){
        int x = board[0].length - 1, y = board.length-1, direction = 1, length = 0;
        for(int i = 0; i < board[0].length; i++) {
            board[y][x] = '*';
            length++;
            x--;
        }
        y--;
        x++;
        for(int i = board[0].length; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                board[y][x] = '*';
                length++;
                switch (direction) {
                    case 0: //left
                        x--; break;
                    case 1: //up
                        y--; break;
                    case 2: //right
                        x++; break;
                    case 3: //down
                        y++; break;
                }
            }
            switch (direction){
                case 0: x++; y--; break;
                case 1: y++; x++; break;
                case 2: x--; y++; break;
                case 3: y--; x--; break;
            }
            direction++;
            direction%=4;
        }
        return length;
    }
    private static int makePathS(char[][] board){
        int x = 0, y = board.length-1, direction = 2, length = 0;
        for(int i = 0; i < board.length; i++) {
            board[y][x] = '*';
            length++;
            y--;
        }
        y++;
        x++;
        for(int i = board.length; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                board[y][x] = '*';
                length++;
                switch (direction) {
                    case 0: //left
                        x--; break;
                    case 1: //up
                        y--; break;
                    case 2: //right
                        x++; break;
                    case 3: //down
                        y++; break;
                }
            }
            switch (direction){
                case 0: x++; y--; break;
                case 1: y++; x++; break;
                case 2: x--; y++; break;
                case 3: y--; x--; break;
            }
            direction++;
            direction%=4;
        }
        return length;
    }
    private static int makePathW(char[][] board){
        int x = 0, y = 0, direction = 3, length = 0;
        for(int i = 0; i < board[0].length; i++) {
            board[y][x] = '*';
            length++;
            x++;
        }
        y++;
        x--;
        for(int i = board[0].length; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                board[y][x] = '*';
                length++;
                switch (direction) {
                    case 0: //left
                        x--; break;
                    case 1: //up
                        y--; break;
                    case 2: //right
                        x++; break;
                    case 3: //down
                        y++; break;
                }
            }
            switch (direction){
                case 0: x++; y--; break;
                case 1: y++; x++; break;
                case 2: x--; y++; break;
                case 3: y--; x--; break;
            }
            direction++;
            direction%=4;
        }
        return length;
    }

    private static void draw(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    // exit method
    private static void klops(){
        System.out.println("klops");
        System.exit(0);
    }
}
