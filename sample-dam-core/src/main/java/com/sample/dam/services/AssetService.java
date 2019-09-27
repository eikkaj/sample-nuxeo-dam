package com.sample.dam.services;

import com.sample.dam.adapters.AssetAdapter;
import org.nuxeo.ecm.core.api.CoreSession;

import java.util.List;

public interface AssetService {

    List<AssetAdapter> getRelatedAssets(CoreSession session, List<String> ids);
}
