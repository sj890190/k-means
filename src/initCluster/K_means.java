/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class K_means {
    
    public static void main(String[] args) {
        // TODO code application logic here
        double[] list = {1,2,3,4,50,51,52,53,500,501,502,503};
        int num_of_clusters = 3;
        
        getCenter(list, num_of_clusters);
        
        
        //List<List<Double>> res = algorithm(list, num_of_clusters);
        //res.stream().forEach(doubleList -> System.out.println(doubleList.toString()));
    }    
    
    public static List<List<Double>> algorithm(double[] list, int k){
        List<List<Double>> res = new ArrayList<>(); // 存放結果
        List<Double> center; // 存放中心點
        
        return res;
    }
    
    public static List<Double> getCenter(double[] data, int k){
        List<Double> res = new ArrayList<>();
        //需要取到k個中心點
        while(res.size()<k){
            //取data長度的亂數為index, 再從data取出對應的數值
            double value = data[new Random().nextInt(data.length)];
            //避免中心點重複
            if(!res.contains(value)){
                res.add(value);
            }
        }
        //由小到大排序
        res = res.stream().sorted().collect(Collectors.toList());
        System.out.println(res.toString());
        return res;
    }
    
}
