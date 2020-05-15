package com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.PubsubScopes;
import com.google.api.services.pubsub.model.PullRequest;
import com.ktb.ssa.listener.pubsub.register.ssalistenerpubsubregisterservice.google.oauth2.GoogleCredential;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
class SsaListenerPubsubRegisterServiceApplicationTests {

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

	@Test
	void contextLoads() throws IOException, GeneralSecurityException {
		Pubsub pubsub = createPubsubClient_1();
		PullRequest pullRequest = new PullRequest().setReturnImmediately(false).setMaxMessages(BATCH_SIZE);

		pubsub.projects().subscriptions().pull(subscriptionName, pullRequest).execute().getReceivedMessages().forEach(receivedMessage -> {
			System.out.println(receivedMessage.getMessage());
			/*try {
				System.out.println(new String(receivedMessage.getMessage().decodeData(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
		});
	}

}
