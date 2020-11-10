package com.an.maphouse.web.controller.house;

import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.web.dto.SubwayDTO;
import com.an.maphouse.web.dto.SubwayStationDTO;
import com.an.maphouse.web.dto.SupportAddressDTO;
import com.an.maphouse.entity.SupportAddress;
import com.an.maphouse.service.ServiceMultiResult;
import com.an.maphouse.service.house.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 *  城市地区及地铁站控制器
 * @author Annan An
 * @date 2020/5/9 14:44
 */
@RestController
@RequestMapping("address")
@Api(tags = "地区与地铁接口")
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping("support/cities")
    @ApiOperation(value = "获取所有支持的城市列表")
    public ApiResponse<ServiceMultiResult<SupportAddressDTO>> getSupportCities(){
        ServiceMultiResult<SupportAddressDTO> cities = addressService.findAllCities();
        return ApiResponse.ofSuccess(cities);
    }

    @GetMapping("support/regions/{cityEnName}")
    @ApiOperation(value = "获取城市下的所有区县")
    public ApiResponse<ServiceMultiResult<SupportAddressDTO>>  getSupportRegionsByBelongTo(
            @ApiParam(value = "城市英文缩写", required = true, example = "bj") @PathVariable String cityEnName){
        ServiceMultiResult<SupportAddressDTO> result = addressService.findAreaByBelongToAndLevel(cityEnName, SupportAddress.AddressLevel.REGION.getValue());
        return ApiResponse.ofSuccess(result);
    }

    @GetMapping("support/subways/{cityEnName}")
    @ApiOperation(value = "获取城市下的所有地铁线路")
    public ApiResponse<ServiceMultiResult<SubwayDTO>> getSubwaysByCityEnName(
            @ApiParam(value = "城市英文缩写", required = true, example = "bj") @PathVariable String cityEnName){
        ServiceMultiResult<SubwayDTO> result = addressService.findAllSubwayByCityEnName(cityEnName);
        return ApiResponse.ofSuccess(result);
    }

    @GetMapping("support/subwayStations/{lineId}")
    @ApiOperation(value = "获取地铁线路下的所有地铁站")
    public ApiResponse<ServiceMultiResult<SubwayStationDTO>> getSubwayStationsBySubwayId(@ApiParam(value = "地铁线路id", required = true, example = "4") @PathVariable String lineId){
        ServiceMultiResult<SubwayStationDTO> result = addressService.findAllSubwayStationBySubwayId(lineId);
        return ApiResponse.ofSuccess(result);
    }

}
