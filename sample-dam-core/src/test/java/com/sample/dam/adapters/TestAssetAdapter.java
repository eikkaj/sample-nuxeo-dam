//package com.sample.dam.adapters;
//
//import javax.inject.Inject;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.nuxeo.ecm.core.api.CoreSession;
//import org.nuxeo.ecm.core.api.DocumentModel;
//import org.nuxeo.ecm.core.test.CoreFeature;
//import org.nuxeo.runtime.test.runner.Deploy;
//import org.nuxeo.runtime.test.runner.Features;
//import org.nuxeo.runtime.test.runner.FeaturesRunner;
//
//@RunWith(FeaturesRunner.class)
//@Features(CoreFeature.class)
//@Deploy({"com.sample.dam.sample-dam-core"})
//public class TestAssetAdapter {
//  @Inject
//  CoreSession session;
//
//  @Test
//  public void shouldCallTheAdapter() {
//    String doctype = "Asset";
//    String testTitle = "My Adapter Title";
//
//    DocumentModel doc = session.createDocumentModel("/", "test-adapter", doctype);
//    AssetAdapter adapter = doc.getAdapter(AssetAdapter.class);
//    adapter.setTitle(testTitle);
//    Assert.assertEquals("Document title does not match when using the adapter", testTitle, adapter.getTitle());
//  }
//}
