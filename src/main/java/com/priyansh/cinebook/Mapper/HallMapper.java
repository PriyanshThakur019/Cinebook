package com.priyansh.cinebook.Mapper;

import com.priyansh.cinebook.DTO.RequestDTO.HallRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallResponseDTO;
import com.priyansh.cinebook.Entity.HallDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {

    HallDetails toEntity(HallRequestDTO requestDTO);

    HallResponseDTO toResponseDTO(HallDetails hallDetails);
}
