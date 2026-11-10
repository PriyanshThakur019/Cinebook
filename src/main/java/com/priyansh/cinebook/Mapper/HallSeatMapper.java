package com.priyansh.cinebook.Mapper;

import com.priyansh.cinebook.DTO.RequestDTO.HallSeatRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallSeatResponseDTO;
import com.priyansh.cinebook.Entity.HallSeats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HallSeatMapper {

    @Mapping(target = "hallDetails", ignore = true)
    @Mapping(target = "hallSeatId", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    HallSeats toEntity(HallSeatRequestDTO requestDTO);

    @Mapping(source = "hallDetails.hallDetailsId", target = "hallId")
    HallSeatResponseDTO toResponseDTO(HallSeats hallSeats);
}
