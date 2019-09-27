package com.sample.dam.operations;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.impl.DocumentModelListImpl;
import org.nuxeo.ecm.platform.tag.TagService;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(FeaturesRunner.class)
@Features({ AutomationFeature.class })
@Deploy({"org.nuxeo.ecm.platform.tag", "com.sample.dam.sample-dam-core"})
@Ignore
public class TestBulkTagOp {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    @Inject
    protected TagService tagService;

    DocumentModel doc1;

    DocumentModel doc2;

    DocumentModelListImpl list;

    @Before
    public void createDocuments() {
        doc1 = session.createDocumentModel("/","Doc 1", "File");
        doc1 = session.createDocument(doc1);

        doc2 = session.createDocumentModel("/","Doc 2", "File");
        doc2 = session.createDocument(doc2);

        session.save();

        list = new DocumentModelListImpl();
        list.add(doc1);
        list.add(doc2);
    }

    @Test
    public void shouldAddTagToDocument() throws OperationException {
        OperationContext ctx = new OperationContext(session);

        DocumentModel doc = session.createDocumentModel("/", "Test Document", "File");
        // set more props as needed
        doc.setPropertyValue("dc:description", "My Description");
        doc = session.createDocument(doc);
        session.save();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tags", "nuxeo,marketing,capital_one");

        ctx.setInput(doc.getRef());
        automationService.run(ctx, BulkTagOp.ID, params);

        // check if document got tagged
        Set<String> tags = tagService.getTags(session, doc.getId());
        assertEquals(tags.size(), 3);
    }

    @Test
    public void shouldAddTagToDocuments() throws OperationException {
        OperationContext ctx = new OperationContext(session);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tags", "nuxeo,marketing,capital_one");

        ctx.setInput(list);
        automationService.run(ctx, BulkTagOp.ID, params);

        for (DocumentModel doc : list) {
            // check if document got tagged
            Set<String> tags = tagService.getTags(session, doc.getId());
            assertEquals(tags.size(), 3);
        }
    }
}
