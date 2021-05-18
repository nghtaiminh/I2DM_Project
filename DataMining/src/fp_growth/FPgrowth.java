package fp_growth;



import weka.associations.FPGrowth;
import weka.core.Instances;

/**
 *
 * @author
 */
public class FPgrowth extends KnowledgeModel {

    Instances newData;
    FPGrowth fp;

    public FPgrowth(String filename, String m_options, String d_options) throws Exception {
        super(filename, m_options, d_options);
        this.fp = new FPGrowth();
    }

    public void mineAssociationRules() throws Exception {
        //loc du lieu
        this.newData = removeData(this.dataset);
        //thiet lap thong so
        fp.setOptions(this.model_options);
        //khai pha
        fp.buildAssociations(this.newData);
    }

    @Override
    public String toString() {
        return fp.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
