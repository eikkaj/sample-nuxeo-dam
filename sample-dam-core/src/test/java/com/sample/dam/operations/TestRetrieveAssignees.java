///*
// *     Jackie Aldama <jaldama@nuxeo.com>
// */
//package com.sample.dam.operations;
//
//import static org.junit.Assert.assertArrayEquals;
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.nuxeo.ecm.automation.AutomationService;
//import org.nuxeo.ecm.automation.OperationContext;
//import org.nuxeo.ecm.automation.OperationException;
//import org.nuxeo.ecm.automation.test.AutomationFeature;
//import org.nuxeo.ecm.core.api.CoreSession;
//import org.nuxeo.ecm.core.api.DocumentModel;
//import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
//import org.nuxeo.ecm.core.test.annotations.Granularity;
//import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
//import org.nuxeo.ecm.platform.usermanager.UserManager;
//import org.nuxeo.runtime.test.runner.Deploy;
//import org.nuxeo.runtime.test.runner.Features;
//import org.nuxeo.runtime.test.runner.FeaturesRunner;
//
///**
// * @since 1.0.0
// */
//@RunWith(FeaturesRunner.class)
//@Features(AutomationFeature.class)
//@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
//@Deploy("com.sample.dam.sample-dam-core")
//public class TestRetrieveAssignees {
//
//    @Inject
//    protected CoreSession session;
//
//    @Inject
//    protected AutomationService automationService;
//
//    @Inject
//    protected UserManager userManager;
//
//    @SuppressWarnings("unchecked")
//    @Before
//    public void setup() {
//        // Create marketing regular user
//        DocumentModel marketingRegular = userManager.getBareUserModel();
//        marketingRegular.setPropertyValue("user:username", "marketing_user");
//        userManager.createUser(marketingRegular);
//
//        // Create marketing manager user
//        DocumentModel marketingManager = userManager.getBareUserModel();
//        marketingManager.setPropertyValue("user:username", "marketing_manager");
//        userManager.createUser(marketingManager);
//
//
//        // Create managers group
//        DocumentModel managersGroupDoc = userManager.getBareGroupModel();
//        managersGroupDoc.setPropertyValue("group:groupname", "managers");
//        managersGroupDoc.setPropertyValue("group:grouplabel", "Managers");
//        managersGroupDoc = userManager.createGroup(managersGroupDoc);
//        List<String> managersMembers = (List<String>) managersGroupDoc.getPropertyValue("members");
//        managersMembers.add("marketing_manager");
//        managersGroupDoc.setPropertyValue("members", (Serializable) managersMembers);
//        userManager.updateGroup(managersGroupDoc);
//
//
//        // Create marketing group
//        DocumentModel marketingGroup = userManager.getBareGroupModel();
//        marketingGroup.setPropertyValue("group:groupname", "marketing");
//        marketingGroup.setPropertyValue("group:grouplabel", "Marketing");
//        marketingGroup = userManager.createGroup(marketingGroup);
//        List<String> marketingMembers = (List<String>) marketingGroup.getPropertyValue("members");
//        marketingMembers.add("marketing_user");
//        marketingMembers.add("marketing_manager");
//        marketingGroup.setPropertyValue("members", (Serializable) marketingMembers);
//        userManager.updateGroup(marketingGroup);
//    }
//
//    @Test
//    public void testGetManagersAssigneesList() throws OperationException {
//        DocumentModel doc = session.createDocumentModel("/", "myfile", "File");
//        OperationContext ctx = new OperationContext(session);
//        ctx.setInput(doc.getRef());
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("workspaceGroupName", "marketing");
//        params.put("roleGroupName", "managers");
//        @SuppressWarnings("unchecked")
//        List<String> assignees = (List<String>) automationService.run(ctx, RetrieveAssignees.ID, params);
//
//        assertArrayEquals(new String[] { "marketing_manager" }, assignees.toArray());
//    }
//}
