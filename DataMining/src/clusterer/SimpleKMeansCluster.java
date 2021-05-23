package clusterer;

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
    private SimpleKMeans skm;
    private FilteredClusterer fc;
    private Instances data;

    public void buildSimpleKMeans(int numClusters) throws Exception{
        // Load dataset
        String dataset = "PreprocessedDataForCluster.csv";
        DataSource source = new DataSource("src\\data\\"+dataset);
        data = source.getDataSet();

        // Configure SimpleKMeans parameters
        SimpleKMeans skmeans = new SimpleKMeans();
        skmeans.setDistanceFunction(new weka.core.EuclideanDistance());
        skmeans.setOptions(weka.core.Utils.splitOptions("-init 0 -max-candidates 100 -periodic-pruning 10000 -min-density 2.0 -t1 -1.25 -t2 -1.0 -N 3 -A \"weka.core.EuclideanDistance -R first-last\" -I 500 -num-slots 1 "));
        skmeans.setNumClusters(numClusters);
        skm = skmeans;
        // Create Remove Filter
        Remove remove = new Remove();
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "1"; // this option is set to ignore CustomerID
        remove.setOptions(options);
        remove.setInputFormat(data);

        // build FilterClusterer with SimpleKMean
        FilteredClusterer filteredClusterer = new FilteredClusterer();
        filteredClusterer.setFilter(remove);
        filteredClusterer.setClusterer(skmeans);
        filteredClusterer.buildClusterer(data);
        fc = filteredClusterer;
//        System.out.println(filteredClusterer.toString());
    }

    public void getCentroids() {
        Instances instances = skm.getClusterCentroids();
        System.out.println(instances);
    }

    public double getSquaredError() {
        return skm.getSquaredError();
    }

    public void getSummary() {
        System.out.println(fc.toString());
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
        saver.setFile(new File("src\\data\\clusteredData.csv"));
        saver.writeBatch();
        System.out.println("New data is exported successfully!");
    }

}
