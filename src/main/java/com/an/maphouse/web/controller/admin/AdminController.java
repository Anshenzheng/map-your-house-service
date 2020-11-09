package com.an.maphouse.web.controller.admin;

import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import com.an.maphouse.base.HouseOperationEnum;
import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import com.an.maphouse.base.HouseOperationEnum;
import com.an.maphouse.entity.SupportAddress;
import com.an.maphouse.property.LimitsProperty;
import com.an.maphouse.service.ServiceMultiResult;
import com.an.maphouse.service.house.AddressService;
import com.an.maphouse.service.house.HouseService;
import com.an.maphouse.service.house.QiniuService;
import com.an.maphouse.service.search.HouseElasticSearchService;
import com.an.maphouse.util.FileUploaderChecker;
import com.an.maphouse.web.dto.*;
import com.an.maphouse.web.form.*;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author Annan An
 * @date 2020/5/9 15:04
 */
@RestController
@RequestMapping("admin")
@Api (tags = "管理员接口")
public class AdminController {

    @Resource
    private HouseService houseService;

    @Resource
    private QiniuService qiniuService;

    @Resource
    private AddressService addressService;

    @Resource
    private LimitsProperty limitsProperty;

    @Resource
    private HouseElasticSearchService houseElasticSearchService;


    @ApiOperation(value = "新增房源接口")
    @PostMapping(value = "house/add")
    public com.an.maphouse.base.ApiResponse<HouseDTO> addHouse(@Validated @RequestBody HouseForm houseForm){
        HouseDTO houseDto = houseService.addHouse(houseForm);
        houseElasticSearchService.save(houseDto.getId());
        return com.an.maphouse.base.ApiResponse.ofSuccess(houseDto);
    }


    @PostMapping(value = "houses")
    @ApiOperation(value = "分页查找房源")
    public com.an.maphouse.base.ApiResponse<ServiceMultiResult<HouseDTO>> listHouses(@RequestBody @Validated AdminHouseSearchForm adminHouseSearchForm){
        ServiceMultiResult<HouseDTO> houseDTOServiceMultiResult = houseService.adminSearch(adminHouseSearchForm);
        return com.an.maphouse.base.ApiResponse.ofSuccess(houseDTOServiceMultiResult);
    }

    @PutMapping("house/edit")
    @ApiOperation(value = "房源信息编辑")
    public com.an.maphouse.base.ApiResponse<HouseDTO> editHouse(@RequestBody @Validated({HouseForm.Edit.class}) HouseForm houseForm){
        HouseDTO houseDto = houseService.editHouse(houseForm);
        houseElasticSearchService.save(houseDto.getId());
        return com.an.maphouse.base.ApiResponse.ofSuccess(houseDto);
    }

    @GetMapping("house/edit/{id}")
    @ApiOperation(value = "通过获得编辑房屋信息")
    public com.an.maphouse.base.ApiResponse<HouseCompleteInfoDTO> findHouse(@PathVariable @ApiParam(value = "房屋id", required = true) Long id){
        // 获取房源信息
        return com.an.maphouse.base.ApiResponse.ofSuccess(houseService.findAgentEditCompleteHouse(id));
    }

    @PostMapping("house/tag")
    @ApiOperation("添加标签")
    public com.an.maphouse.base.ApiResponse addTag(@RequestBody @Validated TagForm tagForm){
        houseService.addTag(tagForm);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @DeleteMapping("house/tag")
    @ApiOperation("删除标签")
    public com.an.maphouse.base.ApiResponse deleteTag(@RequestBody @Validated TagForm tagForm){
        houseService.deleteTag(tagForm);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @DeleteMapping("house/picture/{pictureId}")
    @ApiOperation("删除图片")
    public com.an.maphouse.base.ApiResponse deletePicture(@ApiParam(value = "图片id", required = true) @PathVariable long pictureId){
        houseService.deletePicture(pictureId);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @PutMapping("house/cover")
    @ApiOperation("更新封面")
    public com.an.maphouse.base.ApiResponse updateCover(@RequestBody @Validated CoverForm coverForm){
        houseService.updateCover(coverForm.getCoverId(), coverForm.getHouseId());
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @PutMapping("house/operate/{id}/{operation}")
    @ApiOperation("修改房屋状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房屋id", required = true),
            @ApiImplicitParam(name = "operation", value = "操作类型(0: 下架, 1: 审核通过  2: 在售  3: 删除)", required = true,
                    example = "1", allowableValues = "0,1,2,3"),
    })
    public com.an.maphouse.base.ApiResponse operateHouse(@PathVariable long id, @PathVariable int operation){
        HouseOperationEnum houseOperationEnum = HouseOperationEnum.of(operation);
        houseService.updateStatus(id, houseOperationEnum);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }


    @PostMapping("house/subscribes")
    @ApiOperation("获取房东预约的房源信息")
    public com.an.maphouse.base.ApiResponse<ServiceMultiResult<HouseSubscribeInfoDTO>> listHouseSubscribes(
            @Validated @RequestBody ListHouseSubscribesForm listHouseSubscribesForm){
        ServiceMultiResult<HouseSubscribeInfoDTO> result = houseService.listAdminHouseSubscribes(listHouseSubscribesForm);
        return com.an.maphouse.base.ApiResponse.ofSuccess(result);
    }

    @PutMapping("house/subscribe/{subscribeId}/{operation}")
    @ApiOperation("房东修改预约状态看房")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "预约id", required = true),
            @ApiImplicitParam(name = "operation", value = "操作类型(2: 已联系客户, 3: 已完成看房)", required = true,
                    example = "2", allowableValues = "2, 3"),
    })
    public com.an.maphouse.base.ApiResponse finishHouseSubscribe(
             @PathVariable Long subscribeId,
             @PathVariable int operation
    ){
        houseService.adminUpdateHouseSubscribeStatus(subscribeId, operation);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @DeleteMapping("house/subscribe/{subscribeId}")
    @ApiOperation("房东取消预约看房")
    public com.an.maphouse.base.ApiResponse deleteHouseSubscribe(@ApiParam("预约id") @PathVariable Long subscribeId){
        houseService.cancelHouseSubscribe(subscribeId);
        return com.an.maphouse.base.ApiResponse.ofSuccess();
    }

    @PostMapping(value = "house/upload/photo")
    @ApiOperation(value = "上传图片接口")
    public com.an.maphouse.base.ApiResponse<QiniuUploadResult> uploadHousePhoto(@ApiParam(value = "图片文件") MultipartFile file){
        if(file == null){
            return com.an.maphouse.base.ApiResponse.ofStatus(ApiResponseEnum.NOT_VALID_PARAM);
        }
        FileUploaderChecker.validTypeAndSize(limitsProperty.getHousePhotoTypeLimit(), file.getOriginalFilename(), limitsProperty.getHousePhotoSizeLimit(), file.getSize());
        try {
            return com.an.maphouse.base.ApiResponse.ofSuccess(qiniuService.uploadFile(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponseEnum.FILE_UPLOAD_ERROR);
        }
    }
}
