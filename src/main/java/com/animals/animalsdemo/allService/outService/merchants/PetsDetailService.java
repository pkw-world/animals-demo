package com.animals.animalsdemo.allService.outService.merchants;

import com.animals.animalsdemo.domain.query.merchants.PagePetsQuery;
import com.animals.animalsdemo.domain.request.merchants.AddPetsDetailReqDTO;
import com.animals.animalsdemo.domain.request.merchants.EditPetsDetailReqDTO;
import com.animals.animalsdemo.domain.response.merchants.PagePetsDetailRespDTO;
import com.animals.animalsdemo.exception.Response;
import com.animals.animalsdemo.unit.Page;

public interface PetsDetailService {

    /**
     * 分页查询
     * @param pagePetsQuery
     * @return
     */
    Response<Page<PagePetsDetailRespDTO>> pageInfo(PagePetsQuery pagePetsQuery);

    /**
     * 保存信息
     * @return
     */
    Response savePetsInfo(AddPetsDetailReqDTO petsDetailReqDTO);

    /**
     * 编辑信息
     * @param editPetsDetailReqDTO
     * @return
     */
    Response editPetsInfo(EditPetsDetailReqDTO editPetsDetailReqDTO);

    /**
     * 删除宠物信息
     * @param editPetsDetailReqDTO
     * @return
     */
    Response deletePetsInfo(EditPetsDetailReqDTO editPetsDetailReqDTO);
}
