package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.compute.ComputeCredential;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.PubsubScopes;
import com.google.api.services.pubsub.model.*;
import com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice.wrapper.RetryHttpInitializerWrapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {

    static HttpTransport httpTransport = Utils.getDefaultTransport();
    static HttpTransport transport;
    static File file;

    static {
        try {
            file = ResourceUtils.getFile("classpath:poc-hiperledge.p12");
            transport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
    //    static String CREDENTIAL_FILEPATH = "/Users/pigke/Downloads/demoBigQuery/src/main/resources/poc-hiperledge.p12";
    static String subscriptionName = "projects/poc-hiperledger/subscriptions/pigkeSubscribers";
    static String topicName = "projects/poc-hiperledger/topics/gcp.demo.pigke";
    static String serviceAccountId = "poc-hiperledger@cloudservices.gserviceaccount.com";
    static String serviceAccountEmail = "626582378250-compute@developer.gserviceaccount.com";
    static int BATCH_SIZE = 1000;

    public static Pubsub createPubsubClient_2() throws GeneralSecurityException, IOException {
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        ComputeCredential credential = new ComputeCredential.Builder(transport, jsonFactory).build();
        HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
        return new Pubsub.Builder(transport, jsonFactory, initializer).build();
    }

    public static Pubsub createPubsubClient_3() throws IOException {
        GoogleCredential credential = new AppIdentityCredential.AppEngineCredentialWrapper(httpTransport, jsonFactory).createScoped(PubsubScopes.all());
        HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
        return new Pubsub.Builder(httpTransport, jsonFactory, initializer).build();
    }

    public static void createPubsubClient_4() throws GeneralSecurityException, IOException {
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountScopes(PubsubScopes.all())
                .setServiceAccountPrivateKeyFromP12File(file)
                .build();
    }

    public static Pubsub createPubsubClient_1() throws IOException, GeneralSecurityException {
//        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        HttpTransport transport = Utils.getDefaultTransport();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(transport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountScopes(PubsubScopes.all())
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountPrivateKeyFromP12File(file)
                .build();

//        HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
        return new Pubsub.Builder(transport, jsonFactory, credential).build();
    }

    public static void main(String[] args) {
        try {
            Pubsub pubsub = createPubsubClient_1();
            System.out.println(pubsub.projects().topics().get(topicName));

            PullRequest pullRequest = new PullRequest().setReturnImmediately(false).setMaxMessages(500);
            System.out.println(pubsub.projects().subscriptions().pull(subscriptionName, pullRequest).execute().getReceivedMessages().size());

//            PullResponse pullResponse = pubsub.projects().subscriptions().pull(subscriptionName, pullRequest).execute();
//            List<String> ackIds = new ArrayList<>(BATCH_SIZE);
//            List<ReceivedMessage> receivedMessages = pullResponse.getReceivedMessages();
//            if (receivedMessages != null) {
//                int count = 0;
////                for (int i = 0; i < 2; i++) {
//                    for (ReceivedMessage receivedMessage : receivedMessages) {
//                        PubsubMessage pubsubMessage = receivedMessage.getMessage();
//                        if (pubsubMessage != null && pubsubMessage.decodeData() != null) {
//                            System.out.println("count :  " + count++ + " " + new String(pubsubMessage.decodeData(), "UTF-8"));
//                        }
//                        ackIds.add(receivedMessage.getAckId());
//                    }
//                    AcknowledgeRequest ackRequest = new AcknowledgeRequest();
//                    ackRequest.setAckIds(ackIds);
//                    pubsub.projects().subscriptions().acknowledge(subscriptionName, ackRequest).execute();
//
//                    System.out.println("++++++++++" + count + "++++++++++++++");
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
