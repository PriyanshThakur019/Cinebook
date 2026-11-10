package com.priyansh.cinebook.Mapper;

import com.priyansh.cinebook.DTO.RequestDTO.TheatreToHallMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreToHallMappingResponseDTO;
import com.priyansh.cinebook.Entity.TheatreToHallMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TheatreToHallMappingMapper {

    @Mapping(target = "theatreDetails", ignore = true)
    @Mapping(target = "hallDetails", ignore = true)
    @Mapping(target = "theatreToHallMappingId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    TheatreToHallMapping toEntity(
            TheatreToHallMappingRequestDTO requestDTO
    );

    @Mapping(
            source = "theatreDetails.theatreDetailId",
            target = "theatreId"
    )
    @Mapping(
            source = "hallDetails.hallDetailsId",
            target = "hallId"
    )
    TheatreToHallMappingResponseDTO toResponseDTO(
            TheatreToHallMapping mapping
    );
}
