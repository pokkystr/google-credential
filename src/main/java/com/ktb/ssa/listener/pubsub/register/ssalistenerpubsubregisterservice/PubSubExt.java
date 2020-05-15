package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.PubsubRequestInitializer;

public class PubSubExt extends AbstractGoogleJsonClient {
    protected PubSubExt(Builder builder) {
        super(builder);
    }

    PubSubExt(Pubsub.Builder var1) {
        super(var1);
    }



    public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {
        public Builder(HttpTransport var1, JsonFactory var2, HttpRequestInitializer var3) {
            super(var1, var2, "https://pubsub.googleapis.com/", "", var3, false);
            this.setBatchPath("batch");
        }

        public PubSubExt build() {
            return new PubSubExt(this);
        }

        public Pubsub.Builder setRootUrl(String var1) {
            return (Pubsub.Builder) super.setRootUrl(var1);
        }

        public Pubsub.Builder setServicePath(String var1) {
            return (Pubsub.Builder) super.setServicePath(var1);
        }

        public Pubsub.Builder setBatchPath(String var1) {
            return (Pubsub.Builder) super.setBatchPath(var1);
        }

        public Pubsub.Builder setHttpRequestInitializer(HttpRequestInitializer var1) {
            return (Pubsub.Builder) super.setHttpRequestInitializer(var1);
        }

        public Pubsub.Builder setApplicationName(String var1) {
            return (Pubsub.Builder) super.setApplicationName(var1);
        }

        public Pubsub.Builder setSuppressPatternChecks(boolean var1) {
            return (Pubsub.Builder) super.setSuppressPatternChecks(var1);
        }

        public Pubsub.Builder setSuppressRequiredParameterChecks(boolean var1) {
            return (Pubsub.Builder) super.setSuppressRequiredParameterChecks(var1);
        }

        public Pubsub.Builder setSuppressAllChecks(boolean var1) {
            return (Pubsub.Builder) super.setSuppressAllChecks(var1);
        }

        public Pubsub.Builder setPubsubRequestInitializer(PubsubRequestInitializer var1) {
            return (Pubsub.Builder) super.setGoogleClientRequestInitializer(var1);
        }

        public Pubsub.Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer var1) {
            return (Pubsub.Builder) super.setGoogleClientRequestInitializer(var1);
        }
    }
}
