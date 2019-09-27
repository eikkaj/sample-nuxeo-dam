//package com.sample.dam.listeners;
//
//import org.nuxeo.ecm.core.api.DocumentModel;
//import org.nuxeo.ecm.core.event.Event;
//import org.nuxeo.ecm.core.event.EventContext;
//import org.nuxeo.ecm.core.event.EventListener;
//import org.nuxeo.ecm.core.event.impl.UnboundEventContext;
//import org.nuxeo.ecm.platform.usermanager.UserManager;
//import org.nuxeo.runtime.api.Framework;
//
//import java.io.Serializable;
//import java.util.Map;
//
//public class UserCreationListener implements EventListener {
//    @Override
//    public void handleEvent(Event event) {
//        EventContext ctx = event.getContext();
//        if (!(ctx instanceof UnboundEventContext)) {
//            return;
//        }
//
//        event.getName().equals("user_Created")
//
//        UserManager userManager = Framework.getService(UserManager.class);
//
//        Map<String, Serializable> properties = ctx.getProperties();
//        Iterator<Map.Entry<String, Serializable>> it = properties.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Serializable> pair = it.next();
//            if (pair.getKey().equals(USER_ID)) {
//                // Retrieve the user
//                DocumentModel user = userManager.getUserModel(pair.getValue().toString());
//                if (user != null) {
//                    logger.info("New user created: " + user.getTitle() + ". Assigning to Marketing Access and members group.");
//                    ArrayList<String> userGroups = (ArrayList<String>) user.getProperty(SCHEMA_NAME, GROUPS_COLUMN);
//                    userGroups.add(MARKETING);
//                    userGroups.add(MEMBERS);
//                    user.setProperty(SCHEMA_NAME, GROUPS_COLUMN, userGroups);
//                    userManager.updateUser(user);
//                }
//            }
//        }
//    }
//    }
//}
