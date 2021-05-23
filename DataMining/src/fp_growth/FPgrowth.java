package fp_growth;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.FPGrowth;
import weka.associations.Item;
import weka.core.Instances;

import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

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
       
        this.newData = removeData(this.dataset);
        
        fp.setOptions(this.model_options);
       
        fp.buildAssociations(this.newData);
    }

    // get association rules
    public List<AssociationRule> getAssociationRules(){
        return fp.getAssociationRules().getRules();
    }

    @Override
    public String toString() {
        return fp.toString(); 
    }

    public void saveFrequentItemset(String path) {
        AssociationRules associationRules = this.fp.getAssociationRules();
        List<AssociationRule> listAssociattionRule = associationRules.getRules();

        HashMap<Collection<Item>, Integer> frequentItemSets = new HashMap<>();
        for (AssociationRule ar : listAssociattionRule){
            Collection<Item> itemSet = ar.getPremise();
            itemSet.addAll(ar.getConsequence());
            if (!frequentItemSets.keySet().contains(itemSet)){
                frequentItemSets.put(itemSet, ar.getTotalSupport());
            }else{
                continue;
            }
        }
        try{
            FileWriter fileWriter = new FileWriter(path);
//            fileWriter.write("@RELATION frequent_item_set\n" +
//                    "@ATTRIBUTE item_set string\n");
//            fileWriter.write("@ATTRIBUTE support NUMERIC\n" +
//                    "@DATA\n");
            for (Collection<Item> fi : frequentItemSets.keySet() ){
                ArrayList<String> items = new ArrayList<>();
                for (Item i : fi){
                    items.add(i.toString().split("=")[0]);
                }
                fileWriter.write(String.join(";", items));
                fileWriter.write("," + frequentItemSets.get(fi).toString() + "\n");
            }
            fileWriter.close();
            System.out.println("write successfully");
        }catch (IOException e){
            System.out.println("Write file error");
        }
    }
}
