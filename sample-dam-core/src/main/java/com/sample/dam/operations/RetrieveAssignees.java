/*
 *     Jackie Aldama <jaldama@nuxeo.com>
 */
package com.sample.dam.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.usermanager.UserAdapter;
import org.nuxeo.ecm.platform.usermanager.UserManager;


/**
 * Operation to retrieve the list of assignees for the Approval workflow.
 *
 * @since 1.0.0
 */
@Operation(id = RetrieveAssignees.ID, category = "sample", label = "Get assignees", description = "Get the user list of assignees for the Approval, Work Task, and Request workflows.")
public class RetrieveAssignees {

    public static final String ID = "sample.RetrieveAssignees";

    @Context
    protected UserManager userManager;

    @Param(name = "roleGroupName", required = false)
    protected String roleGroupName;

    @Param(name = "workspaceGroupName", required = true)
    protected String workspaceGroupName;

    @Param(name = "includeAdministrators", required = false)
    protected Boolean includeAdministrators = false;

    @OperationMethod
    public List<String> run() {
        List<String> result = new ArrayList<>();

        
        // Get the administrators
        if (includeAdministrators) {
            result = userManager.getUsersInGroup("Administrators");
        }

        List<String> userList = userManager.getUsersInGroup(workspaceGroupName);

        // If roleGroupName has a value, filter the users with only those who are members of the given roleGroupName
		if (roleGroupName == null || roleGroupName.isEmpty()) {
			result.addAll(userList);
        } else {
            for (String userName : userList) {
                DocumentModel user = userManager.getUserModel(userName);
                UserAdapter userAdapter = userManager.getUserModel(user.getId()).getAdapter(UserAdapter.class);

                List<String> groups = userAdapter.getGroups();
                if (groups != null && groups.contains(roleGroupName)) {
               		result.add(userName);
                }
            }
        }

        return result.stream().distinct().collect(Collectors.toList());
    }

}
