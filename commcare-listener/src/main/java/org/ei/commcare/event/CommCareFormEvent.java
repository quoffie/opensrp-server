package org.ei.commcare.event;

import org.ei.commcare.domain.CommcareForm;
import org.motechproject.model.MotechEvent;

import java.util.HashMap;
import java.util.Map;

public class CommCareFormEvent {
    private final CommcareForm form;
    private final Map<String, String> fieldsInXMLWeCareAbout;

    public CommCareFormEvent(CommcareForm form, Map<String, String> fieldsInXMLWeCareAbout) {
        this.form = form;
        this.fieldsInXMLWeCareAbout = fieldsInXMLWeCareAbout;
    }

    public MotechEvent toMotechEvent() {
        HashMap<String, Object> parameters = new HashMap<String, Object>(fieldsInXMLWeCareAbout);
        return new MotechEvent(form.definition().name(), parameters);
    }
}
