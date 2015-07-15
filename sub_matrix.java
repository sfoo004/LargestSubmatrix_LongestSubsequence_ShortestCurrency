
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stevefoo
 */


public class sub_matrix {
    public int highest = 0;
    public sub_matrix(){
        
    }
    
    public void matrix(String f) throws FileNotFoundException{
        Scanner in = new Scanner(new FileReader(f));
        if(in.hasNextLine()){//checks to make sure there is at least a first line
        String [] prev = in.nextLine().trim().split("");
        int [] prev_row = convert(prev);
        String []  curr = new String [prev_row.length];
        int row_counter = 0;
        int [] coordinates = new int [2];
        while(in.hasNextLine()){// only really need to keep track of 2 rows to determine if there is subsquare
            curr = in.nextLine().trim().split("");
            int [] curr_row = convert(curr, prev_row, ++row_counter, coordinates);
            prev_row=Arrays.copyOf(curr_row, curr_row.length);           
        }
        
        System.out.println("The largest subsquare is: "+highest+" at coordinates (" + coordinates[0]+", "+coordinates[1]+")\n");
        }
        else
            System.out.println(" There are no elements in this file ");
    }   
    public int [] convert(String [] arr){
        int [] transfer = new int[arr.length];
        for(int i = 0 ; i< arr.length;i++){
            if(arr[i].equals("")){
                transfer[i]=0;
            }else{
                transfer[i] = Integer.parseInt(arr[i]);
                if(transfer[i]==1){
                    highest = 1;
                }
            }
        }
        return transfer;
    }
    public int[] convert(String[] curr, int[] prev, int row_counter, int [] coordinates) {
        int[] transfer = new int[curr.length];
        for (int i = 0; i < curr.length; i++) {
            if (curr[i].equals("")) {
                transfer[i] = 0;
            } else {
                int temp = Integer.parseInt(curr[i]);
                if (temp == 1 && i != 0) {
                    temp = Math.min(transfer[i - 1], Math.min(prev[i - 1], prev[i])) + 1;//finds min of three adjacent sqaures
                    transfer[i] = temp;
                    if (temp > highest) {
                        highest = temp;
                        coordinates[0] = row_counter;
                        coordinates[1] = i;
                    }
                } else {
                    transfer[i] = temp;
                }
            }
        }
        return transfer;
    }
}
