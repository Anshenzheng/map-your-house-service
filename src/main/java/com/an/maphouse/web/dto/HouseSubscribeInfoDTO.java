package com.an.maphouse.web.dto;

import com.an.maphouse.entity.HouseSubscribe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Annan An
 * @date 2020/6/4 18:23
 */
@Data
@ApiModel("房源预约信息")
public class HouseSubscribeInfoDTO {

   @ApiModelProperty("房源预约信息")
   private HouseSubscribe houseSubscribe;

   @ApiModelProperty("房源信息")
   private HouseDTO houseDTO;

   @ApiModelProperty("用户")
   private UserDTO user;

   @ApiModelProperty("房东信息")
   private UserDTO agent;
}
