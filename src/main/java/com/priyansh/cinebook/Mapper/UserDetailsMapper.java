package com.priyansh.cinebook.Mapper;

import com.priyansh.cinebook.DTO.UserDetailsDTO;
import com.priyansh.cinebook.Entity.UserDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsDTO toDTO(UserDetails userDetails);

    List<UserDetailsDTO> toDTOList(List<UserDetails> userDetails);
}
