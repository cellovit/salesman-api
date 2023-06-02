package com.aiv.crud.salesmanapi.rest.client;

import com.aiv.crud.salesmanapi.domain.dto.BranchDTO;

public interface BranchClient {

    BranchDTO findBranchById(long id);
}
