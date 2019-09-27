package com.sample.dam.adapters;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory;

public class AssetAdapterFactory implements DocumentAdapterFactory {

    @Override
    public Object getAdapter(DocumentModel doc, Class<?> itf) {
        if ("Asset".equals(doc.getType()) && doc.hasSchema("dublincore")){
            return new AssetAdapter(doc);
        } else{
            return null;
        }
    }
}
