package com.an.maphouse.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  地铁线路dto
 * @author Annan An
 * @date 2020/5/9 13:55
 */
@Data
@ApiModel("地铁 DTO")
public class SubwayDTO {

    @ApiModelProperty(value = "地铁线路id", example = "2")
    private Long id;

    @ApiModelProperty(value = "城市代码", example = "3100")
    private String cityId;

    @ApiModelProperty(value = "地铁线路unique id", example = "310100025693")
    private String lineId;

    @ApiModelProperty(value = "地铁线路名称", example = "2号线")
    private String lineName;

    @ApiModelProperty(value = "地铁所属城市英文简称", example = "sh")
    private String cityEnName;






}
