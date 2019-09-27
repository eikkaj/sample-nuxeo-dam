package com.sample.dam.adapters;

import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;

import java.util.List;

public class AssetAdapter {

  protected DocumentModel doc;

  protected String titleXpath = "dc:title";
  protected String descriptionXpath = "dc:description";

  public AssetAdapter(DocumentModel doc) {
    this.doc = doc;
  }

  public void save() {
    CoreSession session = doc.getCoreSession();
    doc = session.saveDocument(doc);
  }

  public DocumentRef getParentRef() {
    return doc.getParentRef();
  }

  public String getId() {
    return doc.getId();
  }

  public String getName() {
    return doc.getName();
  }

  public String getPath() {
    return doc.getPathAsString();
  }

  public String getState() {
    return doc.getCurrentLifeCycleState();
  }

  // Metadata get / set
  public String getTitle() {
    return doc.getTitle();
  }

  public void setTitle(String value) {
    doc.setPropertyValue(titleXpath, value);
  }

  public String getDescription() {
    return (String) doc.getPropertyValue(descriptionXpath);
  }

  public void setDescription(String value) {
    doc.setPropertyValue(descriptionXpath, value);
  }

  public List<String> getRelatedAssets() {
    return (List<String>) doc.getPropertyValue("asset:related_assets");
  }
}
