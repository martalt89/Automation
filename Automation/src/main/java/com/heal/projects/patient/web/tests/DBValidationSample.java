package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import org.testng.annotations.Test;
import static com.heal.framework.foundation.DBResult.cell;

/**
 * Created by vahanmelikyan on 9/27/2017.
 */
public class DBValidationSample extends TestBase {


    @Test
    public void dbCancelReasonVerify() throws  Exception{
        connector.param("id", "4").query("cancel_reason");

        assertEquals("verfiy value approval_required", cell(0, "approval_required"), "f");
        assertEquals("verfiy value client", cell(0, "client"), "ADMIN");
    }

    @Test
    public void dbUserAccountVerify() throws  Exception{
        connector.query("user_account");

        assertEquals("verfiy value firstname", cell(0, "first_name"), "Vahan");
        assertEquals("verfiy value lastname", cell(0, "last_name"), "Melikyan");
    }
}
