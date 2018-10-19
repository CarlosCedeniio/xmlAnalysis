package com.temnet.pruebas;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.bson.Document;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryWatcher {
    

    public static void EnableDirectoryWatcher(Path incoming, Path backup,Path temp, WatchService watcher) throws IOException, InterruptedException {

        WatchKey key;
        String json = "";
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase db =mongoClient.getDatabase("Investigacion");
        MongoCollection col = db.getCollection("prueba8");
        try {
            key = incoming.register(watcher,
                    ENTRY_CREATE
                    );
        } catch (IOException x) {
            System.err.println(x);
        }

        for (;;) {

            // wait for key to be signaled

            key = watcher.take();


            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // This key is registered only
                // for ENTRY_CREATE events,
                // but an OVERFLOW event can
                // occur regardless if events
                // are lost or discarded.
                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();
                FileInputStream inputStream = null;
                Scanner sc = null;
                json="";

                // Verify that the new
                //  file is a text file.

                    // Resolve the filename against the directory.
                    // If the filename is "test" and the directory is "foo",
                    // the resolved name is "test/foo".
                Path child = incoming.resolve(filename);
                Path temp1 = temp.resolve(filename);
                Path destination = backup.resolve(filename);
                ////////////////////////////////////////
                Path temp2 = Files.copy
                        (Paths.get(child.toString()),
                                Paths.get(temp1.toString()));
                //Thread.sleep(1);




                ////////////////
                    // Email the file to the
                    //  specified email alias.
                    System.out.println("."+child.toString());



                try {
                    inputStream = new FileInputStream(temp1.toString());
                    sc = new Scanner(inputStream, "UTF-8");
                    while (sc.hasNextLine()) {
                        json += sc.nextLine();
                        // System.out.println(line);
                    }
                    // note that Scanner suppresses exceptions
                    if (sc.ioException() != null) {
                        throw sc.ioException();
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (sc != null) {
                        sc.close();
                    }
                }




                //ReadSMS sms_reader = gson.fromJson(reader, ReadSMS.class);
                    Document doc = Document.parse(json);
                System.out.println(doc.get("data_bgp_instances_instance_instance-active_default-vrf_neighbors_neighbor_message-statistics_keepalive_rx_count"));
                //DBObject dbObject = (DBObject)JSON.parse(json);
                    col.insertOne(doc);
                Path temp3 = Files.move
                        (Paths.get(child.toString()),
                                Paths.get(destination.toString()));





        }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid,
            // the directory is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
        mongoClient.close();
    }
}
