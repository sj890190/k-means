/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package initCluster;

import java.util.ArrayList;
import java.util.Arrays;
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
        
        getDistance(List.of(1.0, 502.0, 503.0), list);
        
        
        
        
        //List<List<Double>> res = algorithm(list, num_of_clusters);
        //res.stream().forEach(doubleList -> System.out.println(doubleList.toString()));
    }    
    
    public static List<List<Double>> algorithm(double[] list, int k){
        List<List<Double>> res = new ArrayList<>(); // 存放結果
        List<Double> center = getCenter(list, k); // 存放中心點
        
        return res;
    }
    
    //已經驗證過是預期的結果
    public static List<List<Double>> getDistance(List<Double> center, double[] data){
        List<List<Double>> res = new ArrayList<>(); // 存放分組結果        
        for(int i=0; i<data.length; i++){//以資料跟中心點做比對
            List<Double> distance = new ArrayList<>(); // 存放與中心點的距離
            for(int j=0; j<center.size(); j++){
                //取得資料與給個中心點的距離
                distance.add(Math.abs(data[i] - center.get(j)));
            }
            res.add(distance);
        }
        res.stream().map(list -> list.toString()).forEach(s -> System.err.println(s));
        return res;     
        
//        for(int i=0; i<center.size(); i++){//以中心點跟資料做比對
//            List<Double> distance = new ArrayList<>(); // 存放與中心點的距離
//            for(int j=0; j<data.length; j++){
//                //取得資料與給個中心點的距離
//                distance.add(Math.abs(center.get(i) - data[j]));
//            }
//            res.add(distance);
//        }
//        res.stream().map(list -> list.toString()).forEach(s -> System.err.println(s));
//        return res;
    }
    
    //已經驗證過是預期的結果
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
        System.out.println(res.stream().sorted().collect(Collectors.toList()).toString());
        return res.stream().sorted().collect(Collectors.toList());
    }
    
}
