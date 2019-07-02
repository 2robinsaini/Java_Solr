package RSys.Solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

/**
 * 
 * @author Robin.Kumar
 *
 */
public class App {
	public static void main(String[] args) throws SolrServerException, IOException {
		String urlString = "http://10.131.65.78:8983/solr/my_core";
		SolrClient client = new HttpSolrClient.Builder(urlString).build();

		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		//query.addFilterQuery("fileText:HR Manual");
		// query.setFields("id","Keyword","startTime","endTime","speakerType");
		// query.setFields("id","Keywords","KeywordId");
		query.setStart(0);
		query.set("rows", "1000000");
		//query.set("defType", "edismax");
		final long startTime = System.nanoTime();
		QueryResponse response = client.query(query);
		SolrDocumentList results = response.getResults();
		int sz = results.size();
		System.out.println(sz);
		System.out.println("No. of records in output: " + results.getNumFound());
		for (int i = 0; i < results.size(); ++i) {
			System.out.println(results.get(i).get("pid"));
		}

	}
}
