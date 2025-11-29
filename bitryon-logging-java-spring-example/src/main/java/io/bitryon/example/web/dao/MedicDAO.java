package io.bitryon.example.web.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.bitryon.example.web.model.MedicCondition;
import io.bitryon.example.web.model.Page;
import io.bitryon.logger.annotation.Logging;

@Repository
@Logging
public class MedicDAO {
	//We could use mybatis interface, then it requires AOP or proxy on interfaces to get logged.

	public List<MedicCondition> query(long userId, Page page){
		MedicCondition medic1 = new MedicCondition();
		medic1.setDeceases("idk, maybe conding fever");
		medic1.setId(321L);
		medic1.setTimeCreated(111111111111111L);
		medic1.setUserId(123L);
		MedicCondition medic2 = new MedicCondition();
		medic2.setDeceases("idk, maybe conding fever");
		medic2.setId(654L);
		medic2.setTimeCreated(222222222222222L);
		medic2.setUserId(456L);
		MedicCondition medic3 = new MedicCondition();
		medic3.setDeceases("idk, maybe conding fever");
		medic3.setId(987L);
		medic3.setTimeCreated(333333333333333L);
		medic3.setUserId(789L);
		return Arrays.asList(medic1, medic2, medic3);
	}
	
	public MedicCondition save(MedicCondition condition){
		MedicCondition medic1 = new MedicCondition();
		medic1.setDeceases(condition.getDeceases());
		medic1.setId(condition.getId());
		medic1.setTimeCreated(condition.getTimeCreated());
		medic1.setUserId(condition.getUserId());
		return medic1;
	}
}
