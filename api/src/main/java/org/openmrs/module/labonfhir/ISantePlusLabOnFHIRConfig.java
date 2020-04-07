package org.openmrs.module.labonfhir;

import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Obs;
import org.openmrs.api.AdministrationService;

import org.openmrs.module.fhir2.FhirConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ISantePlusLabOnFHIRConfig {
	// https://wiki.openmrs.org/display/docs/Setting+and+Reading+Global+Properties?src=contextnavpagetreemode
	public static final String GP_TEST_ORDER_CONCEPT_UUID = "labonfihr.testsOrderedConceptUuid";

	public static final String GP_ORDER_DESTINATION_CONCEPT_UUID = "labonfhir.orderDestinationConceptUuid";

	public static final String GP_OPENELIS_URL = "https://testapi.openelisci.org:8444/hapi-fhir-jpaserver/";

	public static final String OPENELIS_USER_UUID = "3f7d1c6b-2781-4707-847c-03d4cb579470";

	@Autowired
	@Qualifier("adminService")
	AdministrationService administrationService;

	public String getOpenElisUrl() {
		//return GP_OPENELIS_URL
		return "http://hapi.fhir.org/baseR4";
	}

	public String getTestOrderConceptUuid() {
		return administrationService.getGlobalProperty(GP_TEST_ORDER_CONCEPT_UUID);
	}

	public String getOrderDestinationConceptUuid() {
		return administrationService.getGlobalProperty(GP_ORDER_DESTINATION_CONCEPT_UUID);
	}

	public String getOpenElisUserUuid() {
		return OPENELIS_USER_UUID;
	}

	public Predicate<Obs> isTestOrder() {
		final String testOrderConceptUuid = getTestOrderConceptUuid();
		return o -> testOrderConceptUuid.equals(o.getConcept().getUuid());
	}

	public boolean isOpenElisEnabled() {
		return StringUtils.isNotBlank(getOpenElisUrl());
	}
}
