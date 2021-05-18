package cluster;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddCluster;
import weka.clusterers.FilteredClusterer;
import weka.filters.unsupervised.attribute.*;
import weka.core.converters.CSVSaver;

import java.io.File;

public class SimpleKMeansCluster {
    private FilteredClusterer fc;
    private Instances data;

    public void buildSimpleKMeans() throws Exception{
        // Load dataset
        String dataset = "PreprocessedDataForCluster.csv";
        DataSource source = new DataSource("src\\data\\"+dataset);
        data = source.getDataSet();

        // Configure SimpleKMeans parameters
        SimpleKMeans skmean = new SimpleKMeans();
        skmean.setDistanceFunction(new weka.core.EuclideanDistance());
        skmean.setNumClusters(3);
        // Create Remove Filter
        Remove remove = new Remove();
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "1,2"; // this option is set to ignore InvoiceNo, StockCode attr
        remove.setOptions(options);
        remove.setInputFormat(data);

        // build FilterClusterer with SimpleKMean
        FilteredClusterer filteredClusterer = new FilteredClusterer();
        filteredClusterer.setFilter(remove);
        filteredClusterer.setClusterer(skmean);
        filteredClusterer.buildClusterer(data);
        fc = filteredClusterer;
        System.out.println(filteredClusterer.toString());
    }

    public void saveModel() throws Exception {
        weka.core.SerializationHelper.write("src\\skmean_cluster_model.model", fc);
        System.out.println("Model is saved successfully!");
    }

    public void exportDataWithCluster() throws Exception {
        AddCluster addCluster = new AddCluster();
        addCluster.setClusterer(fc);
        addCluster.setInputFormat(data);
        Instances newData = Filter.useFilter(data, addCluster);
        CSVSaver saver = new CSVSaver();
        saver.setInstances(newData);
        saver.setFile(new File("src\\data\\NewDataAfterCluster.csv"));
        saver.writeBatch();
        System.out.println("New data is exported successfully!");
    }

}
