package com.aizoon.fabrickproyect.service;

import com.aizoon.fabrickproyect.entity.DTO.BonificoDTO;
import com.aizoon.fabrickproyect.entity.DTO.ResponseDTO;

public interface IFabrickService {

    ResponseDTO getSaldo(Long accountId);

    ResponseDTO bonifico(Long accountId,BonificoDTO bonifico);

    ResponseDTO transazioni(Long accountId, String fromDate, String toDate);
}
