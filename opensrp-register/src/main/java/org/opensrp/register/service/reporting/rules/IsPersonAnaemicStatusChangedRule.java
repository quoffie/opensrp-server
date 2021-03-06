package org.opensrp.register.service.reporting.rules;

import org.opensrp.service.reporting.rules.IRule;
import org.opensrp.util.SafeMap;
import org.springframework.stereotype.Component;

import static org.opensrp.common.AllConstants.HbTestFormFields.ANAEMIC_STATUS_FIELD;
import static org.opensrp.common.AllConstants.HbTestFormFields.PREVIOUS_ANAEMIC_STATUS_FIELD;

@Component
public class IsPersonAnaemicStatusChangedRule implements IRule {

    @Override
    public boolean apply(SafeMap reportFields) {
        String anaemicStatus = reportFields.get(ANAEMIC_STATUS_FIELD) != null ? reportFields.get(ANAEMIC_STATUS_FIELD) : "";
        String previousAnaemicStatus = reportFields.get(PREVIOUS_ANAEMIC_STATUS_FIELD);

        return !anaemicStatus.equalsIgnoreCase(previousAnaemicStatus);
    }
}
