package org.macula.cloud.cainiao;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.macula.cloud.cainiao.AddressClassifyRequest.LinkQueryAddress;
import org.macula.cloud.cainiao.configure.CainiaoConfig;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CainiaoLinkServiceTest {

	private CainiaoLinkService service = new CainiaoLinkService(new CainiaoConfig());

	@Test
	public void testGetChinaSubDivisions() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_CHINA_SUB_DIVISIONS -------------------");
		SubDivisionsResponse response = service.getChinaSubDivisions(SubDivisionsRequest.of("320584"));
		response.getDivisionsList().forEach(vo -> {
			log.info(vo.getDivisionName());
		});
		System.out.println(response);
		Assertions.assertTrue(response.isSuccess());
	}

	@Test
	public void testGetChinaDivision() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_CHINA_DIVISION -------------------");
		DivisionResponse response = service.getChinaDivision(DivisionsRequest.of("110100"));
		Assertions.assertTrue(response.isSuccess());
	}

	@Test
	public void testGetDivisionVersionList() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_DIVISION_VERSION_LIST -------------------");
		DivisionVersionListResponse response = service.getDivisionVersionList(DivisionVersionListRequest.of("", null, "1", "1"));
		Assertions.assertTrue(response.isSuccess());
	}

	@Test
	public void testGetVersionChangeList() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_VERSION_CHANGE_LIST -------------------");
		VersionChangeListResponse response = service.getVersionChangeList(VersionChangeListRequest.of("20180510001", null, "1", "1"));
		Assertions.assertTrue(response.isSuccess());
	}

	@Test
	public void testGetAddressClassify() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_ADDRESS_CLASSIFY -------------------");
		AddressClassifyResponse response = service.getAddressClassify(
				AddressClassifyRequest.of(LinkQueryAddress.of("北京北京市朝阳区南磨房镇西大望路甲12号北京国家广告产业园区", "CN", new HashMap<String, String>())));
		Assertions.assertTrue(response.isSuccess());
	}

	@Test
	public void testGetDivisionParse() throws JsonProcessingException {
		service.initialRestTemplate();
		log.info("--------------------------- CNDZK_DIVISION_PARSE -------------------");
		DivisionParseResponse response = service.getDivisionParse(DivisionParseRequest.of("上海市闸北区城区城区中山北路966号30幢101室", "18Q4"));
		System.out.println(response);
	}

}
