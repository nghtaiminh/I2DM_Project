package fp_growth;


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.core.converters.ArffSaver;
import java.io.File;
 
public class csvtoarff {
    public static void main(String[] args) throws Exception{
    	
    	Instances entire_data = DataSource.read("src\\data\\transaction_data.csv");
        Instances cluster1 = DataSource.read("src\\data\\cluster_1_transaction_data.csv");
        Instances cluster2 = DataSource.read("src\\data\\cluster_2_transaction_data.csv");
        Instances cluster3 = DataSource.read("src\\data\\cluster_3_transaction_data.csv");
       
        entire_data.setClassIndex(cluster1.numAttributes()-1);
        cluster1.setClassIndex(cluster1.numAttributes()-1);
        cluster2.setClassIndex(cluster2.numAttributes()-1);
        cluster3.setClassIndex(cluster3.numAttributes()-1);
        
        //Numeric to Nominal
        NumericToNominal numericToNominal = new NumericToNominal();
		numericToNominal.setInputFormat(entire_data);
          
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
		
		entire_data = Filter.useFilter(entire_data, numericToNominal);
		cluster1 = Filter.useFilter(cluster1, numericToNominal1);
		cluster2 = Filter.useFilter(cluster2, numericToNominal2);
		cluster3 = Filter.useFilter(cluster3, numericToNominal3);
		
		//Save data as .arff file
		ArffSaver saver = new ArffSaver();
        saver.setInstances(entire_data);
        saver.setFile(new File("src\\data\\transaction_data.arff"));
        saver.writeBatch();
		
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

