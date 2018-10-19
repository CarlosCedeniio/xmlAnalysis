package com.temnet.pruebas;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
@RestController
public class PruebasApplicationread {
    static Set<Integer> var = new HashSet<>();
    static ArrayList<String> x = new ArrayList<>();
    static ArrayList<String> x1 = new ArrayList<>();
    public static Map<String, Integer> etapa1 = new HashMap<String, Integer>();
    public static Map<String, ArrayList> DataSeriesSet = new HashMap<String, ArrayList>();
    public static Map<String, Integer> keyset1 = new HashMap<String, Integer>();
    public static Map<Integer, String> keyset2= new HashMap<Integer, String>();

//    static Map<String, Integer> etapa2 = new HashMap<String, Integer>();
//    static int etapa1size = 0;
//    static int etapa2size = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(PruebasApplicationread.class, args);
//        Path incoming = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\incoming");
//        Path backup = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\backup");
//        Path temp = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\temp");
//        WatchService watcher = FileSystems.getDefault().newWatchService();
//        DirectoryWatcher.EnableDirectoryWatcher(incoming,backup,temp,watcher);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase db = mongoClient.getDatabase("Investigacion");
        MongoCollection col = db.getCollection("prueba8");
        //System.out.println(JSON.serialize(col.find().first()));
        MongoIterable dbiter = col.find();
        MongoIterable dbiter1 = col.find();
        MongoCursor cursor = dbiter.iterator();

        MongoCursor cursor2 = dbiter.iterator();
        ArrayList<Document> etapa1List = new ArrayList<>();
        ArrayList<Document> etapa2List = new ArrayList<>();


//        //mainList.addAll(set);
//        while(cursor1.hasNext()){
//            if (etapa1size==0) {
//                etapa1size = ((Document) cursor1.next()).size();
//            }
//            System.out.println(etapa1size);
//            System.out.println(etapa2size);
//            Document doctemp = (Document) cursor1.next();
//            System.out.println("AQUIIIIIIIIIIIIIIIIIIIIII"+doctemp.keySet().size());
//            if (doctemp.keySet().size()!=etapa1size){
//                etapa2size=doctemp.keySet().size();
//            }
//            else{
//                System.out.println("Doc no cayo en ninguna etapa");
//            }
//
//        }
//        System.out.println(etapa1size);
//        System.out.println(etapa2size);
//        Thread.sleep(10000);
//
//        while(cursor2.hasNext()){
//            Document temp = (Document) cursor2.next();
//            if (temp.keySet().size()==etapa1size){
//                etapa1List.add(temp);
//            }else if (temp.keySet().size()==etapa2size){
//                etapa2List.add(temp);
//            }
//
//        }
//
//        for (Document doc1:etapa1List) {
//            if (doc1.keySet().size()==etapa1size) {
//                if (etapa1.isEmpty()) {
//
//                    x.addAll(doc1.keySet());
//                    for (int i = 0; i < x.size(); i++) {
//                        etapa1.put(x.get(i), 0);
//                    }
//                }
//                for (Document doc2:etapa1List) {
//                    if (doc2.keySet().size()==etapa1size) {
//                        for (int j = 0; j < x.size(); j++) {
//                            if (doc1.get(x.get(j)).equals(doc2.get(x.get(j)))) {
//                                etapa1.put(x.get(j), etapa1.get(x.get(j)) + 1);
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//        for (Document doc1:etapa2List) {
//            if (doc1.keySet().size()==etapa2size) {
//                if (etapa2.isEmpty()) {
//
//                    x1.addAll(doc1.keySet());
//                    for (int i = 0; i < x1.size(); i++) {
//                        etapa2.put(x1.get(i), 0);
//                    }
//                }
//                for (Document doc2:etapa2List) {
//                    if (doc2.keySet().size()==etapa2size) {
//                        for (int j = 0; j < x1.size(); j++) {
//                            if (doc1.get(x1.get(j)).equals(doc2.get(x1.get(j)))) {
//                                etapa2.put(x1.get(j), etapa2.get(x1.get(j)) + 1);
//                            }
//                        }
//                    }
//                }
//            }
//
//        }

        while (cursor.hasNext()) {
            Document doc1 = (Document) cursor.next();

            //carga keyset de tags en primera corrida
            //if (doc1.keySet().size()==etapa1size) {
                if (etapa1.isEmpty()) {

                    x.addAll(doc1.keySet());
                    for (int i = 0; i < x.size(); i++) {
                        etapa1.put(x.get(i), 0);
                        keyset1.put( x.get(i),i);
                        keyset2.put( i,x.get(i));
                        DataSeriesSet.put(x.get(i),new ArrayList());
                    }
               // }
                    MongoCursor cursor1 = dbiter1.iterator();


                while (cursor1.hasNext()) {
                    Document doc2 = (Document) cursor1.next();

                    //System.out.println(doc1.get("data_bgp_instances_instance_instance-active_default-vrf_neighbors_neighbor_message-statistics_keepalive_tx_count"));
                    //System.out.println(doc2.get("data_bgp_instances_instance_instance-active_default-vrf_neighbors_neighbor_message-statistics_keepalive_tx_count"));
                    //if (doc2.keySet().size()==etapa1size) {
                        for (int j = 0; j < x.size(); j++) {
                            //Crear serie de valores de todos los documentos
                            if (DataSeriesSet.isEmpty()){
                                ArrayList temp = DataSeriesSet.get(x.get(j));
                                temp.add(doc1.get(x.get(j)));
                                DataSeriesSet.put(x.get(j),temp);
                            }
                            ArrayList temp = DataSeriesSet.get(x.get(j));
                            temp.add(doc2.get(x.get(j)));
                            DataSeriesSet.put(x.get(j),temp);
//                            if (valuesSeries.isEmpty()){
//                                valuesSeries.add((doc1.get(x.get(j))));
//                            }
//                            valuesSeries.add((doc2.get(x.get(j))));
//                            DataSeriesSet.put(x.get(j),valuesSeries);
                            /////////////////////////////////////////////////////////////
                            if ((doc1.get(x.get(j))).equals(doc2.get(x.get(j)))) {

                                etapa1.put(x.get(j), (etapa1.get(x.get(j))+ 1));
                            }
                        }
                    //}
                }
            }
       }

        System.out.println("finished");
        //System.out.println(etapa1);
        //System.out.println(etapa1.keySet().size());
        //System.out.println(etapa2);
        //System.out.println(etapa2.keySet().size());

//        HashMap<String,Object> result = new ObjectMapper().readValue(JSON.serialize(col.find().first()), HashMap.class);
//
//        result.keySet();
            mongoClient.close();
        }


    @RequestMapping(value = "etapa1", method = RequestMethod.GET)
    public Map<String, Integer> getEtapa1() {
        return etapa1;
    }
    @RequestMapping(value = "dataserie", method = RequestMethod.GET)
    public Map<String, ArrayList> getDataSerie() {
        return DataSeriesSet;
    }
    @RequestMapping(value = "/keyset/etapa2", method = RequestMethod.GET)
    public Map<String, Integer> getEtapa2() {
        return etapa1;
    }

    @RequestMapping(value = "/keyset/{id}", method = RequestMethod.GET)
    public String getNode(
            @PathVariable("id") int id) {

        return keyset2.get(id);
    }

    //public fillStages()

}

