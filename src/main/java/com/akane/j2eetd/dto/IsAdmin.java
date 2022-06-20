package com.akane.j2eetd.dto;

import org.springframework.security.access.prepost.PreAuthorize;


@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface IsAdmin {
}
