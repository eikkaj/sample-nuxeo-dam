package com.sample.dam.elasticsearch;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.sample.dam.services.AssetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.nuxeo.elasticsearch.io.JsonESDocumentWriter;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PropertyException;
import org.nuxeo.runtime.api.Framework;

import com.sample.dam.adapters.AssetAdapter;

/**
 * Json ElasticSearch writer to allow indexing assets.
 *
 * @since 1.0.0
 */
public class AssetESDocumentWriter extends JsonESDocumentWriter {

    protected static final Log log = LogFactory.getLog(AssetESDocumentWriter.class);

    protected static final List<String> commonProperties = Arrays.asList("asset:language", "asset:country");

    @Override
    protected void writeSchemas(JsonGenerator jg, DocumentModel doc, String[] schemas) throws IOException {
        if (schemas == null || (schemas.length == 1 && "*".equals(schemas[0]))) {
            schemas = doc.getSchemas();
        }
        AssetAdapter asset = doc.getAdapter(AssetAdapter.class);


        for (String schema : schemas) {
            writeProperties(jg, doc, schema, null);
        }

        if (asset != null) {
            // Get related assets and write those properties too
            CoreSession session = doc.getCoreSession();
            AssetService assetService = Framework.getService(AssetService.class);
            List<AssetAdapter> relatedAssets = assetService.getRelatedAssets(session, asset.getRelatedAssets());

            // for each related asset, write the needed properties to the original asset in question
            for (String assetProp : commonProperties) {
                writeAssetPropertyArray(jg, relatedAssets, assetProp);
            }
        }
    }


    private void writeAssetPropertyArray(JsonGenerator jg, List<AssetAdapter> assets, String assetPropName)
            throws PropertyException, IOException {
        jg.writeFieldName(assetPropName);
        jg.writeStartArray();
        for (AssetAdapter asset : assets) {
            //asset.appendJsonProperty(jg, asset.getProperty(assetPropName));
        }
        jg.writeEndArray();
    }

}
