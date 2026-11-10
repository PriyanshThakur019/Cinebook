package com.priyansh.cinebook.Mapper;

import com.priyansh.cinebook.DTO.RequestDTO.HallToShowMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallToShowMappingResponseDTO;
import com.priyansh.cinebook.Entity.HallToShowMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HallToShowMappingMapper {

    @Mapping(target = "hallDetails", ignore = true)
    @Mapping(target = "showDetails", ignore = true)
    @Mapping(target = "hallToShowMappingId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    HallToShowMapping toEntity(
            HallToShowMappingRequestDTO requestDTO
    );

    @Mapping(source = "hallDetails.hallDetailsId", target = "hallId")
    @Mapping(source = "showDetails.showDetailId", target = "showDetailId")
    HallToShowMappingResponseDTO toResponseDTO(
            HallToShowMapping mapping
    );
}

