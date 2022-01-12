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
        int k = 3;   
        
        //System.out.println("待分群資料: [1,2,3,4,50,51,52,53,500,501,502,503]");  
        List<List<Double>> res = algorithm(list, k);
        res.stream().map(item -> item.toString()).forEach(s -> System.err.println(s));        
    }    
    
    public static List<List<Double>> algorithm(double[] data, int k){
        List<List<Double>> res = new ArrayList<>(); // 存放結果
        //System.out.print("中心點: ");
        List<Double> center = getCenter(data, k); // 存放中心點
        
        int count = 0;
        //直到中心點位置不變
        while(true){
            //System.out.println("最小距離的群組: ");
            List<List<Double>> group = getDistance(center, data);
            count++;
            //System.out.printf("第%d次, 取平均值後的中心點: \n", count);
            List<Double> newCenter = calcNewCenter(group); // 取平均值為中心點
            //若兩組的中心點不同, 重新計算取平均值為中心點
            if(!center.equals(newCenter)){
                //新的取代舊的, 下次再計算
                center = newCenter;
            }else{
                res = group;
                break;
            }
        }
        
        //res.stream().map(item -> item.toString()).forEach(s -> System.err.println(s));
        return res;
    }
    
    //取平均值為中心點
    public static List<Double> calcNewCenter(List<List<Double>> group){
        List<Double> res = new ArrayList<>();
        for(int item=0; item<group.size(); item++){
            double sum = 0.0;
            for(int index=0; index<group.get(item).size(); index++){
                sum += group.get(item).get(index);
            }
            res.add( sum / group.get(item).size());
        }
        return res;
    }
    
    //已經驗證過是預期的結果
    // 取得隨機中心點
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
        //System.out.println(res.stream().sorted().collect(Collectors.toList()).toString());
        return res.stream().sorted().collect(Collectors.toList());
    }
    
    //已經驗證過是預期的結果
    // 取得各中心點與各資料的距離
    public static List<List<Double>> getDistance(List<Double> center, double[] data){
        List<List<Double>> res = new ArrayList<>(); // 存放分組結果        
        for(int num=0; num<center.size(); num++){
            List<Double> group = new ArrayList<>();
            res.add(group);
        }
        
        for(int i=0; i<data.length; i++){//以資料跟中心點做比對
            List<Double> distance = new ArrayList<>(); // 存放與中心點的距離
            for(int j=0; j<center.size(); j++){
                //取得資料與給個中心點的距離
                distance.add(Math.abs(data[i] - center.get(j)));                
            }            
            //res.add(distance);
            //依據距離, 存入最靠近的中心點
            res.get(getMinDistance(distance)).add(data[i]);
        }
        //System.out.println("各資料與各中心點的距離: ");
        //res.stream().map(list -> list.toString()).forEach(s -> System.err.println(s));
        return res;     
    }
    
    //已經驗證過是預期的結果
    //計算最小的距離, 並返回中心點的index
    public static int getMinDistance(List<Double> distance){
        int index = 0;
        double value = distance.get(0);
        for(int i=1; i<distance.size(); i++){
            if(value > distance.get(i)){
                value = distance.get(i);
                index = i;
            }
        }
        //System.out.println(distance.toString() + " => " + index);
        return index;
    }
    
}
