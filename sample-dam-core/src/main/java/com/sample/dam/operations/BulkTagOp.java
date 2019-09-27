package com.sample.dam.operations;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.automation.core.util.StringList;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.platform.tag.TagService;

@Operation(id = BulkTagOp.ID, category = "sample", label = "Bulk Tag Documents", description = "Bulk Tag Documents")
public class BulkTagOp {

    public static final String ID = "Sample.BulkTag";

    @Param(name="tags", description="Tags to be added to the doc(s), separated by commas", required = true)
    protected StringList tags;

    @Context
    protected TagService tagService;

    @Context
    protected CoreSession session;

    @OperationMethod
    public DocumentModel run(DocumentModel document) {

        for (String tag : tags) {
            tagService.tag(session, document.getId(), tag);
        }
        return document;
    }

    @OperationMethod
    public DocumentModelList run(DocumentModelList documents) {
        for (DocumentModel document : documents) {
            for (String tag : tags) {
                tagService.tag(session, document.getId(), tag);
            }
        }
        return documents;
    }
}
