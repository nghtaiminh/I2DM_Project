import clusterer.SimpleKMeansCluster;

public class SimpleKMeansDemo {
    public static void main(String[] args) throws Exception {
        SimpleKMeansCluster skmean = new SimpleKMeansCluster();
//        for (int i = 1; i <= 10; i++) {
//            skmean.buildSimpleKMeans(i);
//            System.out.println(skmean.getSquaredError());
//        }
        skmean.buildSimpleKMeans(3);
        skmean.getSummary();
        skmean.exportDataWithCluster();
        skmean.saveModel();
    }

}
