package com.an.maphouse.web.controller.house;

import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import com.fasterxml.jackson.annotation.JsonView;
import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import com.an.maphouse.elastic.entity.BaiduMapLocation;
import com.an.maphouse.elastic.entity.HouseElastic;
import com.an.maphouse.entity.House;
import com.an.maphouse.entity.HouseDetail;
import com.an.maphouse.entity.SupportAddress;
import com.an.maphouse.entity.User;
import com.an.maphouse.exception.BusinessException;
import com.an.maphouse.service.ServiceMultiResult;
import com.an.maphouse.service.auth.UserService;
import com.an.maphouse.service.house.AddressService;
import com.an.maphouse.service.house.HouseService;
import com.an.maphouse.service.search.HouseElasticSearchService;
import com.an.maphouse.util.AuthenticatedUserUtil;
import com.an.maphouse.web.dto.*;
import com.an.maphouse.web.form.HouseIdListForm;
import com.an.maphouse.web.form.MapBoundSearchForm;
import com.an.maphouse.web.form.MapSearchForm;
import com.an.maphouse.web.form.SearchHouseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Annan An
 * @date 2020/5/14 10:18
 */
@RestController
@Api(tags = "房源接口")
@RequestMapping("house")
public class HouseController {

    @Resource
    private HouseService houseService;

    @Resource
    private UserService userService;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private HouseElasticSearchService houseElasticSearchService;

    @Resource
    private AddressService addressService;

    @PostMapping("houses")
    @ApiOperation(value = "按条件搜索房源")
    public ApiResponse<ServiceMultiResult<HouseDTO>> searchHouses(@RequestBody @Validated SearchHouseForm searchHouseForm){
        ServiceMultiResult<HouseDTO> result = houseService.search(searchHouseForm);
        return ApiResponse.ofSuccess(result);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取房源完整信息")
    public ApiResponse<HouseCompleteInfoDetailDTO> getHouseById(@ApiParam(value = "房屋id") @PathVariable Long id){
        HouseCompleteInfoDTO houseInfo = houseService.findCompleteHouse(id);
        HouseCompleteInfoDetailDTO result = modelMapper.map(houseInfo, HouseCompleteInfoDetailDTO.class);
        // 获取用户信息
        UserDTO agent = userService.findById(houseInfo.getHouse().getAdminId()).orElseThrow(() -> new BusinessException(ApiResponseEnum.USER_NOT_FOUND));
        // 获取小区出租房屋数
        int houseCountInDistrict = houseElasticSearchService
                .aggregateDistrictHouse(houseInfo.getCity().getEnName(),
                        houseInfo.getRegion().getEnName(),
                        houseInfo.getHouse().getDistrict()
                    );
        // TODO 目前采用elastic搜索标题，后期将会采用hadoop + spark计算获取推荐房源
        SearchHouseForm searchHouseForm = new SearchHouseForm();
        searchHouseForm.setPageSize(4);
        searchHouseForm.setCityEnName(houseInfo.getCity().getEnName());
        searchHouseForm.setKeyword(houseInfo.getHouse().getTitle());
        ServiceMultiResult<HouseDTO> suggestResult = houseService.search(searchHouseForm);
        List<HouseDTO> suggest ;
        if(suggestResult == null){
            suggest = new ArrayList<>();
        }else{
            List<HouseDTO> houseDTOS  = suggestResult.getList();
            if(houseDTOS == null){
                suggest = new ArrayList<>();
            }else{
                suggest  = houseDTOS.stream().filter(item -> item!=null && item.getId()!=null && item.getId().longValue() != id).collect(Collectors.toList());

            }

        }

        // 如果当前用户已经登录，则判断当前用户是否搜藏预约过房源
        if(AuthenticatedUserUtil.isAuthenticated()){
            long userId = AuthenticatedUserUtil.getUserId();
            boolean star = houseService.isStarHouse(id, userId);
            boolean reserve = houseService.isReserveHouse(id, userId);
            result.setStar(star);
            result.setReserve(reserve);
        }
        // 返回结果
        result.setAgent(agent);
        result.setHouseCountInDistrict(houseCountInDistrict);
        result.setSuggestHouses(suggest);

        return ApiResponse.ofSuccess(result);
    }

    @GetMapping("search/autocomplete")
    @ApiOperation("房源搜索自动补全")
    public ApiResponse<List<String>> searchAutoComplete(@ApiParam("关键词前缀") @RequestParam String prefix){
        ServiceMultiResult<String> result = houseElasticSearchService.suggest(prefix);
        return ApiResponse.ofSuccess(result.getList());
    }

    @GetMapping("map/{cityEnName}/regions")
    @ApiOperation("地图-》聚合区县房源")
    public ApiResponse<HouseMapRegionsAggDTO> mapAggRegions(@ApiParam("城市英文简称") @PathVariable String cityEnName){
        // 判断城市是否存在
        addressService.findCityByName(cityEnName);
        // 获取所有区县
        ServiceMultiResult<SupportAddressDTO> regions = addressService.findAreaByBelongToAndLevel(cityEnName, SupportAddress.AddressLevel.REGION.getValue());
        // 获取聚合结果
        ServiceMultiResult<HouseBucketDTO> houseBucketDTOResult = houseElasticSearchService.mapAggregateRegionsHouse(cityEnName);
        // 整合数据
        HouseMapRegionsAggDTO houseMapRegionsAggDTO = new HouseMapRegionsAggDTO();
        houseMapRegionsAggDTO.setRegions(regions.getList());
        houseMapRegionsAggDTO.setTotalHouse(houseBucketDTOResult.resultSize());
        houseMapRegionsAggDTO.setAggData(houseBucketDTOResult.getList());
        return ApiResponse.ofSuccess(houseMapRegionsAggDTO);
    }



    @PostMapping("map/city/houses")
    @ApiOperation("地图->按条件搜索当前城市房源信息")
    public ApiResponse<ServiceMultiResult<HouseDTO>> mapCityHouses(@Validated @RequestBody MapSearchForm mapSearchForm){
        return ApiResponse.ofSuccess(houseService.mapHouseSearch(mapSearchForm));
    }

    @PostMapping("houses/ids")
    @ApiOperation(value = "获取id集合中的房源")
    public ApiResponse<ServiceMultiResult<HouseDTO>> findAllByIds(@RequestBody @Validated HouseIdListForm form){
        List<HouseDTO> result = houseService.findAllByIds(form.getHouseIdList());
        return ApiResponse.ofSuccess(new ServiceMultiResult<>(result.size(), result));
    }

}
