package br.com.alveslf.springsecuritytest2.dtos;

import br.com.alveslf.springsecuritytest2.models.enums.RoleEnum;

public record UserDtoRes(Long id, String name, String username, String password, RoleEnum role) {
}
