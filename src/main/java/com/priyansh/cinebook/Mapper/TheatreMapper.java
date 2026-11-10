package com.priyansh.cinebook.Mapper;


import com.priyansh.cinebook.DTO.RequestDTO.TheatreRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreResponseDTO;
import com.priyansh.cinebook.Entity.TheatreDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheatreMapper {

    TheatreDetails toEntity(TheatreRequestDTO requestDTO);

    TheatreResponseDTO toResponseDTO(TheatreDetails theatreDetails);
}
