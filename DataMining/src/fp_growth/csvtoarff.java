package fp_growth;


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.core.converters.ArffSaver;
import java.io.File;
 
public class csvtoarff {
    public static void main(String[] args) throws Exception{
    	
    	Instances entire_data_train = DataSource.read("src\\data\\transactionDataByCusID_train.csv");
    	Instances entire_data_test = DataSource.read("src\\data\\transactionDataByCusID_test.csv");
        Instances cluster1 = DataSource.read("src\\data\\cluster_1_transaction_data.csv");
        Instances cluster2 = DataSource.read("src\\data\\cluster_2_transaction_data.csv");
        Instances cluster3 = DataSource.read("src\\data\\cluster_3_transaction_data.csv");
       
        entire_data_train.setClassIndex(entire_data_train.numAttributes()-1);
        entire_data_test.setClassIndex(entire_data_test.numAttributes()-1);
        cluster1.setClassIndex(cluster1.numAttributes()-1);
        cluster2.setClassIndex(cluster2.numAttributes()-1);
        cluster3.setClassIndex(cluster3.numAttributes()-1);
        
        //Numeric to Nominal
        NumericToNominal numericToNominal = new NumericToNominal();
		numericToNominal.setInputFormat(entire_data_train);
		
		NumericToNominal numericToNominal0 = new NumericToNominal();
		numericToNominal0.setInputFormat(entire_data_test);
          
        NumericToNominal numericToNominal1 = new NumericToNominal();
		numericToNominal1.setInputFormat(cluster1);
		
		 NumericToNominal numericToNominal2 = new NumericToNominal();
		numericToNominal2.setInputFormat(cluster2);
		
		 NumericToNominal numericToNominal3 = new NumericToNominal();
		numericToNominal3.setInputFormat(cluster3); 
		
		String[] options= new String[2];
		options[0] = "-R";
		options[1] = "first-last";
		
		numericToNominal.setOptions(options);
		numericToNominal1.setOptions(options);
		numericToNominal2.setOptions(options);
		numericToNominal3.setOptions(options);
		
		entire_data_train = Filter.useFilter(entire_data_train, numericToNominal);
		entire_data_test = Filter.useFilter(entire_data_test, numericToNominal);
		cluster1 = Filter.useFilter(cluster1, numericToNominal1);
		cluster2 = Filter.useFilter(cluster2, numericToNominal2);
		cluster3 = Filter.useFilter(cluster3, numericToNominal3);
		
		//Save data as .arff file
		ArffSaver saver = new ArffSaver();
        saver.setInstances(entire_data_train);
        saver.setFile(new File("src\\data\\transactionDataByCusID_train.arff"));
        saver.writeBatch();
        
        ArffSaver saver0 = new ArffSaver();
        saver0.setInstances(entire_data_test);
        saver0.setFile(new File("src\\data\\transactionDataByCusID_test.arff"));
        saver0.writeBatch();
		
        ArffSaver saver1 = new ArffSaver();
        saver1.setInstances(cluster1);
        saver1.setFile(new File("src\\data\\cluster_1_transaction_data.arff"));
        saver1.writeBatch();
        
        ArffSaver saver2 = new ArffSaver();
        saver2.setInstances(cluster2);
        saver2.setFile(new File("src\\data\\cluster_2_transaction_data.arff"));
        saver2.writeBatch();
        
        ArffSaver saver3 = new ArffSaver();
        saver3.setInstances(cluster3);
        saver3.setFile(new File("src\\data\\cluster_3_transaction_data.arff"));
        saver3.writeBatch(); 
        
        
        System.out.println ("All files have been converted into arrf file!");
    }
}

