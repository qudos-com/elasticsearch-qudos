package com.qudos.elasticsearch;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettings;

public class ConcatenateTokenFilterFactory extends AbstractTokenFilterFactory {

    private String tokenSeparator = null;
    
    @Inject
    public ConcatenateTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings, name, settings);
        // the token_separator is defined in the ES configuration file
        tokenSeparator = settings.get("token_separator");
    }

    public TokenStream create(TokenStream tokenStream) {
        return new ConcatenateFilter(Version.LUCENE_CURRENT, tokenStream, tokenSeparator);
    }
}