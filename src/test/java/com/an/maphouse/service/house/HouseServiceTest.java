package com.an.maphouse.service.house;

import com.an.maphouse.web.dto.HouseStarDTO;
import com.an.maphouse.web.form.ListHouseStarForm;
import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.service.ServiceMultiResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.util.Assert;

/**
 * @author Annan An
 * @date 2020/7/28 10:39
 */
class HouseServiceTest  extends MapHouseApplicationTests {

    @Autowired
    private HouseService houseService;

    @Test
    @WithUserDetails(value = "Annan")
    void starHouse() {
        houseService.starHouse(57L);
    }

    @Test
    @WithUserDetails(value = "Annan")
    void userStarHouseList() {
        ListHouseStarForm listHouseStarForm = new ListHouseStarForm();
        ServiceMultiResult<HouseStarDTO> result = houseService.userStarHouseList(listHouseStarForm);
        Assert.isTrue(result.getTotal() == 2, "总数不匹配");
    }

    @Test
    @WithUserDetails(value = "Annan")
    void deleteStarInfo() {
        houseService.deleteStarInfo(58L);
    }

    @Test
    @WithUserDetails(value = "Annan")
    void isStar() {
        /*boolean star = houseService.isStar(58L);
        Assert.isTrue(star, "收藏状态不匹配");*/
    }
}