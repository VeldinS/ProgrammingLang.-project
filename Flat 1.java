package FinalExam;

import java.util.ArrayList;

public class Flat {
    private int[][] areaMatrix;

    private int numberOfRooms;
    private ArrayList<String> listOfRooms;

    public Flat(int a, int b) {
        this.areaMatrix = new int[a][b];
        for (int i=0;i<a;i++){
            for (int j=0;j<b;j++){
                areaMatrix[i][j] = 0;
            }
        }

        this.listOfRooms = new ArrayList<>();

        //Add main walls around flat
        addWall(b,0,0,"horizontal");
        addWall(a,0,0,"vertical");
        addWall(b,0,a-1,"horizontal");
        addWall(a,b-1,0,"vertical");

    }


    public void addWall (int length, int x, int y, String direction){
        //method assumes path has been cleared
        if (direction.equalsIgnoreCase("horizontal")){
            for (int i = x; i < x+length; i++) {
                this.areaMatrix[y][i] = 1;
            }
        }
        else if(direction.equalsIgnoreCase("vertical")){
            for (int i = y; i < y+length ; i++) {
                this.areaMatrix[i][x] = 1;
            }
        }
        else {
            System.out.println("Invalid parameters");
        }
    }

    public void addFurniture(int []xCoords,int []yCoords){
        if(xCoords.length != yCoords.length){
            System.out.println("Invalid Coordinates");
        }
        else {
            //First check if space is available
            for (int i = 0; i < xCoords.length; i++) {
                if (areaMatrix[yCoords[i]][xCoords[i]] !=0){
                    System.out.println("One of the coordinates is occupied: "+yCoords[i]+", " + xCoords[i]);
                    return;
                }
            }
            for (int i = 0; i < xCoords.length; i++) {
                this.areaMatrix[yCoords[i]][xCoords[i]] = 2;
            }
        }
    }

    public void removeFurniture(int []xCoords,int []yCoords){
        if(xCoords.length != yCoords.length){
            System.out.println("Invalid Coordinates");
        }
        else {
            //First check if space is available
            for (int i = 0; i < xCoords.length; i++) {
                if (areaMatrix[yCoords[i]][xCoords[i]] !=2){
                    System.out.println("One of the coordinates does not belong to furniture: "+yCoords[i]+", " + xCoords[i]);
                    return;
                }
            }
            for (int i = 0; i < xCoords.length; i++) {
                this.areaMatrix[yCoords[i]][xCoords[i]] = 0;
            }
        }
    }


    public void destroyWall(int length, int y, int x, String direction){
        //method assumes path has been cleared
        if (direction.equalsIgnoreCase("horizontal")){
            for (int i = x; i < x+length; i++) {
                this.areaMatrix[y][i] = 0;
            }
        }
        else if(direction.equalsIgnoreCase("vertical")){
            for (int i = y; i < y+length ; i++) {
                this.areaMatrix[i][x] = 0;
            }
        }
        else {
            System.out.println("Invalid parameters");
        }
    }

    public void addDoor(int y, int x){
        if(areaMatrix[y][x]!=1){
            System.out.println("You can't add a door beyond a wall");
        }
        else areaMatrix[y][x] = 3;
    }


    //TEST

    public void addRoom(int y1, int x1, int y2, int x2, String type){

        this.addWall(x2-x1+1, x1, y1, "horizontal");
        this.addWall(x2-x1+1,x1,y2,"horizontal");
        this.addWall(y2-y1+1,x1,y1,"vertical");
        this.addWall(y2-y1+1,x2,y1,"vertical");

        numberOfRooms++;
        listOfRooms.add(type);
    }


    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public ArrayList<String> getListOfRooms() {
        return listOfRooms;
    }

    public static void main(String[] args) {
        Flat flat = new Flat(20,20);

        flat.addRoom(2,2,7,6,"bedroom");

        flat.addDoor(3,6);

        for (int i = 0; i < flat.areaMatrix.length ; i++) {
            for (int j = 0; j < flat.areaMatrix[0].length; j++) {
                System.out.print(flat.areaMatrix[i][j]);
            }
            System.out.println();
        }
    }

}
