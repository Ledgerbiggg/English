package com.ledger.es_test1.connection;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author ledger
 * @version 1.0
 **/
public class ElasticSearchConnection {
    private volatile static RestHighLevelClient client;

    public static RestHighLevelClient getClient() {
        if (client == null) {
            synchronized (ElasticSearchConnection.class) {
                if (client == null) {
                    HttpHost http = new HttpHost("106.54.9.19", 9200, "http");
                    RestClientBuilder builder = RestClient.builder(http);
                    client = new RestHighLevelClient(builder);
                }
            }
        }
        return client;
    }

    public static void closeClient() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
