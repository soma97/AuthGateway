package org.unibl.etf.master.security.auth.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.unibl.etf.master.security.auth.model.domain.User;
import org.unibl.etf.master.security.auth.model.response.UserDetailsResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.role", target = "role")
    UserDetailsResponse userToUserDetailsResponse(User user);
}