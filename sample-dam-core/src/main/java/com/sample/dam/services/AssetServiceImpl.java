package com.sample.dam.services;

import com.sample.dam.adapters.AssetAdapter;
import org.nuxeo.ecm.core.api.CoreSession;

import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.model.Property;
import org.nuxeo.ecm.core.query.sql.NXQL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssetServiceImpl implements AssetService {

    @Override
    public List<AssetAdapter> getRelatedAssets(CoreSession session, List<String> ids) {
        String joinedIds = this.getEscapedJoinedIds(ids);

        if(joinedIds.isEmpty()) {
            return new ArrayList<AssetAdapter>();
        }

        DocumentModelList results = session.query(String.format("SELECT * FROM ASSET WHERE id IN (%s)", joinedIds));
        return results.stream().map(p -> p.getAdapter(AssetAdapter.class)).collect(Collectors.toList());
    }

    private String getEscapedJoinedIds(List<String> list) {
        List<String> escapedIds = list.stream().map(NXQL::escapeString).collect(Collectors.toList());
        return String.join(",", escapedIds);
    }
}
