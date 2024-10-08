package com.film.gallery.config.containers;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ElasticsearchTestContainer extends ElasticsearchContainer {
    private static final String DOCKER_ELASTIC = "docker.elastic.co/elasticsearch/elasticsearch:7.17.6";

    private static final String CLUSTER_NAME = "sample-cluster";

    private static final String ELASTIC_SEARCH = "elasticsearch";

    public ElasticsearchTestContainer() {
        super(DOCKER_ELASTIC);
        this.addFixedExposedPort(9200, 9200);
        this.addFixedExposedPort(9300, 9300);
        this.withEnv("xpack.security.transport.ssl.enabled", "false");
        this.withEnv("xpack.security.http.ssl.enabled", "false");
        this.addEnv(CLUSTER_NAME, ELASTIC_SEARCH);
    }
}