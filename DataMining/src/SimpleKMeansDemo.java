import clusterer.SimpleKMeansCluster;

public class SimpleKMeansDemo {
    public static void main(String[] args) throws Exception {
        SimpleKMeansCluster skmean = new SimpleKMeansCluster();
        skmean.buildSimpleKMeans();
        skmean.exportDataWithCluster();
        skmean.saveModel();
    }

}
