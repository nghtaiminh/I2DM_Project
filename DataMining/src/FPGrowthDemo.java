

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fp_growth.FPgrowth;



public class FPGrowthDemo {

    public static void main(String[] args) throws Exception {

        FPgrowth model = new FPgrowth("src\\data\\transactionDataByCusID_train.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S", "-R 1");
        model.mineAssociationRules();
        model.saveFrequentItemset("src\\data\\itemset_entiredata_train.csv");
        System.out.println("Association Rules for Entire train data" + model);

       FPgrowth model0 = new FPgrowth("src\\data\\transactionDataByCusID_test.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S", "-R 1");
        model0.mineAssociationRules();
        model0.saveFrequentItemset("src\\data\\itemset_entiredata_test.csv");
        System.out.println("Association Rules for Entire test data " + model0);
        
        FPgrowth model_cluster1 = new FPgrowth("src\\data\\cluster_1_transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S", "-R 1");
        model_cluster1.mineAssociationRules();
        model_cluster1.saveFrequentItemset("src\\data\\itemsets_cluster1.csv");
        System.out.println("Association Rules for cluster 1 data"+ model_cluster1);


        FPgrowth model_cluster2 = new FPgrowth("src\\data\\cluster_2_transaction_data.arff", "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S", "-R 1");
        model_cluster2.mineAssociationRules();
        model_cluster2.saveFrequentItemset("src\\data\\itemsets_cluster2.csv");
        System.out.println("Association Rules for cluster 2 data"+model_cluster2);


        FPgrowth model_cluster3 = new FPgrowth("src\\data\\cluster_3_transaction_data.arff", " -P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S", "-R 1");
        model_cluster3.mineAssociationRules();
        model_cluster3.saveFrequentItemset("src\\data\\itemsets_cluster3.csv");
        System.out.println("Association Rules for cluster 3 data"+model_cluster3); 



    }
}