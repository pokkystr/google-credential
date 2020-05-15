package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

public class PubSubExt extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    protected PubSubExt(HttpTransport transport, JsonFactory jsonFactory, String rootUrl, String servicePath, HttpRequestInitializer httpRequestInitializer, boolean legacyDataWrapper) {
        super(transport, jsonFactory, rootUrl, servicePath, httpRequestInitializer, legacyDataWrapper);
    }

    @Override
    public AbstractGoogleJsonClient build() {
        return null;
    }
}
