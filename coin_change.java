
import java.util.HashMap;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stevefoo
 */
public class coin_change {
    //1, 5, 10, 18, and 25 cent piece
    int [] coins = {1 ,5 ,10 ,18 ,25 };
    HashMap<Integer, LinkedList<Integer>> change = new HashMap<>();
    
    public void make_change(){
        LinkedList<LinkedList<Integer>> least_change = new LinkedList<>();
        for(int i = 1; i <101; i++){
            change.put(i, new LinkedList<Integer>());
            if(i==1||i==5||i==10||i==18||i==25){
                change.get(i).add(i);
            } else {
                for(int x : coins){
                    if(i-x>=0){
                        least_change.add(change.get(i - x));                      
                    }
                }
                if (!least_change.isEmpty()) {
                    int lowest = change.size();//change hashmap will always be greater than or equal to least amount of coins needed
                    int sum = 0;
                    LinkedList<Integer> low_list = new LinkedList<>();
                    for (LinkedList<Integer> list : least_change) {
                        if (lowest > list.size()) {
                            lowest = list.size();
                            low_list = list;
                        }
                    }
                    for (int low : low_list) {
                        sum += low;
                    }
                    for (int coin : change.get(sum)) {
                        change.get(i).add(coin);
                    }
                    change.get(i).add(i - sum);
                }
                least_change.clear();

            }
        }
        for(int i = 1; i<=change.size();i++){
            System.out.println("For "+ i + " cents the optimal # of coins is " + change.get(i).size()+ ":\n" +change.get(i).toString());
        }
    }
    
}
