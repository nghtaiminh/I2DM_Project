

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fp_growth.FPgrowth;

/**
 *
 * @author 
 */
public class FPGrowthDemo {

    public static void main(String[] args) throws Exception {

        FPgrowth model = new FPgrowth("src\\data\\transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.01", "-R 1");
        model.mineAssociationRules();
        System.out.println("Association Rules for Entire data" + model);
        
        FPgrowth model_cluster1 = new FPgrowth("src\\data\\cluster_1_transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.01", "-R 1");
        model_cluster1.mineAssociationRules();
        System.out.println("Association Rules for cluster 1 data"+ model_cluster1);
       
        
        FPgrowth model_cluster2 = new FPgrowth("src\\data\\cluster_2_transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.01", "-R 1");
        model_cluster2.mineAssociationRules();
        System.out.println("Association Rules for cluster 2 data"+model_cluster2);
        
        
        FPgrowth model_cluster3 = new FPgrowth("src\\data\\cluster_3_transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.01", "-R 1");
        model_cluster3.mineAssociationRules();
        System.out.println("Association Rules for cluster 3 data"+model_cluster3);
       
        
        
    }
}
