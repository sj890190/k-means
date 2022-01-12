/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package initCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author CWC
 */
public class initCluster {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] list = {1,2,3,4,50,51,52,53,500,501,502,503};
        int num_of_clusters = 3;
        List<List<Double>> res = algorithm(list, num_of_clusters);
        res.stream().forEach(doubleList -> System.out.println(doubleList.toString()));
    }    
    
    private static List<List<Double>> algorithm(double[] data, int num) {                 
        List<Double> center = List.of(1D, 502D, 503D);
        //List<Double> center = List.of(27D, 501D, 503D);
        //List<Double> center = initCenter(data, num); // 隨機取得num個中心點
        List<List<Double>> res; // 存放分組結果
        
        while(true){
            res = initCluster(data, center);
            List<Double> new_center = calcCenter(res); // 存放新的中心點
            
            if(!center.equals(new_center)){
                center = new_center;
            }else{
                break;
            }
        }
        
        return res;
    }
    
    // 隨機取得num個中心點
    private static List<Double> initCenter(double[] data, int num){
        List<Double> temp = new ArrayList<>();
        while(temp.size()<num){
            double random = data[new Random().nextInt(data.length)];
            if(!temp.contains(random)){
                temp.add(random);
            }
        }
        return temp.stream().sorted().collect(Collectors.toList());
    }
    
    // 重新計算中心
    private static List<Double> calcCenter(List<List<Double>> res){
        List<Double> center = new ArrayList();      
        for(List<Double> temp: res){
            Double sum = 0.0;
            for(Double value: temp){
                sum = sum + value;
            }
            center.add(sum / temp.size());
        }
        return center.stream().sorted().collect(Collectors.toList());
    }
    
    // 計算距離並分組
    private static List<List<Double>> initCluster(double[] data, List<Double> center){    
        List<Integer> cluster = new ArrayList(); // 存放暫時的分組
        List<List<Double>> temp = new ArrayList(); // 存放分組結果
        
        // 對每個元素與中心點比較
        for(int i=0; i<data.length; i++){            
            List<Double> distance = new ArrayList(); // 存放與中心點的距離
            for(int j=0; j<center.size(); j++){
                // 取絕對值後, 紀錄該點與該中心的距離
                distance.add(Math.abs(data[i] - center.get(j)));
            }
            // 找出與中心點最近的
            cluster.add(findMinDistanceIndex(distance));
        }
        
        // 依照暫時分組的, 存放到分組結果中
        for(int i = 0; i < center.size(); i++){
            List<Double> group = new ArrayList<>();
            for(int j = 0; j < cluster.size(); j++){
                if(i == cluster.get(j)){
                    group.add(data[j]);
                }
            }
            temp.add(group);
        }    
        
        return temp;
    }
    
    // 找出與中心點中, 最近的
    private static int findMinDistanceIndex(List<Double> distance){
        int index = 0;
        double minNumber = distance.get(index);
        for(int i=1; i<distance.size(); i++){
            if(minNumber >= distance.get(i)){
                index = i;
                minNumber = distance.get(i);
            }
        }
        return index;
    }
    
}
