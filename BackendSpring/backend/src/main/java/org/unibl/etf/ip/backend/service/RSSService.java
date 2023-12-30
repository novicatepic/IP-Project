package org.unibl.etf.ip.backend.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.RSSModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RSSService {

    @Value("${rss.url}")
    private String apiUrl;

    public List<RSSModel> fetchRssFeed() throws FeedException, IOException {
        List<RSSModel> result = new ArrayList<>();
        URL url = new URL(apiUrl);
        try (XmlReader reader = new XmlReader(url)) {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(reader);
            List<SyndEntry> entries = feed.getEntries();
            for(SyndEntry entry : entries) {
                result.add(new RSSModel(entry.getTitle(), entry.getLink(), entry.getDescription().getValue()));
            }
            return result;
        }
    }
}
