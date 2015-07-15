
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class Main {
    
    public static void main(String [] args) throws FileNotFoundException {
        try{
        if (args.length != 2) {
                System.out.println("ERROR invalid inputs");
        } else {
            double start = System.nanoTime();
            sub_matrix test = new sub_matrix();
            test.matrix(args[1]);
            Scanner in = new Scanner(new FileReader(args[0]));
            sub_seq_matching test2 = new sub_seq_matching();
            test2.matching(in.nextLine().toLowerCase(), in.nextLine().toLowerCase());//incase of capitol letters
            coin_change test3 = new coin_change();
            test3.make_change();
            double stop = System.nanoTime();
            double math = (stop - start) / 1000000000;
            System.out.printf("TIME: %.4f seconds\n", math);
        }
    }catch (IOException e) {
                System.err.println("Error processing");
            }catch (NumberFormatException n) {//checks for wrong input being passed
                System.err.println("Error processing");
            }
}
}
